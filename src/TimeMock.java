import java.util.LinkedList;

public class TimeMock {
    private LinkedList<Task>[] timeFrames;
    private int timeNow = 0;

    public TimeMock(Task[] tasks){
        timeFrames = new LinkedList[calculateFrameCount(tasks) + 1];

        for(Task task : tasks){
            int arrival_time = task.get_arrival_time();

            if (timeFrames[arrival_time] == null)
                timeFrames[arrival_time] = new LinkedList<Task>();

            timeFrames[arrival_time].add(task);
        }
    }

    public LinkedList<Task> elapseTime(){
        return timeFrames[timeNow++];
    }

    private int calculateFrameCount(Task[] tasks){
        int maxArrivalTime = 0;
        for(Task task : tasks){
            if(task.get_arrival_time() > maxArrivalTime)
                maxArrivalTime = task.get_arrival_time();
        }
        return maxArrivalTime;
    }
}
