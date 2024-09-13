package com.github.lipinskipawel.dbTransactions.script;

import com.github.lipinskipawel.dbTransactions.CommonDatabaseSetup;
import com.github.lipinskipawel.dbTransactions.CommonUser;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import static com.github.lipinskipawel.dbTransactions.CommonMapper.toRecord;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.UUID.randomUUID;
import static org.jooq.generated.tables.Users.USERS;

class ScriptTest extends CommonDatabaseSetup implements WithAssertions {

    private final Script script = new Script(dataSource);

    @AfterEach
    void afterEach() {
        script.truncateUsers();
    }

    @Test
    void store_user() {
        final var mark = new CommonUser(randomUUID(), "mark", Instant.parse("2024-01-01T10:20:30Z"));

        script.store(mark);

        final var maybeMark = script.findUser(mark.id());
        assertThat(maybeMark).satisfies(it -> {
            assertThat(it).isPresent();

            final var markFound = it.get();
            assertThat(markFound).isEqualTo(mark);
        });
    }

    @Test
    void execute_two_statements() {
        final var lastVisit = Instant.parse("2024-01-01T10:20:30Z");
        final var mark = new CommonUser(randomUUID(), "mark", lastVisit);
        script.store(mark);

        script.executeUserVisit(mark.id(), lastVisit.plus(30, DAYS));

        final var maybeLastVisit = script.findLastVisit(mark.id());
        assertThat(maybeLastVisit).satisfies(it -> {
            assertThat(it).isPresent();

            final var lastVisitFound = it.get();
            assertThat(lastVisitFound).isEqualTo(lastVisit.plus(30, DAYS));
        });
    }

    @Test
    void run_in_transaction() throws InterruptedException {
        final var mark = new CommonUser(randomUUID(), "mark", Instant.now());
        final var john = new CommonUser(randomUUID(), "john", Instant.now());

        final var firstQuery = new CountDownLatch(1);
        final var secondQuery = new CountDownLatch(1);

        final var thirdQuery = new CountDownLatch(1);
        final var fourthQuery = new CountDownLatch(1);

        new Thread(() -> script.dbAccess().transaction(db -> {
            firstQuery.await();
            db.dsl().executeInsert(toRecord(mark));
            secondQuery.countDown();
            thirdQuery.await();
            db.dsl().executeInsert(toRecord(john));
            fourthQuery.countDown();
        })).start();

        final var emptyDb = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(emptyDb).isEqualTo(0);
        firstQuery.countDown();

        secondQuery.await();
        final var onlyOneRecord = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(onlyOneRecord).isEqualTo(0);
        thirdQuery.countDown();

        fourthQuery.await();
        final var twoRecords = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(twoRecords).isEqualTo(2);
    }

    @Test
    void run_in_transaction_CyclicBarrier() throws InterruptedException, BrokenBarrierException {
        final var mark = new CommonUser(randomUUID(), "mark", Instant.now());
        final var john = new CommonUser(randomUUID(), "john", Instant.now());
        final var barrier = new CyclicBarrier(2);

        new Thread(() -> script.dbAccess().transaction(db -> {
            barrier.await();
            db.dsl().executeInsert(toRecord(mark));
            barrier.await();
            db.dsl().executeInsert(toRecord(john));
            barrier.await();
        })).start();

        barrier.await();
        final var emptyDb = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(emptyDb).isEqualTo(0);

        barrier.await();
        final var onlyOneRecord = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(onlyOneRecord).isEqualTo(0);

        barrier.await();
        final var twoRecords = script.dbAccess().selectFrom(USERS).fetch().stream().count();
        assertThat(twoRecords).isEqualTo(2);
    }
}
