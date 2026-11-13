package deliveryservice;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * EmployeeView handles the restaurant staff interface for viewing pending 
 * orders and marking the next order as completed.
 */
public class EmployeeView {
    private FoodQueueController controller;
    private Scanner scanner;

    public EmployeeView(FoodQueueController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the employee menu loop.
     */
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. View Pending Orders");
            System.out.println("2. Process Next Order (Mark Completed)");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    displayPendingOrders();
                    break;
                case "2":
                    processNextOrderPrompt();
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
     * Displays all pending customer orders and highlights the next order in the FIFO queue[cite: 4, 7].
     */
    private void displayPendingOrders() {
        ArrayList<Order> pendingOrders = controller.viewPendingOrders();
        int waitingCount = controller.getWaitingOrderCount();

        System.out.println("\n--- Pending Customer Orders ---");
        System.out.println("Total Orders Currently Waiting: " + waitingCount);

        if (pendingOrders == null || pendingOrders.isEmpty()) {
            System.out.println("No pending orders in the queue.");
            return;
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-18s %-10s %-20s %-15s%n", "Queue Status", "Order #", "Food Name", "Category");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < pendingOrders.size(); i++) {
            Order order = pendingOrders.get(i);
            String queueIndicator = (i == 0) ? "[NEXT TO MAKE]" : "Waiting";
            
            System.out.printf("%-18s %-10d %-20s %-15s%n",
                queueIndicator,
                order.getOrderNumber(),
                order.getFoodName(),
                order.getCategory()
            );
        }
        System.out.println("-------------------------------------------------------------");
    }

    /**
     * Requests the controller to process the next order in line[cite: 4, 7, 8].
     */
    private void processNextOrderPrompt() {
        try {
            Order completedOrder = controller.processNextOrder();
            if (completedOrder != null) {
                System.out.println("Success! Order #" + completedOrder.getOrderNumber() 
                    + " (" + completedOrder.getFoodName() + ") has been marked as completed.");
            } else {
                System.out.println("The queue is empty. No orders available to process.");
            }
        } catch (Exception e) {
            System.out.println("Error processing order: " + e.getMessage());
        }
    }
}