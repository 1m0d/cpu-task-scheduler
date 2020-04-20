import java.sql.Time;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Scanner;

public class Main {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        read_input();

        TimeMock timeMock = new TimeMock(tasks);
    }

    private static void read_input(){
        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().isEmpty()){
            String[] data=scanner.nextLine().split(",");

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
