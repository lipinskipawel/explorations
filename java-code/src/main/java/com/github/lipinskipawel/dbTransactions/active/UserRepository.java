package com.github.lipinskipawel.dbTransactions.active;

import com.github.lipinskipawel.dbTransactions.CommonAbstractRepository;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.UUID;

import static org.jooq.generated.tables.Users.USERS;

public final class UserRepository extends CommonAbstractRepository {
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    public Optional<User> findUser(UUID userId) {
        final var users = db.selectFrom(USERS)
                .where(USERS.ID.eq(userId))
                .fetch();
        if (users.size() > 1) {
            throw new RuntimeException("userId must be unique");
        }

        return users
                .map(it -> new User(dataSource)
                        .setId(it.getId())
                        .setUsername(it.getUsername())
                        .setLastVisit(it.getLastVisit()))
                .stream()
                .findAny();
    }

    int truncateUsers() {
        return db.truncate(USERS).execute();
    }
}
