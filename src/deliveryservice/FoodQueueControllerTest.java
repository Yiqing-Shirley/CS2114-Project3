package deliveryservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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

        /**
     * A newly submitted order reports a pending status.
     */
    @Test
    public void testGetOrderStatusPendingForNewOrder()
    {
        Order order = controller.submitOrder("Pancakes", "Breakfast", false);
        assertEquals("pending", controller.getOrderStatus(order.getOrderNumber()));
    }

    /**
     * getOrderStatus returns null when no order has the given number.
     */
    @Test
    public void testGetOrderStatusMissingOrderReturnsNull()
    {
        assertNull(controller.getOrderStatus(999));
    }

    /**
     * processNextOrder returns null when the queue is empty.
     */
    @Test
    public void testProcessNextOrderEmptyQueueReturnsNull()
    {
        assertNull(controller.processNextOrder());
    }

    /**
     * processNextOrder completes the oldest order and removes it
     * from the pending list.
     */
    @Test
    public void testProcessNextOrderCompletesFirstInLine()
    {
        Order first = controller.submitOrder("Pancakes", "Breakfast", false);
        controller.submitOrder("Smoothie", "Drinks", false);

        Order processed = controller.processNextOrder();
        assertEquals(first.getOrderNumber(), processed.getOrderNumber());
        assertEquals(1, controller.viewPendingOrders().size());
        assertEquals("Smoothie", controller.viewPendingOrders().get(0).getFoodName());
    }

    /**
     * After processNextOrder, the processed order reports a completed status.
     */
    @Test
    public void testProcessNextOrderSetsCompletedStatus()
    {
        Order first = controller.submitOrder("Pancakes", "Breakfast", false);
        controller.processNextOrder();
        assertEquals("completed", controller.getOrderStatus(first.getOrderNumber()));
    }
}