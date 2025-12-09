import java.util.HashMap;
import java.util.Scanner;

public class StudentDB {
    public static void main(String[] args) {
        HashMap<Integer, String> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Database ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    students.put(id, name);
                    break;

                case 2:
                    System.out.println("\nStudents:");
                    for (Integer key : students.keySet()) {
                        System.out.println(key + " : " + students.get(key));
                    }
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
