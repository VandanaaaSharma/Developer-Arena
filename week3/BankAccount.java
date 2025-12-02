public class BankAccount {
    private String accountNumber;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ", New Balance: " + balance);
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ", Remaining Balance: " + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }

    // Getter
    public double getBalance() { return balance; }

    // Simple runner for testing
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("12345", 1000.00);
        System.out.println("Initial balance: " + acc.getBalance());
        acc.deposit(250.00);
        acc.withdraw(100.00);
        acc.withdraw(2000.00); // should show insufficient funds
        System.out.println("Final balance: " + acc.getBalance());
    }
}
