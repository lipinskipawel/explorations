package com.github.lipinskipawel.dbTransactions;

import org.jooq.Converter;

import java.time.Instant;
import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public final class JooqInstantConverter implements Converter<LocalDateTime, Instant> {

    @Override
    public Instant from(LocalDateTime databaseObject) {
        return databaseObject.toInstant(UTC);
    }

    @Override
    public LocalDateTime to(Instant userObject) {
        return LocalDateTime.ofInstant(userObject, UTC);
    }

    @Override
    public Class<LocalDateTime> fromType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<Instant> toType() {
        return Instant.class;
    }
}
