#!/usr/bin/env bash
psql "postgres://$POSTGRES_USER:$POSTGRES_PASSWORD@$POSTGRES_HOST/$POSTGRES_DB?sslmode=disable" <<-EOSQL
  CREATE TABLE IF NOT EXISTS bus_schedule (
      id integer,
      day integer,
      daytime integer,
      daytimestring text,
      direction text,
      station text
  );
EOSQL