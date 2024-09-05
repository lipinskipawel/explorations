#!/bin/bash

echo "Name of the script $0"
echo "First argument $1"
echo "All arguments $@"

echo "Error code of previous command $?"
echo "Last argument to previous command $_"

echo "\" Will expand what do you have inside of them --> $(date)"
echo \'' Will NOT expand what do you have inside of them --> $(date)'

echo "; Will execute one command after the other";
false; echo "This is executed command after the 'false'"

