package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class OrderTest
{
    //~ Fields ................................................................
    private Order order;


    //~ Constructors .........................................................
    @BeforeEach
    public void setUp() 
    {
        order = new Order(1, "Burger", "lunch");
    }
    //~Public  Methods ........................................................
    @Test
    public void testConstructor() {
        assertEquals(1, order.getOrderNumber());
        assertEquals("Burger", order.getFoodName());
        assertEquals("lunch", order.getCategory());
        assertEquals("PENDING", order.getStatus());
    }

    @Test
    public void testGetOrderNumber() {
        assertEquals(1, order.getOrderNumber());
    }

    @Test
    public void testGetFoodName() {
        assertEquals("Burger", order.getFoodName());
    }

    @Test
    public void testGetCategory() {
        assertEquals("lunch", order.getCategory());
    }

    @Test
    public void testGetStatus() {
        assertEquals("PENDING", order.getStatus());
    }

    @Test
    public void testSetStatus() {
        order.setStatus("IN_PROGRESS");
        assertEquals("IN_PROGRESS", order.getStatus());

        order.setStatus("COMPLETED");
        assertEquals("COMPLETED", order.getStatus());
    }
}
