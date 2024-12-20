#!/bin/bash

docker rm -f pg_container_index &> /dev/null
docker volume rm -f pg_data_index &> /dev/null

