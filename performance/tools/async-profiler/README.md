# async-profiler

https://github.com/async-profiler/async-profiler

First check your kernel runtime variables by
```sh
sysctl kernel.perf_event_paranoid
sysctl kernel.kptr_restrict
```

you have to set it to 0, and 1 respectively. Run
```sh
sudo sysctl kernel.perf_event_paranoid=1
sudo sysctl kernel.kptr_restrict=0
```

after profiling you can set variables to previous values.

Running async-profiler after downloading
```
asprof -d 10 -f flamegraph.html [pid]
asprof -d 10 -e cpu -o flamegraph -f flamegraph.html [pid]
```

