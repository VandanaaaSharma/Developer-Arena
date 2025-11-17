import java.util.Scanner;
public class reverse {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a string to reverse: ");
            String input = sc.nextLine();

            // Check for empty input (throw custom exception)
            if (input.trim().length() == 0) {
                throw new Exception("String cannot be empty!");
            }

            // Reverse string manually
            String reversed = "";
            for (int i = input.length() - 1; i >= 0; i--) {
                reversed += input.charAt(i);
            }

            System.out.println("Reversed String: " + reversed);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
