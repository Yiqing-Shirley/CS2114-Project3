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

        order1 = model.addOrder("Burger", "lunch");
        order2 = model.addOrder("Coffee", "drinks");
        order3 = model.addOrder("Cake", "dessert");
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
            model.addOrder("", "lunch");
        });
    }

    /**
     * tests illegal arg exception of add order category param
     */
    @Test
    public void testAddOrderInvalidCategory() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            model.addOrder("Pizza", "snack");
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
    public void testGetOrdersAhead() {
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
    public void testCompletedOrderCanStillBeTracked()
    {
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
        model.completeNextOrder();

        assertEquals(-1, model.getOrderPosition(1));
        assertEquals(-1, model.getOrdersAhead(1));
    }
}
