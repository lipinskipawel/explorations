#!/bin/bash

docker rm -f pg_container &> /dev/null
docker volume rm -f pg_data &> /dev/null

