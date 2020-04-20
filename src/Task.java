public class Task {
    enum Priority { LOW, HIGH }

    private char pid;
    private Priority priority;
    private int arrival_time;
    private int burst;

    public char get_pid() { return pid; }
    public Priority get_priority() { return priority; }
    public int get_arrival_time() { return arrival_time; }
    public int get_burst() { return burst; }

    public Task(char pid, int priority, int arrival_time, int burst) {
        if(priority == 0)
            this.priority = Priority.HIGH;
        else if(priority == 1)
            this.priority = Priority.LOW;
        else
            throw new IllegalArgumentException( String.format("Invalid priority value, has to be 0 or 1, got: %d", priority) );

        if(arrival_time < 0)
            throw new IllegalArgumentException( String.format("Invalid arrival time value, has to be >= 0, got: %d", arrival_time));

        if(burst < 1)
            throw new IllegalArgumentException( String.format("Invalid burst value, has to be >= 1, got: %d", burst));

        this.pid = Character.toLowerCase(pid);
        this.arrival_time = arrival_time;
        this.burst = burst;
    }

    public void run_task(){
        if(burst <= 0)
            throw new IllegalStateException("Task has already finished running");

        burst -= 1;
    }
}
