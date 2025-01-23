CREATE TABLE if NOT EXISTS cars_started_date(
    id               UUID            NOT NULL,
    brand            varchar(20)     NOT NULL,
    model            varchar(50)     NOT NULL,
    state            varchar(20)     NOT NULL,
    started_date     TIMESTAMP       NULL
) PARTITION BY RANGE(started_date);
