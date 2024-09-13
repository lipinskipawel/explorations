create table if not exists Users (
    id              UUID            PRIMARY KEY,
    username        varchar(15)     NOT NULL,
    last_visit      TIMESTAMP       NOT NULL
);

create table if not exists VisitLogs (
    id              UUID        PRIMARY KEY,
    user_id         UUID        NOT NULL,
    visit_date      TIMESTAMP   NOT NULL
);
