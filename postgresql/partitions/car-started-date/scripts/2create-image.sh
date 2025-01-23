#!/bin/bash

set -e

docker build -t postgresql-partition . &> /dev/null
