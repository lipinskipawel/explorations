CREATE TABLE if NOT EXISTS cars(
    id               UUID            PRIMARY KEY,
    brand            varchar(20)     NOT NULL,
    model            varchar(50)     NOT NULL,
    state            varchar(20)     NOT NULL,
    assembled_date   TIMESTAMP       NULL
);