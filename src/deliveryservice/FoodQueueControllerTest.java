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

        /**
     * getOrderCategory returns the category the order was submitted with.
     */
    @Test
    public void testGetOrderCategoryReturnsCategory()
    {
        Order order = controller.submitOrder("Smoothie", "Drinks", false);
        assertEquals("Drinks", controller.getOrderCategory(order.getOrderNumber()));
    }

    /**
     * getOrderCategory returns null when no order has the given number.
     */
    @Test
    public void testGetOrderCategoryMissingOrderReturnsNull()
    {
        assertNull(controller.getOrderCategory(999));
    }

    /**
     * Queue positions are 1-based and follow submission order.
     */
    @Test
    public void testGetOrderPositionFollowsFifoOrder()
    {
        Order a = controller.submitOrder("Pancakes", "Breakfast", false);
        Order b = controller.submitOrder("Smoothie", "Drinks", false);
        Order c = controller.submitOrder("Waffles", "Breakfast", false);

        assertEquals(1, controller.getOrderPosition(a.getOrderNumber()));
        assertEquals(2, controller.getOrderPosition(b.getOrderNumber()));
        assertEquals(3, controller.getOrderPosition(c.getOrderNumber()));
    }

    /**
     * Completing the first order moves the next order up to position 1.
     */
    @Test
    public void testGetOrderPositionUpdatesAfterProcessing()
    {
        controller.submitOrder("Pancakes", "Breakfast", false);
        Order b = controller.submitOrder("Smoothie", "Drinks", false);

        controller.processNextOrder();
        assertEquals(1, controller.getOrderPosition(b.getOrderNumber()));
    }

    /**
     * getOrderPosition returns -1 when no order has the given number.
     */
    @Test
    public void testGetOrderPositionMissingOrderReturnsNegativeOne()
    {
        assertEquals(-1, controller.getOrderPosition(999));
    }

    /**
     * getOrdersAhead counts the pending orders in front of each order.
     */
    @Test
    public void testGetOrdersAheadCountsOrdersInFront()
    {
        Order a = controller.submitOrder("Pancakes", "Breakfast", false);
        Order b = controller.submitOrder("Smoothie", "Drinks", false);
        Order c = controller.submitOrder("Waffles", "Breakfast", false);

        assertEquals(0, controller.getOrdersAhead(a.getOrderNumber()));
        assertEquals(1, controller.getOrdersAhead(b.getOrderNumber()));
        assertEquals(2, controller.getOrdersAhead(c.getOrderNumber()));
    }

    /**
     * getOrdersAhead returns -1 when no order has the given number.
     */
    @Test
    public void testGetOrdersAheadMissingOrderReturnsNegativeOne()
    {
        assertEquals(-1, controller.getOrdersAhead(999));
    }

    /**
     * getWaitingOrderCount is zero for an empty queue.
     */
    @Test
    public void testGetWaitingOrderCountEmptyQueue()
    {
        assertEquals(0, controller.getWaitingOrderCount());
    }

    /**
     * getWaitingOrderCount tracks submissions and completions.
     */
    @Test
    public void testGetWaitingOrderCountAfterSubmitAndProcess()
    {
        controller.submitOrder("Pancakes", "Breakfast", false);
        controller.submitOrder("Smoothie", "Drinks", false);
        controller.submitOrder("Waffles", "Breakfast", false);
        assertEquals(3, controller.getWaitingOrderCount());

        controller.processNextOrder();
        assertEquals(2, controller.getWaitingOrderCount());
    }

        /**
     * A later order in the queue has a longer estimated wait than
     * an earlier one.
     */
    @Test
    public void testGetEstimatedWaitTimeIncreasesWithQueuePosition()
    {
        Order a = controller.submitOrder("Pancakes", "Breakfast", false);
        Order b = controller.submitOrder("Smoothie", "Drinks", false);

        assertTrue(controller.getEstimatedWaitTime(b.getOrderNumber())
            > controller.getEstimatedWaitTime(a.getOrderNumber()));
    }

    /**
     * A confirmed duplicate order is added to the pending list.
     */
    @Test
    public void testSubmitConfirmedDuplicateIsAdded()
    {
        controller.submitOrder("Pancakes", "Breakfast", false);
        controller.submitOrder("Pancakes", "Breakfast", true);

        assertEquals(2, controller.viewPendingOrders().size());
    }
    
}