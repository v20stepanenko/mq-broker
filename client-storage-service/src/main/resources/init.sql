DO
$do$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_roles WHERE rolname = 'client') THEN
CREATE ROLE client WITH LOGIN PASSWORD 'client';
END IF;
END
$do$;

DO
$do$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'client_db') THEN
      CREATE DATABASE client_db OWNER client;
END IF;
END
$do$;

\c client_db;

DO
$do$
BEGIN
   IF NOT EXISTS (SELECT schema_name FROM information_schema.schemata WHERE schema_name = 'client_info') THEN
CREATE SCHEMA client_info AUTHORIZATION client;
END IF;
END
$do$;

GRANT CONNECT ON DATABASE client_db TO client;
GRANT ALL PRIVILEGES ON SCHEMA client_info TO client;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA client_info TO client;
ALTER DEFAULT PRIVILEGES IN SCHEMA client_info GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO client;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA client_info TO client;
