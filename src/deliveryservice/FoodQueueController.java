package deliveryservice;

import java.util.ArrayList;

/**
 * FoodQueueController connects the customer and employee views to the
 * FoodQueueModel. It contains no queue logic of its own.
 *
 *  @author Aleysa Sweeney
 *  @version Nov 13, 2026
 */
public class FoodQueueController
{
    //~ Fields ................................................................
    private FoodQueueModel model;

    //~ Constructors ..........................................................
    /**
     * @param model the model that stores and manages orders
     */
    public FoodQueueController(FoodQueueModel model)
    {
        this.model = model;
    }

    //~ Public Methods ........................................................
    /**
     * submits a new order. Validation errors are thrown as
     * IllegalArgumentException by the model.
     * @param foodName name of the food
     * @param category food category
     * @return the newly created order
     */
    public Order submitOrder(String foodName, String category)
    {
        return model.addOrder(foodName, category);
    }

    /**
     * @return orders waiting in the queue, in FIFO order
     */
    public ArrayList<Order> viewPendingOrders()
    {
        return model.getPendingOrders();
    }
    
    /**
     * marks the next order in line as completed
     * @return the completed order, or null if the queue is empty
     */
    public Order processNextOrder()
    {
        if (model.getWaitingOrderCount() == 0)
        {
            return null;
        }
        return model.completeNextOrder();
    }


    /**
     * @param orderNumber the order to look up
     * @return the order's status, or null if no order has that number
     */
    public String getOrderStatus(int orderNumber)
    {
        Order order = model.trackOrder(orderNumber);
        if (order == null)
        {
            return null;
        }
        return order.getStatus();
    }

    /**
     * @param orderNumber the order to look up
     * @return the order's category, or null if not found
     */
    public String getOrderCategory(int orderNumber)
    {
        return model.getOrderCategory(orderNumber);
    }


    /**
     * @param orderNumber the order to look up
     * @return 1-based queue position, or -1 if not in the queue
     */
    public int getOrderPosition(int orderNumber)
    {
        return model.getOrderPosition(orderNumber);
    }

    /**
     * @param orderNumber the order to look up
     * @return number of orders ahead, or -1 if not in the queue
     */
    public int getOrdersAhead(int orderNumber)
    {
        return model.getOrdersAhead(orderNumber);
    }

    /**
     * @return number of orders waiting in the queue
     */
    public int getWaitingOrderCount()
    {
        return model.getWaitingOrderCount();
    }
}

