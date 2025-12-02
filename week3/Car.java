public class Car {
    private String brand;
    private String model;
    private int year;
    private int speed;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.speed = 0;
    }

    // Getters & Setters
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
    public int getSpeed() { return speed; }

    // Methods
    public void accelerate(int amount) {
        speed += amount;
        System.out.println("Accelerated. Current speed: " + speed);
    }

    public void brake(int amount) {
        speed = Math.max(speed - amount, 0);
        System.out.println("Braked. Current speed: " + speed);
    }

    public void displayDetails() {
        System.out.println(brand + " " + model + " (" + year + ")");
    }

    // Simple runner for testing
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Corolla", 2020);
        car.displayDetails();
        System.out.println("Initial speed: " + car.getSpeed());
        car.accelerate(50);
        car.brake(20);
        car.brake(40); // should not go below 0
        System.out.println("Final speed: " + car.getSpeed());
    }
}

