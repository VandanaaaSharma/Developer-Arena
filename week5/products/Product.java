package week5.products;
public abstract class Product {
    protected String id, name, description;
    protected double price;
    protected int stockQuantity;

    public Product(String id, String name, double price, String description, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public abstract double calculateDiscount();

    public double getFinalPrice() {
        return price - calculateDiscount();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getStockQuantity() { return stockQuantity; }
    public void reduceStock(int qty) { stockQuantity -= qty; }

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Price: ₹" + price);
        System.out.println("Description: " + description);
        System.out.println("Stock: " + stockQuantity);
        System.out.println("Discount: ₹" + calculateDiscount());
        System.out.println("Final Price: ₹" + getFinalPrice());
    }
}

