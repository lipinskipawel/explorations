#!/bin/bash

docker pull postgres:16.4 &> /dev/null
docker rm -f pg_container &> /dev/null

docker run -d \
	-p 5432:5432 \
	--name pg_container \
	-e POSTGRES_USER=car_user \
	-e POSTGRES_PASSWORD=car_password \
	-e POSTGRES_DB=cars \
	-v pg_data:/var/lib/postgresql/data \
	postgres:16.4 &> /dev/null

echo "Database started"

