#!/bin/bash

set -e

cd ../..

echo "Building car project"
./gradlew :car-started-date:clean :car-started-date:build -x test &> /dev/null

echo "Running car project"
java -jar car-started-date/build/libs/cars-started-date.jar &> /dev/null &
CAR_PID=$!
echo $CAR_PID

sleep 10
echo "Generating car data"
./gradlew :random:r-car-started-date:clean :random:r-car-started-date:generateCars --args='numCars=100' &> /dev/null

kill $CAR_PID
echo "Car application has been stopped"

