import java.io.*;
import java.util.*;

public class TodoList {
    static String file = "todo.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter task: ");
                    String task = sc.nextLine();

                    try {
                        FileWriter fw = new FileWriter(file, true);
                        fw.write(task + "\n");
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Error writing file.");
                    }
                    break;

                case 2:
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        System.out.println("\nTasks:");
                        while ((line = br.readLine()) != null)
                            System.out.println("- " + line);
                        br.close();
                    } catch (Exception e) {
                        System.out.println("Error reading file.");
                    }
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}

