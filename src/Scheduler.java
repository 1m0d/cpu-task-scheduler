import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Scheduler {
    TimeMock timeMock;
    PriorityQueue<Task> sRTF = new PriorityQueue<>(new SRTFComparator());
    LinkedList<Task> roundRobin = new LinkedList<>();
    int roundRobinCountdown;
    boolean sRTFRunning = false;
    Task runningTask;

    public Scheduler(TimeMock timeMock) {
        this.timeMock = timeMock;
    }

    public void run() {
        while(true) {
            ArrayList<Task> tasks = timeMock.elapseTime();

            if (tasks == null && sRTF.isEmpty() && roundRobin.isEmpty() && !timeMock.getHasMoreData())
                break;

            if (tasks != null) {
                for (Task task : tasks) {
                    if (task.get_priority() == Task.Priority.HIGH)
                        sRTF.add(task);
                    else
                        roundRobin.add(task);
                }
            }

            // Round Robin
            if (sRTF.isEmpty() && !roundRobin.isEmpty()) {
                // There is no running task
                if (runningTask == null) {
                    Task task = roundRobin.pollFirst();
                    runningTask = task;
                    runningTask.printPID();
                }
                // There is a running low priority task which has run for 2 time frames
                else if (roundRobinCountdown == 0 && !sRTFRunning) {
                    roundRobin.add(runningTask);
                    runningTask = roundRobin.pollFirst();
                    runningTask.printPID();
                    roundRobinCountdown = 2;
                }
                roundRobinCountdown--;
            }
            // SRTF
            else if (!sRTF.isEmpty() && !sRTFRunning) {
                // Low priority task running, stop it and put it back in que
                if(runningTask != null)
                    roundRobin.add(runningTask);
                runningTask = sRTF.poll();
                runningTask.printPID();
                sRTFRunning = true;
            }


            if(runningTask != null) {
                runningTask.run_task();

                if (runningTask.get_burst() <= 0) {
                    runningTask = null;
                    sRTFRunning = false;
                }
            }

            // Count wait time
            for (Task task: roundRobin ) { task.pause(); }
            for (Task task: sRTF ) { task.pause(); }
        }
    }

    class SRTFComparator implements Comparator<Task> {
        public int compare(Task t1, Task t2) {
            if (t1.get_burst() < t2.get_burst())
                return 1;
            else if (t1.get_burst() > t2.get_burst())
                return -1;
            return 0;
        }
    }
}


