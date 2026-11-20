package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
/**
 * // -------------------------------------------------------------------------
/**
 * food queue model test class
 * 
 *  @author annak
 *  @version Nov 13, 2026
 */
class FoodQueueModelTest
{

    
    //~ Fields ................................................................
    private FoodQueueModel model;
    private Order order1;
    private Order order2;
    private Order order3;
    //~ Constructors ..........................................................
    /**
    * set up method
    */
    @BeforeEach
    public void setUp() 
    {
        model = new FoodQueueModel();

        order1 = model.addOrder("Burger", "lunch", false);
        order2 = model.addOrder("Coffee", "drinks", false);
        order3 = model.addOrder("Cake", "dessert", false);
    }
    //~Public  Methods ........................................................
   /**
    * tests constructor
    */
    @Test
    public void testConstructor() 
    {
        FoodQueueModel emptyModel = new FoodQueueModel();

        assertEquals(0, emptyModel.getWaitingOrderCount());
    }

    /**
     * tests add order
     */
    @Test
    public void testAddOrder() 
    {
        assertEquals(3, model.getWaitingOrderCount());
        assertEquals(1, order1.getOrderNumber());
        assertEquals(2, order2.getOrderNumber());
        assertEquals(3, order3.getOrderNumber());
        assertEquals("PENDING", order1.getStatus());
    }

