import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveToFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("output.txt", true);

            System.out.print("Enter text to save: ");
            String data = sc.nextLine();

            fw.write(data + "\n");
            fw.close();
            System.out.println("Saved Successfully!");

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
