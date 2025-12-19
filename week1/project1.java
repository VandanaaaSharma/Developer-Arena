import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private int age;
    private String grade;
    private String contact;

    public Student(String studentId, String name, int age, String grade, String contact) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.contact = contact;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        } else {
            System.out.println("Invalid age! Age must be positive.");
        }
    }

    public void displayStudent() {
        System.out.printf("%-10s %-15s %-5d %-8s %-15s\n",
                studentId, name, age, grade, contact);
    }
}

public class project1 {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== STUDENT INFORMATION SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine();

        if (age <= 0) {
            System.out.println("Age must be positive!");
            return;
        }

        System.out.print("Enter Grade: ");
        String grade = sc.nextLine();

        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();

        students.add(new Student(id, name, age, grade, contact));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        System.out.printf("%-10s %-15s %-5s %-8s %-15s\n",
                "ID", "Name", "Age", "Grade", "Contact");
        System.out.println("--------------------------------------------------");

        for (Student s : students) {
            s.displayStudent();
        }
    }

    static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String id = sc.nextLine();

        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                System.out.println("\nStudent Found:");
                s.displayStudent();
                return;
            }
        }
        System.out.println("Student not found!");
    }
}
