package deliveryservice;

import java.util.ArrayList;

/**
 * FoodQueueController connects the customer and employee views to the
 * FoodQueueModel. It contains no queue logic of its own.
 *
 * @author Aleysa Sweeney
 * @version Nov 13, 2026
 */
public class FoodQueueController
{
    // ~ Fields ................................................................
    private FoodQueueModel model;

    // ~ Constructors ..........................................................
    /**
     * @param model
     *            the model that stores and manages orders
     */
    public FoodQueueController(FoodQueueModel model)
    {
        this.model = model;
    }


    public Order submitOrder(String foodName, String category)
    {
        return model.addOrder(foodName, category);
    }


    public String getOrderStatus(int orderNumber)
    {
        Order order = model.trackOrder(orderNumber);
        if (order == null)
        {
            return null;
        }
        return order.getStatus();
    }


    public String getOrderCategory(int orderNumber)
    {
        return model.getOrderCategory(orderNumber);
    }


    public int getOrderPosition(int orderNumber)
    {
        return model.getOrderPosition(orderNumber);
    }


    public int getOrdersAhead(int orderNumber)
    {
        return model.getOrdersAhead(orderNumber);
    }


    public int getWaitingOrderCount()
    {
        return model.getWaitingOrderCount();
    }


    public ArrayList<Order> viewPendingOrders()
    {
        return model.getPendingOrders();
    }


    public Order processNextOrder()
    {
        if (model.getWaitingOrderCount() == 0)
        {
            return null;
        }
        return model.completeNextOrder();
    }

}
