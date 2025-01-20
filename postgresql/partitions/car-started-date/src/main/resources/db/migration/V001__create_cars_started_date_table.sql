CREATE TABLE if NOT EXISTS cars_started_date(
    id               UUID            PRIMARY KEY,
    brand            varchar(20)     NOT NULL,
    model            varchar(50)     NOT NULL,
    state            varchar(20)     NOT NULL,
    started_date     TIMESTAMP       NULL
);
