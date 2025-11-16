
import java.util.Scanner;
public class project1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student age: ");
        int age = sc.nextInt();

        System.out.print("Enter student grade: ");
        sc.nextLine(); 
        String grade = sc.nextLine();

        System.out.println("\n--- Student Information ---");
        System.out.printf("Name  : %s\n", name);
        System.out.printf("Age   : %d\n", age);
        System.out.printf("Grade : %s\n", grade);

        System.out.println("---------------------------");
    }

}
