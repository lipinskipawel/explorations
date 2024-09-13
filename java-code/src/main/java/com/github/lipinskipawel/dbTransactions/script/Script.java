package com.github.lipinskipawel.dbTransactions.script;

import com.github.lipinskipawel.dbTransactions.CommonAbstractRepository;
import com.github.lipinskipawel.dbTransactions.CommonMapper;
import com.github.lipinskipawel.dbTransactions.CommonUser;
import org.jooq.DSLContext;
import org.jooq.Record1;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static com.github.lipinskipawel.dbTransactions.CommonMapper.toRecord;
import static java.util.UUID.randomUUID;
import static org.jooq.generated.tables.Users.USERS;
import static org.jooq.generated.tables.Visitlogs.VISITLOGS;

public final class Script extends CommonAbstractRepository {

    public Script(DataSource dataSource) {
        super(dataSource);
    }

    public int store(CommonUser user) {
        return db.executeInsert(toRecord(user));
    }

    public void executeUserVisit(UUID userId, Instant visitDate) {
        db.transaction(transaction -> {
            transaction.dsl().update(USERS)
                    .set(USERS.LAST_VISIT, visitDate)
                    .where(USERS.ID.eq(userId))
                    .execute();
            transaction.dsl().insertInto(VISITLOGS)
                    .columns(VISITLOGS.ID, VISITLOGS.USER_ID, VISITLOGS.VISIT_DATE)
                    .values(randomUUID(), userId, visitDate)
                    .execute();
        });
    }

    public Optional<CommonUser> findUser(UUID userId) {
        final var users = db.selectFrom(USERS)
                .where(USERS.ID.eq(userId))
                .fetch()
                .map(CommonMapper::fromRecord);
        if (users.size() > 1) {
            throw new RuntimeException("userId must be unique");
        }
        return users.stream().findAny();
    }

    public Optional<Instant> findLastVisit(UUID userId) {
        final var lastVisit = db.select(VISITLOGS.VISIT_DATE)
                .from(VISITLOGS)
                .where(VISITLOGS.USER_ID.eq(userId))
                .fetch()
                .map(Record1::component1);
        if (lastVisit.size() > 1) {
            throw new RuntimeException("userId must be unique");
        }
        return lastVisit.stream().findAny();
    }

    DSLContext dbAccess() {
        return db;
    }

    int truncateUsers() {
        return db.truncate(USERS).execute();
    }
}
