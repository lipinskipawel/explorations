package com.github.lipinskipawel.dbTransactions;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

import static org.jooq.SQLDialect.POSTGRES;

public abstract class CommonAbstractRepository {
    protected final DSLContext db;

    public CommonAbstractRepository(DataSource dataSource) {
        this.db = DSL.using(dataSource, POSTGRES);
    }
}
