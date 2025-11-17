import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int level = 1;
        int totalLevels = 3;

        printTitle();

        while (level <= totalLevels) {
            int range = 0;

            // Switch for level setup
            switch (level) {
                case 1:
                    range = 10;
                    System.out.println("\n==============================");
                    System.out.println("           LEVEL 1");
                    System.out.println("        Range: 1 - 10");
                    System.out.println("==============================");
                    break;

                case 2:
                    range = 50;
                    System.out.println("\n==============================");
                    System.out.println("           LEVEL 2");
                    System.out.println("        Range: 1 - 50");
                    System.out.println("==============================");
                    break;

                case 3:
                    range = 100;
                    System.out.println("\n==============================");
                    System.out.println("           LEVEL 3");
                    System.out.println("        Range: 1 - 100");
                    System.out.println("==============================");
                    break;
            }

            int answer = rand.nextInt(range) + 1;
            int attempts = 5;
            boolean levelPassed = false;

            System.out.println("You have " + attempts + " attempts.");

            while (attempts > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts--;

                if (guess == answer) {
                    System.out.println("\n✔ Correct! You passed the level!");
                    levelPassed = true;
                    break;
                } else if (guess < answer) {
                    System.out.println("Too low! Attempts left: " + attempts);
                } else {
                    System.out.println("Too high! Attempts left: " + attempts);
                }
            }

            if (!levelPassed) {
                System.out.println("\n✖ You lost this level.");
                System.out.println("The correct number was: " + answer);
                break;
            }

            level++;
        }

        if (level > totalLevels) {
            System.out.println("\n======================================");
            System.out.println("      CONGRATULATIONS!");
            System.out.println("   You completed ALL levels!");
            System.out.println("======================================");
        }

        System.out.println("\nThank you for playing!");
    }

    public static void printTitle() {
        System.out.println("======================================");
        System.out.println("         NUMBER GUESSING GAME");
        System.out.println("======================================");
    }
}

