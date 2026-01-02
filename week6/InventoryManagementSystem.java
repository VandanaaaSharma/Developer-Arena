package week6;

import java.util.*;

// ================= PRODUCT CLASS =================

class Product implements Comparable<Product> {
    private String sku;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private Date lastUpdated;

    public Product(String sku, String name, String category, double price, int quantity) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.lastUpdated = new Date();
    }

    // Natural sorting by SKU
    @Override
    public int compareTo(Product other) {
        return this.sku.compareTo(other.sku);
    }

    // HashSet uniqueness
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product p = (Product) obj;
        return sku.equals(p.sku);
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }

    public String getSku() { return sku; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastUpdated = new Date();
    }

    public double getInventoryValue() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-18s %-15s %-10.2f %-8d %-12.2f",
                sku, name, category, price, quantity, getInventoryValue());
    }
}

// ================= COMPARATORS =================

class PriceComparator implements Comparator<Product> {
    public int compare(Product a, Product b) {
        return Double.compare(a.getPrice(), b.getPrice());
    }
}

class ValueComparator implements Comparator<Product> {
    public int compare(Product a, Product b) {
        return Double.compare(b.getInventoryValue(), a.getInventoryValue());
    }
}

class NameComparator implements Comparator<Product> {
    public int compare(Product a, Product b) {
        return a.getName().compareTo(b.getName());
    }
}

// ================= MAIN SYSTEM =================

public class InventoryManagementSystem {

    private HashSet<Product> productSet = new HashSet<>();
    private LinkedList<String> transactionHistory = new LinkedList<>();
    private Stack<Product> undoStack = new Stack<>();
    private Queue<Product> lowStockQueue = new LinkedList<>();

    private Scanner sc = new Scanner(System.in);

    // ================= ADD PRODUCT =================

    private void addProduct() {
        System.out.println("\n=== ADD NEW PRODUCT ===");
        System.out.print("Enter SKU: ");
        String sku = sc.next();

        System.out.print("Enter Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Category: ");
        String category = sc.nextLine();

        System.out.print("Enter Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();

        Product p = new Product(sku, name, category, price, qty);

        if (productSet.add(p)) {
            transactionHistory.addFirst("ADD: " + sku + " (" + qty + ") at " + new Date());

            if (qty < 10) {
                lowStockQueue.add(p);
                System.out.println("⚠️  LOW STOCK ALERT!");
            }

            System.out.println("✅ Product added successfully!");
        } else {
            System.out.println("❌ Product with this SKU already exists!");
        }
    }

    // ================= UPDATE QUANTITY =================

    private void updateQuantity() {
        System.out.print("\nEnter SKU to update: ");
        String sku = sc.next();

        for (Product p : productSet) {
            if (p.getSku().equals(sku)) {

                undoStack.push(new Product(
                        p.getSku(), p.getName(), p.getCategory(),
                        p.getPrice(), p.getQuantity()
                ));

                System.out.print("Enter new quantity: ");
                int newQty = sc.nextInt();

                p.setQuantity(newQty);
                transactionHistory.addFirst(
                        "UPDATE: " + sku + " quantity changed to " + newQty + " at " + new Date()
                );

                System.out.println("✅ Quantity updated successfully!");
                return;
            }
        }
        System.out.println("❌ Product not found!");
    }

    // ================= UNDO =================

    private void undoLastUpdate() {
        if (undoStack.isEmpty()) {
            System.out.println("❌ No operation to undo!");
            return;
        }

        Product prev = undoStack.pop();
        for (Product p : productSet) {
            if (p.getSku().equals(prev.getSku())) {
                p.setQuantity(prev.getQuantity());
                System.out.println("✅ Last update undone!");
                System.out.println("Quantity reverted to: " + prev.getQuantity());
                return;
            }
        }
    }

    // ================= DISPLAY PRODUCTS =================

    private void viewProducts() {
        System.out.print("\nSort by (sku/price/value/name): ");
        String choice = sc.next();

        List<Product> list = new ArrayList<>(productSet);

        switch (choice.toLowerCase()) {
            case "sku":
                Collections.sort(list);
                break;
            case "price":
                Collections.sort(list, new PriceComparator());
                break;
            case "value":
                Collections.sort(list, new ValueComparator());
                break;
            case "name":
                Collections.sort(list, new NameComparator());
                break;
            default:
                System.out.println("Invalid option!");
                return;
        }

        System.out.println("\n=== PRODUCT LIST ===");
        System.out.printf("%-10s %-18s %-15s %-10s %-8s %-12s%n",
                "SKU", "Name", "Category", "Price", "Qty", "Value");
        System.out.println("-".repeat(80));

        for (Product p : list) {
            System.out.println(p);
        }
    }

    // ================= LOW STOCK =================

    private void lowStockAlerts() {
        System.out.println("\n=== LOW STOCK ALERTS ===");
        if (lowStockQueue.isEmpty()) {
            System.out.println("No low stock items!");
            return;
        }

        int i = 1;
        for (Product p : lowStockQueue) {
            System.out.println(i++ + ". " + p.getSku() + " - " + p.getName()
                    + " (Qty: " + p.getQuantity() + ")");
        }
    }

    // ================= TRANSACTIONS =================

    private void viewTransactions() {
        System.out.print("\nEnter number of transactions to view: ");
        int count = sc.nextInt();

        System.out.println("\n=== LAST " + count + " TRANSACTIONS ===");
        int shown = 0;
        for (String t : transactionHistory) {
            if (shown++ == count) break;
            System.out.println(t);
        }
    }

    // ================= STATISTICS =================

    private void statistics() {
        double totalValue = 0;
        for (Product p : productSet) {
            totalValue += p.getInventoryValue();
        }

        System.out.println("\n=== INVENTORY STATISTICS ===");
        System.out.println("Total Products: " + productSet.size());
        System.out.printf("Total Inventory Value: %.2f%n", totalValue);
    }

    // ================= MENU =================

    public void start() {
        while (true) {
            System.out.println("\n=== INVENTORY MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. View Products (Sorted)");
            System.out.println("4. Low Stock Alerts");
            System.out.println("5. Transaction History");
            System.out.println("6. Inventory Statistics");
            System.out.println("7. Undo Last Update");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addProduct();
                case 2 -> updateQuantity();
                case 3 -> viewProducts();
                case 4 -> lowStockAlerts();
                case 5 -> viewTransactions();
                case 6 -> statistics();
                case 7 -> undoLastUpdate();
                case 8 -> {
                    System.out.println("Thank you! Inventory system closed.");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static void main(String[] args) {
        new InventoryManagementSystem().start();
    }
}

