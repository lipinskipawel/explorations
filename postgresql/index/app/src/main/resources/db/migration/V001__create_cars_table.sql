CREATE TABLE
IF NOT EXISTS
cars (
	id              UUID                    PRIMARY KEY,
	brand           varchar(20)             NOT NULL,
	model           character varying(50)   NOT NULL,
	submodel        varchar(50)             NOT NULL,
	wheels          integer                 NOT NULL,
	current_owner   UUID                    NULL,
	features        TEXT[]                  NOT NULL,
	details         JSONB                   NOT NULL,
	created_date    TIMESTAMP               NOT NULL, -- TIMESTAMP WITHOUT TIME ZONE
	assembled_date  TIMESTAMP WITHOUT TIME ZONE
);
