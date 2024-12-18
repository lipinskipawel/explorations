ALTER TABLE cars RENAME TO cars_old;

CREATE TABLE if NOT EXISTS cars(
    id               UUID            NOT NULL,
    brand            varchar(20)     NOT NULL,
    model            varchar(50)     NOT NULL,
    state            varchar(20)     NOT NULL,
    assembled_date   TIMESTAMP       NULL,
    CONSTRAINT cars_partitioned_pkey PRIMARY KEY (id, state)
) PARTITION BY LIST(state);

CREATE TABLE cars_wip PARTITION OF cars
    FOR VALUES IN ('WORK_IN_PROGRESS');
CREATE TABLE cars_assembled PARTITION OF cars
    FOR VALUES IN ('ASSEMBLED');
CREATE TABLE cars_sold PARTITION OF cars
    FOR VALUES IN ('SOLD');

INSERT INTO cars_wip
    SELECT *
    FROM cars_old
    WHERE state = 'WORK_IN_PROGRESS';

INSERT INTO cars_assembled
    SELECT *
    FROM cars_old
    WHERE state = 'ASSEMBLED';

INSERT INTO cars_sold
    SELECT *
    FROM cars_old
    WHERE state = 'SOLD';

TRUNCATE TABLE cars_old;
DROP TABLE cars_old;
