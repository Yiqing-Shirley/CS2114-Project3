package deliveryservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for FoodQueueController. Uses a real FoodQueueModel
 * so the controller is tested against actual queue behavior.
 *
 *  @author Aleysa Sweeney
 *  @version Nov 19, 2026
 */
public class FoodQueueControllerTest
{
    //~ Fields ................................................................
    private FoodQueueModel model;
    private FoodQueueController controller;

    //~ Public Methods ........................................................
    /**
     * Creates a fresh model and controller before each test.
     */
    @Before
    public void setUp()
    {
        model = new FoodQueueModel();
        controller = new FoodQueueController(model);
    }

    /**
     * submitOrder returns the newly created order with its food name
     * and category.
     */
    @Test
    public void testSubmitOrderReturnsNewOrder()
    {
        Order order = controller.submitOrder("Pancakes", "Breakfast", false);
        assertNotNull(order);
        assertEquals("Pancakes", order.getFoodName());
        assertEquals("Breakfast", order.getCategory());
    }

    /**
     * Each submitted order gets added to the pending list in FIFO order.
     */
    @Test
    public void testSubmitOrderAddsToPendingInFifoOrder()
    {
        controller.submitOrder("Pancakes", "Breakfast", false);
        controller.submitOrder("Smoothie", "Drinks", false);

        ArrayList<Order> pending = controller.viewPendingOrders();
        assertEquals(2, pending.size());
        assertEquals("Pancakes", pending.get(0).getFoodName());
        assertEquals("Smoothie", pending.get(1).getFoodName());
    }

    /**
     * viewPendingOrders returns an empty list when nothing has been submitted.
     */
    @Test
    public void testViewPendingOrdersEmptyQueue()
    {
        ArrayList<Order> pending = controller.viewPendingOrders();
        assertNotNull(pending);
        assertTrue(pending.isEmpty());
    }
}