    /**
     * tests illegal arg exception of add order food name param
     */
    @Test
    public void testAddOrderInvalidFoodName() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            model.addOrder("", "lunch", false);
        });
    }

    /**
     * tests illegal arg exception of add order category param
     */
    @Test
    public void testAddOrderInvalidCategory() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            model.addOrder("Pizza", "snack", false);
        });
    }

    /**
     * tests get pending orders
     */
    @Test
    public void testGetPendingOrders() 
    {
        ArrayList<Order> pendingOrders = model.getPendingOrders();

        assertEquals(3, pendingOrders.size());
        assertEquals(order1, pendingOrders.get(0));
        assertEquals(order2, pendingOrders.get(1));
        assertEquals(order3, pendingOrders.get(2));
    }

    /**
     * tests start next order
     */
    @Test
    public void testStartNextOrder() 
    {
        model.startNextOrder();

        assertEquals("IN_PROGRESS", order1.getStatus());
        assertEquals("PENDING", order2.getStatus());
    }

    /**
     * tests complete next order 
     */
    @Test
    public void testCompleteNextOrder()
    {
        model.startNextOrder();

        Order completedOrder = model.completeNextOrder();

        assertEquals(order1, completedOrder);
        assertEquals("COMPLETED", completedOrder.getStatus());
        assertEquals(2, model.getWaitingOrderCount());
    }

    /**
     * tests track order 
     */
    @Test
    public void testTrackOrder() 
    {
        assertEquals(order2, model.trackOrder(2));
    }

    /**
     * tests track order bad input
     */
    @Test
    public void testTrackOrderNotFound() 
    {
        assertNull(model.trackOrder(99));
    }

    /**
     * tests get order position
     */
    @Test
    public void testGetOrderPosition() 
    {
        assertEquals(1, model.getOrderPosition(1));
        assertEquals(2, model.getOrderPosition(2));
        assertEquals(3, model.getOrderPosition(3));
    }

    /**
     * tests get orders ahead
     */
    @Test
    public void testGetOrdersAhead() 
    {
        assertEquals(0, model.getOrdersAhead(1));
        assertEquals(1, model.getOrdersAhead(2));
        assertEquals(2, model.getOrdersAhead(3));
    }

    /**
     * tests get waiting order count
     */
    @Test
    public void testGetWaitingOrderCount() 
    {
        assertEquals(3, model.getWaitingOrderCount());

        model.startNextOrder();
        model.completeNextOrder();

        assertEquals(2, model.getWaitingOrderCount());
    }

    /**
     * tests get order category
     */
    @Test
    public void testGetOrderCategory() 
    {
        assertEquals("lunch", model.getOrderCategory(1));
        assertEquals("drinks", model.getOrderCategory(2));
        assertEquals("dessert", model.getOrderCategory(3));
    }

    /**
     * tests tracking on completed order
     */
    @Test
    public void testCompletedOrderCanStillBeTracked() {

        model.startNextOrder();

        model.completeNextOrder();

        Order trackedOrder = model.trackOrder(1);

        assertNotNull(trackedOrder);
        assertEquals("COMPLETED", trackedOrder.getStatus());

    }

    /**
     * tests queue position on completed order
     */
    @Test
    public void testCompletedOrderHasNoQueuePosition() 
    {
        model.startNextOrder();
        model.completeNextOrder();

        assertEquals(0, model.getOrderPosition(1));
        assertEquals(0, model.getOrdersAhead(1));
    }

    /**
     * tests when there is a dupe order
     */
    @Test
    public void testIsDuplicateOrder() {
        assertTrue(model.isDuplicateOrder("Burger", "lunch"));
    }

    /**
     * tests not dupe order
     */
    @Test
    public void testIsNotDuplicateOrder() {
        assertFalse(model.isDuplicateOrder("Pizza", "dinner"));
    }

    /**
     * tests dupe with caps
     */
    @Test
    public void testDuplicateOrderDifferentCapitalization() {
        assertTrue(model.isDuplicateOrder("burger", "LUNCH"));
    }

    /**
     * tests if cancelled
     */
    @Test
    public void testDuplicateOrderCancelled() {
        int originalSize = model.getWaitingOrderCount();

        Order result = model.addOrder("Burger", "lunch", false);

        assertNull(result);
        assertEquals(originalSize, model.getWaitingOrderCount());
    }

    /**
     * tests confirmed order
     */
    @Test
    public void testDuplicateOrderConfirmed() {
        int originalSize = model.getWaitingOrderCount();

        Order result = model.addOrder("Burger", "lunch", true);

        assertNotNull(result);
        assertEquals(originalSize + 1, model.getWaitingOrderCount());
        assertEquals(4, result.getOrderNumber());
    }

    /**
     * tests dupe order after original 
     */
    @Test
    public void testDuplicateOrderAfterOriginalIsCompleted() {
        model.startNextOrder();
        model.completeNextOrder();

        assertFalse(model.isDuplicateOrder("Burger", "lunch"));

        Order result = model.addOrder("Burger", "lunch", false);

        assertNotNull(result);
        assertEquals(4, result.getOrderNumber());
    }
    
    /**
     * tests order status changes
     */
    @Test
    public void testOrderStatusChanges() 
    {

        assertEquals("PENDING", order1.getStatus());

        model.startNextOrder();

        assertEquals("IN_PROGRESS", order1.getStatus());

        model.completeNextOrder();

        assertEquals("COMPLETED", order1.getStatus());
    }

    /**
     * tests order of completion
     */
    @Test
    public void testCannotCompleteOrderBeforeStarting() 
    {

        assertThrows(IllegalStateException.class, () ->
            model.completeNextOrder()
        );
    }

    /**
     * tests starting twice
     */
    @Test
    public void testCannotStartOrderTwice() 
    {

        model.startNextOrder();

        assertThrows(IllegalStateException.class, () ->
            model.startNextOrder()
        );
    }

    /**
     * tests completed order position
     */
    @Test
    public void testCompletedOrderPosition() 
    {

        model.startNextOrder();
        model.completeNextOrder();

        assertEquals(0, model.getOrderPosition(1));
        assertEquals(0, model.getOrdersAhead(1));
    }

    /**
     * tests searchability of order position
     */
    @Test
    public void testCompletedOrderStillSearchable() 
    {

        model.startNextOrder();
        model.completeNextOrder();

        Order completedOrder = model.trackOrder(1);

        assertNotNull(completedOrder);
        assertEquals("COMPLETED", completedOrder.getStatus());
    }
    
    /**
     * tests first order wait time
     */
    @Test
    public void testEstimatedWaitTimeFirstOrder() {
        assertEquals(15, model.getEstimatedWaitTime(1));
    }

    /**
     * lunch wait time
     */
    @Test
    public void testEstimatedWaitTimeSecondOrder() {
        assertEquals(20, model.getEstimatedWaitTime(2));
    }

    /**
     * dinner wait time
     */
    @Test
    public void testEstimatedWaitTimeThirdOrder() {
        assertEquals(25, model.getEstimatedWaitTime(3));
    }

    /**
     * tests wait time on completed order
     */
    @Test
    public void testEstimatedWaitTimeForCompletedOrder() {
        model.startNextOrder();
        model.completeNextOrder();

        assertEquals(-1, model.getEstimatedWaitTime(1));
    }

    /**
     * invalid order
     */
    @Test
    public void testEstimatedWaitTimeForInvalidOrder() {
        assertEquals(-1, model.getEstimatedWaitTime(99));
    }
}
