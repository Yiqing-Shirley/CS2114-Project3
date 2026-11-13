package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
/**
 * // -------------------------------------------------------------------------
/**
 * 
 *  tests the Order class
 * 
 *  @author annak
 *  @version Nov 12, 2026
 */
class OrderTest
{
    //~ Fields ................................................................
    private Order order;


    // ----------------------------------------------------------
    /**
     * constructor 
     */
    //~ Constructors .........................................................
    @BeforeEach
    public void setUp() 
    {
        order = new Order(1, "Burger", "lunch");
    }
    //~Public  Methods ........................................................
    /**
     * tests constructor
     */
    @Test
    public void testConstructor() {
        assertEquals(1, order.getOrderNumber());
        assertEquals("Burger", order.getFoodName());
        assertEquals("lunch", order.getCategory());
        assertEquals("PENDING", order.getStatus());
    }

    /**
     * tests getter for ordernumber
     */
    @Test
    public void testGetOrderNumber() {
        assertEquals(1, order.getOrderNumber());
    }

    /**
     * tests getter for food name
     */
    @Test
    public void testGetFoodName() {
        assertEquals("Burger", order.getFoodName());
    }

    /**
     * tests getter for category
     */
    @Test
    public void testGetCategory() {
        assertEquals("lunch", order.getCategory());
    }

    /**
     * tests getter for status
     */
    @Test
    public void testGetStatus() {
        assertEquals("PENDING", order.getStatus());
    }

    /**
     * tests setter for status
     */
    @Test
    public void testSetStatus() {
        order.setStatus("IN_PROGRESS");
        assertEquals("IN_PROGRESS", order.getStatus());

        order.setStatus("COMPLETED");
        assertEquals("COMPLETED", order.getStatus());
    }
}
