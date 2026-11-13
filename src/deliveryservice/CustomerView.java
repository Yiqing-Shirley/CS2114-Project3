package deliveryservice;
import foodtracker.FoodQueueController;
import foodtracker.Order;
import java.util.Scanner;

/**
 * CustomerView handles the customer interface for submitting orders 
 * and checking order tracking statuses.
 */
public class CustomerView {
    private FoodQueueController controller;
    private Scanner scanner;

    public CustomerView(FoodQueueController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the customer menu loop.
     */
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. Submit Food Order");
            System.out.println("2. Track Order Status");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    submitOrderPrompt();
                    break;
                case "2":
                    trackOrderPrompt();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Prompts the customer for food name and category and submits the order[cite: 4, 6].
     */
    private void submitOrderPrompt() {
        System.out.print("Enter food name: ");
        String foodName = scanner.nextLine().trim();
        
        System.out.print("Enter food category (e.g., Drinks, Breakfast, Lunch, Dinner, Dessert): ");
        String category = scanner.nextLine().trim();

        try {
            // Sends request to controller/model[cite: 6, 8]
            Order order = controller.submitOrder(foodName, category);
            if (order != null) {
                System.out.println("Order successfully submitted!");
                System.out.println("Your Order Number: " + order.getOrderNumber());
            }
        } catch (IllegalArgumentException e) {
            // Handles missing fields or validation errors
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please complete the required fields.");
        }
    }

    /**
     * Prompts the customer to enter an order number to track progress and queue details[cite: 4, 8].
     */
    private void trackOrderPrompt() {
        System.out.print("Enter your order number: ");
        String input = scanner.nextLine().trim();

        // 1. Check if the order number field is blank[cite: 2, 8]
        if (input.isEmpty()) {
            System.out.println("Error: Order number cannot be blank. Please enter a valid order number.");
            return;
        }

        try {
            // Convert input to integer; catches formatting/range errors safely[cite: 8]
            int orderNumber = Integer.parseInt(input);

            // Check if the order exists in the system[cite: 2, 8]
            String status = controller.getOrderStatus(orderNumber);
            if (status == null) {
                System.out.println("Order not found. Please try again.");
                return;
            }

            // Retrieve tracking details[cite: 4, 8]
            String category = controller.getOrderCategory(orderNumber);
            int position = controller.getOrderPosition(orderNumber);
            int ordersAhead = controller.getOrdersAhead(orderNumber);
            int totalWaiting = controller.getWaitingOrderCount();

            // Display results to the customer[cite: 2, 4]
            System.out.println("\n--- Order Status Details ---");
            System.out.println("Order Number: " + orderNumber);
            System.out.println("Category: " + category);
            System.out.println("Status: " + status);
            System.out.println("Current Position in Queue: " + position);
            System.out.println("Orders Ahead of You: " + ordersAhead);
            System.out.println("Total Waiting Orders: " + totalWaiting);

        } catch (NumberFormatException e) {
            // Handles letters, special characters, or numbers outside int range[cite: 2, 8]
            System.out.println("Error: Invalid order number format. Please enter a valid integer.");
        }
    }
}