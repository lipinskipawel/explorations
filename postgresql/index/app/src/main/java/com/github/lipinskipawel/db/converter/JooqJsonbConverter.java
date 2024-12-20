package com.github.lipinskipawel.db.converter;

import org.jooq.Converter;
import org.jooq.JSONB;

import static org.jooq.JSONB.jsonb;

public final class JooqJsonbConverter implements Converter<JSONB, String> {

    @Override
    public String from(JSONB databaseObject) {
        return databaseObject.data();
    }

    @Override
    public JSONB to(String userObject) {
        return jsonb(userObject);
    }

    @Override
    public Class<JSONB> fromType() {
        return JSONB.class;
    }

    @Override
    public Class<String> toType() {
        return String.class;
    }
}
