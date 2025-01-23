CREATE SCHEMA IF NOT EXISTS partman;
CREATE EXTENSION pg_partman SCHEMA partman;
CREATE ROLE partman_user;

GRANT ALL ON ALL TABLES IN SCHEMA partman TO partman_user;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA partman TO partman_user;
GRANT USAGE ON SCHEMA partman TO partman_user;
GRANT ALL ON SCHEMA partman TO partman_user;
