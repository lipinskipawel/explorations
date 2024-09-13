package com.github.lipinskipawel.dbTransactions;

import org.jooq.generated.tables.records.UsersRecord;

public final class CommonMapper {

    public static CommonUser fromRecord(UsersRecord record) {
        return new CommonUser(
                record.getId(),
                record.getUsername(),
                record.getLastVisit()
        );
    }

    public static UsersRecord toRecord(CommonUser user) {
        return new UsersRecord(
                user.id(),
                user.username(),
                user.lastVisit()
        );
    }
}
