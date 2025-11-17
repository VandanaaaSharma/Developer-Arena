import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Stored login details
        String correctUsername = "admin";
        String correctPassword = "password123";

        System.out.println("=== SIMPLE LOGIN SYSTEM ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        // Using String methods
        username = username.trim().toLowerCase();   // remove spaces + convert for case-insensitive login
        password = password.trim();                 // remove extra spaces

        // Login validation
        if (username.equals(correctUsername) && password.equals(correctPassword)) {
            System.out.println("\nLogin Successful! Welcome " + username.toUpperCase());
        } else {
            System.out.println("\nLogin Failed!");
            System.out.println("Username or password is incorrect.");
        }
    }
}
