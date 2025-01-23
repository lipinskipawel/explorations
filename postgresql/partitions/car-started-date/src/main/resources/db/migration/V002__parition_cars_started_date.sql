--before that update all the rows to have timestamp
ALTER TABLE cars_started_date
    ALTER COLUMN started_date SET NOT NULL;

CREATE TABLE cars_started_date_template
    (LIKE cars_started_date INCLUDING ALL);

--ALTER TABLE cars_started_date_template ADD PRIMARY KEY (id);

SELECT partman.create_parent(
    p_parent_table := 'public.cars_started_date',
    p_control := 'started_date',
    p_jobmon := false,
    p_type := 'range',
    p_interval := '1 month',
    p_premake := 4,
    p_start_partition := '2025-01-01',
    p_template_table := 'public.cars_started_date_template'
);
