package deliveryservice;
/**
 * // -------------------------------------------------------------------------
/**
 *  OrderQueue class
 * 
 *  @author annak
 *  @version Nov 12, 2026
 */
public class OrderQueue
{
    //~ Fields ................................................................
    private Node<Order> front;
    private Node<Order> back;
    private int size;
    //~ Constructors ..........................................................
    /**
     * constructor
     */
    public OrderQueue() 
    {
        front = null;
        back = null;
        size = 0;
    }
    //~Public  Methods ........................................................
    /**
     * @param order 
     * enqueues the order
     */
    public void enqueue(Order order) 
    {
        Node<Order> newNode = new Node<>(order);

        if (isEmpty()) {
            front = newNode;
        } else {
            back.setNext(newNode);
        }

        back = newNode;
        size++;
    }
    
    /**
     * dequeues the order
     * @return order that was dequeued
     */
    public Order dequeue() 
    {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        Order removedOrder = front.getData();
        front = front.getNext();
        size--;

        if (isEmpty()) {
            back = null;
        }

        return removedOrder;
    }
    
    /**
     * returns order on top
     * @return order that was peeked
     */
    public Order peek() 
    {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        return front.getData();
    }
    
    /**
     * returns true if empty
     * @return if empty
     */
    public boolean isEmpty() 
    {
        return size == 0;
    }
    
    /**
     * returns size of order queue
     * @return size
     */
    public int getSize() 
    {
        return size;
    }
}
