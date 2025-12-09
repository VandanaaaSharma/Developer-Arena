import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DiaryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String filename = "diary.txt";

        System.out.print("Write today's diary entry: ");
        String entry = sc.nextLine();

        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(LocalDate.now() + ": " + entry + "\n");
            fw.close();
            System.out.println("Entry saved!");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
