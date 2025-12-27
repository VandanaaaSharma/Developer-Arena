package week5.products;

public class ClothingProduct extends Product {
    private String size, color, material;

    public ClothingProduct(String id, String name, double price, String description,
                           int stockQuantity, String size, String color, String material) {
        super(id, name, price, description, stockQuantity);
        this.size = size; this.color = color; this.material = material;
    }

    public double calculateDiscount() { return price * 0.15; }

    public void displayInfo() {
        super.displayInfo();
        System.out.println("Type: Clothing");
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Material: " + material);
        System.out.println("----------------------------------------");
    }
}

