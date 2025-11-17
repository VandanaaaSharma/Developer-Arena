import java.util.Scanner;

public class Project2 {

    // Method 1: Display Subject & Marks
    static void displayData(String[] subjects, int[] marks) {
        System.out.println("\n--- SUBJECT MARKS REPORT ---");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println(subjects[i] + " : " + marks[i]);
        }
    }

    // Method 2: Calculate Average
    static double calculateAverage(int[] marks) {
        int sum = 0;
        for (int m : marks) {
            sum += m;
        }
        return (double) sum / marks.length;
    }

    // Method 3: Grade Assignment
    static char getGrade(double avg) {
        if (avg >= 90) return 'A';
        else if (avg >= 75) return 'B';
        else if (avg >= 60) return 'C';
        else if (avg >= 40) return 'D';
        else return 'F';
    }

    // Method 4: Update Mark
    static void updateMark(String[] subjects, int[] marks, int index, int newMark) {
        if (index >= 0 && index < marks.length) {
            marks[index] = newMark;
            System.out.println("Updated " + subjects[index] + " to " + newMark);
        } else {
            System.out.println("Invalid subject index!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==== GRADE MANAGEMENT SYSTEM ====");

        System.out.print("\nEnter Student Name: ");
        String studentName = sc.nextLine();

        System.out.print("Enter Number of Subjects: ");
        int n = sc.nextInt();
        sc.nextLine(); // clear buffer

        String[] subjects = new String[n];
        int[] marks = new int[n];

        System.out.println("\n--- Enter Subject Names ---");

        for (int i = 0; i < n; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            subjects[i] = sc.nextLine();
        }

        System.out.println("\n--- Enter Marks for Each Subject ---");

        for (int i = 0; i < n; i++) {
            System.out.print("Marks in " + subjects[i] + ": ");
            marks[i] = sc.nextInt();
        }

        // Display data
        displayData(subjects, marks);

        // Calculate average
        double avg = calculateAverage(marks);
        System.out.println("\nAverage Marks: " + avg);

        // Grade
        char grade = getGrade(avg);
        System.out.println("Overall Grade: " + grade);

        // Option to update
        System.out.println("\nDo you want to update any marks? (yes/no)");
        sc.nextLine();
        String choice = sc.nextLine().toLowerCase();

        if (choice.equals("yes")) {
            System.out.println("\nSelect subject index to update:");
            for (int i = 0; i < subjects.length; i++) {
                System.out.println(i + " → " + subjects[i]);
            }

            System.out.print("\nEnter index: ");
            int index = sc.nextInt();

            System.out.print("Enter new mark: ");
            int newMark = sc.nextInt();

            updateMark(subjects, marks, index, newMark);

            System.out.println("\n--- UPDATED REPORT ---");
            displayData(subjects, marks);

            avg = calculateAverage(marks);
            System.out.println("New Average: " + avg);
            System.out.println("New Grade: " + getGrade(avg));
        }

        System.out.println("\n==== REPORT GENERATED FOR: " + studentName.toUpperCase() + " ====");
    }
}
