import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create two Coffee objects with prices in PHP
        Coffee espresso = new Coffee(
                "Espresso",
                "Arabica",
                "Small",
                196.00, // Price in PHP
                "Dark",
                "Italy",
                false,
                10,
                new ArrayList<>(List.of("Chocolate", "Nutty")),
                "Espresso Machine"
        );

        Coffee latte = new Coffee(
                "Latte",
                "Robusta",
                "Medium",
                252.00, // Price in PHP
                "Medium",
                "Brazil",
                true,
                5,
                new ArrayList<>(List.of("Vanilla", "Caramel")),
                "Steamed Milk"
        );

        // Call methods for the first object (espresso)
        System.out.println("=== Espresso Details ===");
        espresso.displayDetails();
        System.out.printf("Calculated Price for Large Size (PHP): ₱%.2f%n", espresso.calculatePrice());
        System.out.println("Is in Stock? " + (espresso.isInStock() ? "Yes" : "No"));
        espresso.addFlavor("Citrus");
        espresso.updateStock(-2); // Reduce stock by 2
        espresso.setDecaf(true);
        espresso.setRoastLevel("Medium");
        espresso.applyDiscount(10); // Apply 10% discount
        System.out.println("Updated Description: " + espresso.describe());
        espresso.displayDetails();

        // Call methods for the second object (latte)
        System.out.println("=== Latte Details ===");
        latte.displayDetails();
        System.out.printf("Calculated Price for Small Size (PHP): ₱%.2f%n", latte.calculatePrice());
        System.out.println("Is in Stock? " + (latte.isInStock() ? "Yes" : "No"));
        latte.addFlavor("Hazelnut");
        latte.updateStock(10); // Add 10 to stock
        latte.setDecaf(false);
        latte.setRoastLevel("Dark");
        latte.applyDiscount(20); // Apply 20% discount
        System.out.println("Updated Description: " + latte.describe());
        latte.displayDetails();
    }
}