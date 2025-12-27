package week5.products;
public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyMonths;

    public ElectronicsProduct(String id, String name, double price, String description,
                              int stockQuantity, String brand, int warrantyMonths) {
        super(id, name, price, description, stockQuantity);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    public double calculateDiscount() { return price * 0.10; }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Electronics");
        System.out.println("Brand: " + brand);
        System.out.println("Warranty: " + warrantyMonths + " months");
        System.out.println("----------------------------------------");
    }
}

