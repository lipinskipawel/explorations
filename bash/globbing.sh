#!/bin/bash

echo "Globbing reference: https://tldp.org/LDP/abs/html/globbingref.html"

echo 'ls *.sh'
ls *.sh

echo "ls dir?"
ls dir?

echo "mv example.sh example.fish"
echo "mv example.{sh,fish}"
echo "{} will expand that into line above"
