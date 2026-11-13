package deliveryservice;
public class OrderQueue
{
    //~ Fields ................................................................
    private Node<Order> front;
    private Node<Order> back;
    private int size;
    //~ Constructors ..........................................................
    public OrderQueue() 
    {
        front = null;
        back = null;
        size = 0;
    }
    //~Public  Methods ........................................................
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
    
    public Order peek() 
    {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }

        return front.getData();
    }
    
    public boolean isEmpty() 
    {
        return size == 0;
    }
    
    public int getSize() 
    {
        return size;
    }
}
