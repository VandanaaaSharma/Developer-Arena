import java.util.Scanner;

public class StudentMarksManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== STUDENT MARKS MANAGER ===");

        // 1. Create array size
        System.out.print("Enter number of students: ");
        int count = sc.nextInt();

        // 2. Create array
        int[] marks = new int[count];

        // 3. Input marks into the array
        for (int i = 0; i < count; i++) {
            System.out.print("Enter marks for student " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        // 4. Process array (max, min, average)
        int max = marks[0];
        int min = marks[0];
        int sum = 0;

        for (int m : marks) {
            sum += m;
            if (m > max) max = m;
            if (m < min) min = m;
        }

        double avg = (double) sum / count;

        // 5. Output results
        System.out.println("\n=== RESULTS ===");
        System.out.println("Highest Mark: " + max);
        System.out.println("Lowest Mark: " + min);
        System.out.println("Average Mark: " + avg);

        // 6. Print all marks
        System.out.println("\nAll Marks Entered:");
        for (int m : marks) {
            System.out.print(m + " ");
        }
    }
}
