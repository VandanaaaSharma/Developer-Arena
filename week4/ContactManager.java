import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    String name;
    String phone;

    Contact(String n, String p) {
        name = n;
        phone = p;
    }

    public String toString() {
        return name + " - " + phone;
    }
}

public class ContactManager {
    public static void main(String[] args) {
        ArrayList<Contact> contacts = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Contact Manager ---");
            System.out.println("1. Add Contact");
            System.out.println("2. View Contacts");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = sc.nextLine();
                    contacts.add(new Contact(name, phone));
                    break;

                case 2:
                    System.out.println("\nContacts:");
                    for (Contact c : contacts)
                        System.out.println(c);
                    break;

                case 3:
                    System.exit(0);
            }
        }
    }
}
