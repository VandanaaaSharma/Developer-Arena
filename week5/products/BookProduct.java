package week5.products;
public class BookProduct extends Product {
    private String author, isbn;
    private int pages;

    public BookProduct(String id, String name, double price, String description,
                       int stockQuantity, String author, String isbn, int pages) {
        super(id, name, price, description, stockQuantity);
        this.author = author; this.isbn = isbn; this.pages = pages;
    }

    public double calculateDiscount() { return price * 0.10; }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Book");
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Pages: " + pages);
        System.out.println("----------------------------------------");
    }
}
