// ----------------------------EmployeeManagementSystem-----------------------
import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int i, String n, double s) {
        id = i;
        name = n;
        salary = s;
    }

    public String toString() {
        return id + "," + name + "," + salary;
    }
}

public class project4 {
    static String file = "employees.txt";
    static HashMap<Integer, Employee> employees = new HashMap<>();

    public static void main(String[] args) {
        loadFromFile();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Save & Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    addEmployee(sc);
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    updateEmployee(sc);
                    break;

                case 4:
                    deleteEmployee(sc);
                    break;

                case 5:
                    saveToFile();
                    System.exit(0);
            }
        }
    }

    // LOAD EXISTING DATA
    static void loadFromFile() {
        try {
            File f = new File(file);
            if (!f.exists()) return;

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double salary = Double.parseDouble(parts[2]);

                employees.put(id, new Employee(id, name, salary));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }

    // SAVE TO FILE
    static void saveToFile() {
        try {
            FileWriter fw = new FileWriter(file);
            for (Employee e : employees.values())
                fw.write(e + "\n");
            fw.close();
            System.out.println("Data saved!");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // ADD
    static void addEmployee(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Salary: ");
        double sal = sc.nextDouble();

        employees.put(id, new Employee(id, name, sal));
        System.out.println("Employee Added!");
    }

    // VIEW
    static void viewEmployees() {
        System.out.println("\nEmployees:");
        for (Employee e : employees.values())
            System.out.println(e.id + " | " + e.name + " | " + e.salary);
    }

    // UPDATE
    static void updateEmployee(Scanner sc) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (!employees.containsKey(id)) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("New Name: ");
        String name = sc.nextLine();
        System.out.print("New Salary: ");
        double salary = sc.nextDouble();

        employees.put(id, new Employee(id, name, salary));
        System.out.println("Updated!");
    }

    // DELETE
    static void deleteEmployee(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();

        if (employees.remove(id) != null)
            System.out.println("Deleted!");
        else
            System.out.println("Employee not found.");
    }
}
