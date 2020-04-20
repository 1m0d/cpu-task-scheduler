import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        read_input();

        TimeMock timeMock = new TimeMock(tasks);
        Scheduler scheduler = new Scheduler(timeMock);
        scheduler.run();

        System.out.print("\n");
        int index = 0;
        for (Task task: tasks ) {
            task.printWaitTime();
            if(index < tasks.size() - 1)
                System.out.print(",");
            index++;
        }
    }

    private static void read_input(){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.isEmpty())
                break;

            String[] data=line.split(",");

            if(data.length != 4)
                throw new IllegalArgumentException("Input should be 4 values delimited by ','");

            tasks.add(new Task(data[0].charAt(0),
                    Integer.parseInt(data[1]),
                    Integer.parseInt(data[2]),
                    Integer.parseInt(data[3]))
            );
        }
        scanner.close();
    }
}
