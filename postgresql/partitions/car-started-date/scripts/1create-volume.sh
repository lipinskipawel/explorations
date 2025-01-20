#!/bin/bash

docker volume rm -f pg_data
docker volume create pg_data &> /dev/null
echo $PG_DATA "docker volume ready to use"
