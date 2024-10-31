#!/bin/bash

PG_DATA=pg_data
docker volume ls | grep $PG_DATA &> /dev/null
pg_data_exits=$?

if [[ $pg_data_exits -gt 0 ]]; then
	docker volume create $PG_DATA &> /dev/null
fi

echo $PG_DATA "docker volume ready to use"

