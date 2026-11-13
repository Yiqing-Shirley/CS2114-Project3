package deliveryservice;
/**
 * // -------------------------------------------------------------------------
/**
 *  Node class
 *  @param <T>
 * 
 *  @author annak
 *  @version Nov 12, 2026
 */
public class Node<T>
{
    //~ Fields ................................................................
    private T data;
    private Node<T> next;
    
    //~ Constructors ..........................................................
    /**
    * @param data 
    */
    public Node(T data) 
    {
        this(data, null);
    }
    /**
     * @param data
     * @param next 
     * 
     */
    public Node(T data, Node<T> next) 
    {
        this.data = data;
        this.next = next;
    }

    //~Public  Methods ........................................................
    /**
     * gets data
     * @return node data
     */
    public T getData() 
    {
        return data;
    }

    /**
     * gets next
     * @return node
     */
    public Node<T> getNext() 
    {
        return next;
    }

    /**
     * sets next node
     * @param next 
     */
    public void setNext(Node<T> next) 
    {
        this.next = next;
    }
}
