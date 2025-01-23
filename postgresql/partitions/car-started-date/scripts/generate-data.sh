#!/bin/bash

./1create-volume.sh
./2create-image.sh
./3start-postgresql.sh
./4generate-data.sh
./5stop-container.sh

