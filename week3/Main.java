public class Main {
    public static void main(String[] args) {

        // Student objects
        Student s1 = new Student("Alice", 101, "A");
        Student s2 = new Student("Bob", 102, "B");
        s1.displayInfo();
        s2.displayInfo();

        // BankAccount objects
        BankAccount acc = new BankAccount("12345", 5000);
        acc.deposit(1500);
        acc.withdraw(2000);

        // Book objects
        Book b1 = new Book("Java Programming", "James Gosling");
        b1.borrow();
        b1.returnBook();

        // Car objects
        Car car = new Car("Toyota", "Corolla", 2020);
        car.displayDetails();
        car.accelerate(30);
        car.brake(10);
    }
}

