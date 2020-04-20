import java.util.ArrayList;
public class TimeMock {
    private ArrayList<Task>[] timeFrames;
    private int timeNow = 0;

    public TimeMock(Task[] tasks){
        timeFrames = new ArrayList[calculateFrameCount(tasks) + 1];

        for(Task task : tasks){
            int arrival_time = task.get_arrival_time();

            if (timeFrames[arrival_time] == null)
                timeFrames[arrival_time] = new ArrayList<Task>();

            timeFrames[arrival_time].add(task);
        }
    }

    public ArrayList<Task> elapseTime(){
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
