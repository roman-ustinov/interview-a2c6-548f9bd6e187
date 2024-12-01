#!/bin/bash

DB_NAME="animals_db"
DB_USER="root"
DB_PASSWORD="toor"
DB_SCHEMA="animals_schema"

createdb $DB_NAME

execute_sql() {
  psql $DB_NAME -c "$1"
}

echo "Creating PostgreSQL database, user, and schema..."

echo "Creating user: $DB_USER"
execute_sql "CREATE USER $DB_USER WITH PASSWORD '$DB_PASSWORD';"

echo "Granting privileges to user: $DB_USER on database: $DB_NAME"
execute_sql "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DB_USER;"

echo "Creating schema: $DB_SCHEMA"
execute_sql "\c $DB_NAME; CREATE SCHEMA $DB_SCHEMA AUTHORIZATION $DB_USER;"

echo "Granting usage and creation privileges on schema: $DB_SCHEMA"
execute_sql "\c $DB_NAME; GRANT USAGE, CREATE ON SCHEMA $DB_SCHEMA TO $DB_USER;"

echo "PostgreSQL resources created successfully."

