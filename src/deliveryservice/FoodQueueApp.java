
package deliveryservice;

import java.util.Scanner;

public class FoodQueueApp {

    public static void main(String[] args) {
        FoodQueueModel model = new FoodQueueModel();
        FoodQueueController controller =
            new FoodQueueController(model);

        CustomerView customerView =
            new CustomerView(controller);
        EmployeeView employeeView =
            new EmployeeView(controller);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- FoodQueue Manager ---");
            System.out.println("1. Customer");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    customerView.start();
                    break;
                case "2":
                    employeeView.start();
                    break;
                case "3":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println(
                        "Invalid option. Please try again.");
            }
        }
    }
}
