package com.github.lipinskipawel.dbTransactions.active;

import com.github.lipinskipawel.dbTransactions.CommonDatabaseSetup;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static java.time.Clock.systemUTC;
import static java.time.Clock.tick;
import static java.time.temporal.ChronoUnit.MICROS;
import static java.util.UUID.randomUUID;

class UserTest extends CommonDatabaseSetup implements WithAssertions {

    private final UserRepository userRepository = new UserRepository(dataSource);

    @AfterEach
    void afterEach() {
        userRepository.truncateUsers();
    }

    @Test
    void save_user() {
        final var mark = new User(dataSource)
                .setId(randomUUID())
                .setUsername("mark")
                .setLastVisit(instant());

        mark.save();

        final var maybeMark = userRepository.findUser(mark.getId());
        assertThat(maybeMark).satisfies(it -> {
            assertThat(it).isPresent();

            final var markFound = it.get();
            assertThat(markFound).isEqualTo(mark);
        });
    }


    @Test
    void update_user() {
        final var mark = new User(dataSource);
        mark.setId(randomUUID());
        mark.setUsername("mark");
        mark.setLastVisit(instant());
        mark.save();
        final var markFromDb = userRepository.findUser(mark.getId()).orElseThrow();

        markFromDb.setUsername("john");
        markFromDb.update();

        final var maybeJohn = userRepository.findUser(markFromDb.getId());
        assertThat(maybeJohn).satisfies(it -> {
            assertThat(it).isPresent();

            final var markFound = it.get();
            assertThat(markFound).isEqualTo(new User(dataSource)
                    .setId(mark.getId())
                    .setUsername("john")
                    .setLastVisit(mark.getLastVisit()));
        });
    }

    public Instant instant() {
        return tick(systemUTC(), MICROS.getDuration()).instant();
    }
}