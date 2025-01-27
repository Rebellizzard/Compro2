import java.util.Scanner;

public class ActivityOne{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        int[] quantities = new int[5]; // to store quantities for each item (including index 0 for finishing order)

        do {
            System.out.println("--- Coffee Menu ---");
            System.out.println("1. Espresso - 50.0 PHP");
            System.out.println("2. Latte - 70.0 PHP");
            System.out.println("3. Cappuccino - 65.0 PHP");
            System.out.println("4. Mocha - 80.0 PHP");
            System.out.println("0. Finish order");
            System.out.print("Choose your coffee (1-4, or 0 to finish): ");

            try {
                choice = Integer.parseInt(input.nextLine());

                if (choice >= 1 && choice <= 4) {
                    System.out.print("Enter quantity: ");
                    int quantity = Integer.parseInt(input.nextLine());
                    quantities[choice] += quantity;
                } else if (choice != 0) {
                    System.out.println("Invalid choice, please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, please try again.");
                choice = -1; // continue the loop
            }
        } while (choice != 0);

        printReceipt(quantities);
        input.close();
    }

    public static void printReceipt(int[] quantities) {
        double[] prices = {0.0, 50.0, 70.0, 65.0, 80.0};
        String[] names = {"", "Espresso", "Latte", "Cappuccino", "Mocha"};
        double total = 0.0;

        System.out.println("\n--- Coffee Oder Receipt ---");
        for (int i = 1; i < quantities.length; i++) {
            if (quantities[i] > 0) {
                double itemTotal = quantities[i] * prices[i];
                System.out.printf("%d x %s - %.2f PHP\n", quantities[i], names[i], itemTotal);
                total += itemTotal;
            }
        }
        System.out.printf("Total: %.2f PHP\n", total);
    }
}