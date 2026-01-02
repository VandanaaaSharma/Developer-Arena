import java.util.*;

// ===================== PRODUCT CLASSES =====================

// Abstract Product class
abstract class Product {
    protected String id;
    protected String name;
    protected double price;
    protected String description;
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

    public void displayInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.printf("Price: ₹%.2f%n", price);
        System.out.println("Description: " + description);
        System.out.println("Stock: " + stockQuantity);
        System.out.printf("Discount: ₹%.2f%n", calculateDiscount());
        System.out.printf("Final Price: ₹%.2f%n", getFinalPrice());
    }
}

// Electronics Product
class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyMonths;

    public ElectronicsProduct(String id, String name, double price, String description,
                              int stockQuantity, String brand, int warrantyMonths) {
        super(id, name, price, description, stockQuantity);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double calculateDiscount() {
        return price * 0.10;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Electronics");
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyMonths + " months");
        System.out.println("----------------------------------------");
    }
}

// Clothing Product
class ClothingProduct extends Product {
    private String size;
    private String color;
    private String material;

    public ClothingProduct(String id, String name, double price, String description,
                           int stockQuantity, String size, String color, String material) {
        super(id, name, price, description, stockQuantity);
        this.size = size;
        this.color = color;
        this.material = material;
    }

    @Override
    public double calculateDiscount() {
        return price * 0.15;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Clothing");
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Material: " + material);
        System.out.println("----------------------------------------");
    }
}

// Book Product
class BookProduct extends Product {
    private String author;

    public BookProduct(String id, String name, double price, String description,
                       int stockQuantity, String author) {
        super(id, name, price, description, stockQuantity);
        this.author = author;
    }

    @Override
    public double calculateDiscount() {
        return price * 0.10;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Book");
        System.out.println("Author: " + author);
        System.out.println("----------------------------------------");
    }
}

// ===================== CART CLASSES =====================

class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getItemTotal() {
        return product.getFinalPrice() * quantity;
    }
}

class ShoppingCart {
    List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.product.getId().equals(product.getId())) {
                item.quantity += quantity;
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public double getTotalAmount() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getItemTotal();
        }
        return total;
    }

    public void displayCart() {
        System.out.println("\n=== SHOPPING CART ===");
        if (items.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        System.out.printf("%-10s %-20s %-8s %-5s %-10s%n",
                "ID", "Name", "Price", "Qty", "Total");
        System.out.println("--------------------------------------------------");

        for (CartItem item : items) {
            System.out.printf("%-10s %-20s ₹%-7.2f %-5d ₹%-9.2f%n",
                    item.product.getId(),
                    item.product.getName(),
                    item.product.getFinalPrice(),
                    item.quantity,
                    item.getItemTotal());
        }

        System.out.println("--------------------------------------------------");
        System.out.printf("Total Amount: ₹%.2f%n", getTotalAmount());
    }
}

// ===================== ORDER CLASS =====================

class Order {
    private static int counter = 1000;
    private String orderId;
    private ShoppingCart cart;

    public Order(ShoppingCart cart) {
        this.orderId = "ORD" + counter++;
        this.cart = cart;
    }

    public void displayOrder() {
        System.out.println("\n=== ORDER DETAILS ===");
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + new Date());

        cart.displayCart();

        double gst = cart.getTotalAmount() * 0.18;
        System.out.printf("GST (18%%): ₹%.2f%n", gst);
        System.out.printf("Final Amount: ₹%.2f%n", cart.getTotalAmount() + gst);

        System.out.println("🎉 ORDER CONFIRMED!");
    }
}

// ===================== MAIN CLASS =====================

public class ECommerceSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        List<Product> products = new ArrayList<>();
        products.add(new ElectronicsProduct("E001", "Smartphone X", 50000,
                "Latest 5G smartphone", 50, "TechBrand", 24));
        products.add(new ClothingProduct("C001", "Cotton T-Shirt", 1200,
                "100% Cotton T-Shirt", 100, "M", "Blue", "Cotton"));
        products.add(new BookProduct("B001", "Java Programming", 800,
                "Learn Java from scratch", 75, "John Doe"));

        while (true) {
            System.out.println("\n=== E-COMMERCE SYSTEM ===");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    for (Product p : products) {
                        p.displayInfo();
                    }
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    String pid = sc.next();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    boolean found = false;
                    for (Product p : products) {
                        if (p.getId().equalsIgnoreCase(pid)) {
                            cart.addItem(p, qty);
                            System.out.println("✅ Added to cart!");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Product not found!");
                    }
                    break;

                case 3:
                    cart.displayCart();
                    break;

                case 4:
                    if (cart.items.isEmpty()) {
                        System.out.println("Cart is empty!");
                    } else {
                        Order order = new Order(cart);
                        order.displayOrder();
                        return;
                    }
                    break;

                case 5:
                    System.out.println("Thank you for shopping!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
