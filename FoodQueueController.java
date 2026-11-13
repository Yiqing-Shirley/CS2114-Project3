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
    
}
