#!/bin/bash

set -e

cd ../..

echo "Building car project"
./gradlew :car-state:clean :car-state:build -x test &> /dev/null

echo "Running car project"
java -jar car-state/build/libs/cars.jar &> /dev/null &
CAR_PID=$!
echo $CAR_PID

sleep 10
echo "Generating car data"
./gradlew :random:car-state:clean :random:car-state:generateCars --args='numCars=100' &> /dev/null

kill $CAR_PID
echo "Car application has been stopped"

