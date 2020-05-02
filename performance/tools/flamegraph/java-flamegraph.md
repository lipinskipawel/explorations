# Java Flame graph

- run `java process with -XX:+PreserveFramePointer`
- run perf tool ``perf record -F 99 -p `pgrep java` -g --call-graph dwarf -- sleep 120``
- run perf-map-agent ``./bin/create-java-perf-map.sh `pgrep java` ``

After collecting perf.data from second command it is time for further processing:
- run `perf script > perf.data.script`
- run `cat perf.data.script | ./stackcollapse-perf.pl > perf.data.folded`
- run `cat perf.data.folded | ./flamegraph.pl > perf.data.svg`
