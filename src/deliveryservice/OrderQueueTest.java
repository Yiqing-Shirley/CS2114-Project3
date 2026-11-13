package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderQueueTest
{

   
    //~ Fields ................................................................
    
    private OrderQueue queue;
    private Order order1;
    private Order order2;
    private Order order3;

    //~ Constructors ..........................................................
    
    @BeforeEach
    public void setUp() 
    {
        queue = new OrderQueue();

        order1 = new Order(1, "Burger", "lunch");
        order2 = new Order(2, "Coffee", "drinks");
        order3 = new Order(3, "Cake", "dessert");
    }
    
    //~Public  Methods ........................................................
    
    @Test
    public void testConstructor() 
    {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }

    @Test
    public void testEnqueue() 
    {
        queue.enqueue(order1);

        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getSize());
        assertEquals(order1, queue.peek());
    }

    @Test
    public void testDequeue() 
    {
        queue.enqueue(order1);
        queue.enqueue(order2);

        Order removedOrder = queue.dequeue();

        assertEquals(order1, removedOrder);
        assertEquals(1, queue.getSize());
        assertEquals(order2, queue.peek());
    }

    @Test
    public void testDequeueLastOrder() 
    {
        queue.enqueue(order1);

        Order removedOrder = queue.dequeue();

        assertEquals(order1, removedOrder);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.getSize());
    }

    @Test
    public void testPeek() 
    {
        queue.enqueue(order1);
        queue.enqueue(order2);

        assertEquals(order1, queue.peek());
        assertEquals(2, queue.getSize());
    }

    @Test
    public void testIsEmpty() 
    {
        assertTrue(queue.isEmpty());

        queue.enqueue(order1);

        assertFalse(queue.isEmpty());
    }

    @Test
    public void testGetSize() 
    {
        assertEquals(0, queue.getSize());

        queue.enqueue(order1);
        queue.enqueue(order2);
        queue.enqueue(order3);

        assertEquals(3, queue.getSize());

        queue.dequeue();

        assertEquals(2, queue.getSize());
    }

    @Test
    public void testDequeueEmptyQueue() 
    {
        assertThrows(IllegalStateException.class, () -> {
            queue.dequeue();
        });
    }

    @Test
    public void testPeekEmptyQueue() 
    {
        assertThrows(IllegalStateException.class, () -> {
            queue.peek();
        });
    }

    @Test
    public void testFIFOOrder() 
    {
        queue.enqueue(order1);
        queue.enqueue(order2);
        queue.enqueue(order3);

        assertEquals(order1, queue.dequeue());
        assertEquals(order2, queue.dequeue());
        assertEquals(order3, queue.dequeue());

        assertTrue(queue.isEmpty());
    }
    
}
