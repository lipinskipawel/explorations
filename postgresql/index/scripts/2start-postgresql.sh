#!/bin/bash

docker pull postgres:16.4 &> /dev/null
docker rm -f pg_container_index &> /dev/null

docker run -d \
	-p 5432:5432 \
	--name pg_container_index \
	-e POSTGRES_USER=car_user \
	-e POSTGRES_PASSWORD=car_password \
	-e POSTGRES_DB=cars \
	-v pg_data_index:/var/lib/postgresql/data \
	postgres:16.4 &> /dev/null

echo "Database started"

