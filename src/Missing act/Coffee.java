import java.util.ArrayList;
import java.util.List;

public class Coffee {
    // Attributes
     String name;
     String type;
     String size;
     double price; // Price in PHP
     String roastLevel;
     String origin;
     boolean isDecaf;
     int stock;
     List<String> flavorNotes;
     String brewMethod;

    // Constructor
    public Coffee(String name, String type, String size, double price, String roastLevel, String origin,
                  boolean isDecaf, int stock, List<String> flavorNotes, String brewMethod) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.price = price;
        this.roastLevel = roastLevel;
        this.origin = origin;
        this.isDecaf = isDecaf;
        this.stock = Math.max(stock, 0); // Ensuring stock is non-negative
        this.flavorNotes = new ArrayList<>(flavorNotes); // Defensive copy
        this.brewMethod = brewMethod;
    }

    // Calculate price based on size
    public double calculatePrice() {
        switch (size.toLowerCase()) {
            case "small":
                return price;
            case "medium":
                return price * 1.5;
            case "large":
                return price * 2.0;
            default:
                return price; // Default price if size is invalid
        }
    }

    // Check if coffee is in stock
    public boolean isInStock() {
        return stock > 0;
    }

    // Add a flavor note
    public void addFlavor(String note) {
        if (note != null && !note.isEmpty()) {
            flavorNotes.add(note);
        }
    }

    // Update stock quantity
    public void updateStock(int quantity) {
        stock = Math.max(stock + quantity, 0); // Ensuring stock is non-negative
    }

    // Get coffee description
    public String describe() {
        return String.format("A %s roast coffee with %s notes.", roastLevel.toLowerCase(), String.join(", ", flavorNotes));
    }

    // Update decaf status
    public void setDecaf(boolean isDecaf) {
        this.isDecaf = isDecaf;
    }

    // Change roast level
    public void setRoastLevel(String newRoastLevel) {
        if (newRoastLevel != null && !newRoastLevel.isEmpty()) {
            this.roastLevel = newRoastLevel;
        }
    }

    // Apply discount
    public void applyDiscount(double percentage) {
        if (percentage > 0 && percentage <= 100) {
            price -= price * (percentage / 100);
        }
    }

    // Display coffee details
    public void displayDetails() {
        System.out.println("------------------------------");
        System.out.println("Coffee Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Size: " + size);
        System.out.printf("Price (PHP): ₱%.2f%n", calculatePrice());
        System.out.println("Roast Level: " + roastLevel);
        System.out.println("Origin: " + origin);
        System.out.println("Decaf: " + (isDecaf ? "Yes" : "No"));
        System.out.println("Stock: " + stock);
        System.out.println("Flavor Notes: " + (flavorNotes.isEmpty() ? "None" : String.join(", ", flavorNotes)));
        System.out.println("Brew Method: " + brewMethod);
        System.out.println("------------------------------");
    }
}