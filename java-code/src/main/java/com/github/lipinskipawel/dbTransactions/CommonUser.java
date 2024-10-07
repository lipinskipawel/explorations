package com.github.lipinskipawel.dbTransactions;

import java.time.Instant;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public record CommonUser(
    UUID id,
    String username,
    Instant lastVisit
) {

    public CommonUser {
        requireNonNull(id);
        requireNonNull(username);
        requireNonNull(lastVisit);
    }
}
