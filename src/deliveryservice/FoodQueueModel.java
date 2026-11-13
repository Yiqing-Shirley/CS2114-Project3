package deliveryservice;
import java.util.ArrayList;

public class FoodQueueModel
{
    //~ Fields ................................................................
    private OrderQueue orderQueue;
    private ArrayList<Order> completedOrders;
    private int nextOrderNumber;
    //~ Constructors ..........................................................
    public FoodQueueModel() 
    {
        orderQueue = new OrderQueue();
        completedOrders = new ArrayList<>();
        nextOrderNumber = 1;
    }
    //~Public  Methods ........................................................
    public Order addOrder(String foodName, String category) {
        if (foodName == null || foodName.trim().isEmpty()) {
            throw new IllegalArgumentException("Food name is required.");
        }

        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category is required.");
        }

        category = category.trim().toLowerCase();

        if (!category.equals("drinks") &&
            !category.equals("breakfast") &&
            !category.equals("lunch") &&
            !category.equals("dinner") &&
            !category.equals("dessert")) {

            throw new IllegalArgumentException("Invalid food category.");
        }

        Order newOrder = new Order(nextOrderNumber, foodName.trim(), category);
        orderQueue.enqueue(newOrder);
        nextOrderNumber++;

        return newOrder;
    }
    
    public ArrayList<Order> getPendingOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        OrderQueue tempQueue = new OrderQueue();

        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.dequeue();
            orders.add(order);
            tempQueue.enqueue(order);
        }

        while (!tempQueue.isEmpty()) {
            orderQueue.enqueue(tempQueue.dequeue());
        }

        return orders;
    }
    
    public Order completeNextOrder() 
    {
        if (orderQueue.isEmpty()) {
            throw new IllegalStateException("No pending orders.");
        }

        Order completedOrder = orderQueue.dequeue();
        completedOrder.setStatus("COMPLETED");
        completedOrders.add(completedOrder);

        return completedOrder;
    }
    
    public Order startNextOrder() 
    {
        if (orderQueue.isEmpty()) {
            throw new IllegalStateException("No pending orders.");
        }

        Order order = orderQueue.peek();
        order.setStatus("IN_PROGRESS");

        return order;
    }
    
    public Order trackOrder(int orderNumber) 
    {
        for (Order order : getPendingOrders()) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }

        for (Order order : completedOrders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }

        return null;
    }
    
    public int getOrderPosition(int orderNumber) 
    {
        ArrayList<Order> orders = getPendingOrders();

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == orderNumber) {
                return i + 1;
            }
        }

        return -1;
    }
    
    public int getOrdersAhead(int orderNumber) 
    {
        int position = getOrderPosition(orderNumber);

        if (position == -1) {
            return -1;
        }

        return position - 1;
    }
    
    public int getWaitingOrderCount() 
    {
        return orderQueue.getSize();
    }
    
    public String getOrderCategory(int orderNumber) 
    {
        Order order = trackOrder(orderNumber);

        if (order == null) {
            return null;
        }

        return order.getCategory();
    }
}
