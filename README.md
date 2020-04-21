# Task Scheduler

Globally preemptive CPU scheduler with static priorities. High priority tasks get assigned to  SRTF (Shortest Remaining Time First) scheduler, low priority tasks get assigned to RR (Round Robin) scheduler.

## Input
Input expects the following data:
pid(char), priority(int), arrival time(int), burst (int)

Tasks are seperated by new lines, program reads until empty line.

### Example
```shell
A,1,2,7
B,0,4,2
C,1,2,2
D,1,2,1
```

## Output

The first line is the order of the processes, the second line is the wait time for each process.

### Example
```shell
ABCDA
A:5,B:0,C:4,D:6
```
