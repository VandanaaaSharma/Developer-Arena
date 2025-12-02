public class Book {
    private String title;
    private String author;
    private boolean available;

    // Constructor
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true; // default
    }

    // Getter and Setter
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    // Methods
    public void borrow() {
        if (available) {
            available = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnBook() {
        available = true;
        System.out.println(title + " has been returned.");
    }

    // Simple runner for testing
    public static void main(String[] args) {
        Book b = new Book("1984", "George Orwell");
        System.out.println("Available: " + b.isAvailable());
        b.borrow();
        b.borrow(); // try borrowing again
        System.out.println("Available after borrow attempts: " + b.isAvailable());
        b.returnBook();
        System.out.println("Available after return: " + b.isAvailable());
    }
}
