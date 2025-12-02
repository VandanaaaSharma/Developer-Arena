package Project3;

public class Main {

    // ------------------ Book Class ------------------
    static class Book {
        private String title;
        private String author;
        private boolean available;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public boolean isAvailable() { return available; }

        public void borrowBook() {
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
    }

    // ------------------ Member Class ------------------
    static class Member {
        private String name;
        private int memberId;
        private Book borrowedBook;

        public Member(String name, int memberId) {
            this.name = name;
            this.memberId = memberId;
            this.borrowedBook = null;
        }

        public void borrow(Book book) {
            if (borrowedBook != null) {
                System.out.println(name + " already borrowed: " + borrowedBook.getTitle());
                return;
            }

            if (book.isAvailable()) {
                book.borrowBook();
                borrowedBook = book;
                System.out.println(name + " successfully borrowed: " + book.getTitle());
            } else {
                System.out.println(book.getTitle() + " is not available.");
            }
        }

        public void returnBook() {
            if (borrowedBook == null) {
                System.out.println(name + " has no book to return.");
                return;
            }

            borrowedBook.returnBook();
            System.out.println(name + " returned: " + borrowedBook.getTitle());
            borrowedBook = null;
        }
    }

    // ------------------ Main Method ------------------
    public static void main(String[] args) {

        // Books
        Book b1 = new Book("Clean Code", "Robert C. Martin");
        Book b2 = new Book("Java Programming", "James Gosling");

        // Members
        Member m1 = new Member("Alice", 101);
        Member m2 = new Member("Bob", 102);

        // Borrow / Return
        m1.borrow(b1); 
        m2.borrow(b1); // not available
        m1.returnBook();
        m2.borrow(b1);
        m1.borrow(b2); 
    }
}
