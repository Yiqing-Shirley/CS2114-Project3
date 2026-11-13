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
    
}
