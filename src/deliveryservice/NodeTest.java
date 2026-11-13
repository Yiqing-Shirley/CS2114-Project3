package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
// -------------------------------------------------------------------------
/**
 *  test class for node class
 *  
 *  @author annak
 *  @version Nov 12, 2026
 */
class NodeTest
{

    //~ Fields ................................................................
    private Node<String> node;
    private Node<String> nextNode;


    //~ Constructors ..........................................................
    /**
     * tests setup
     */
    @BeforeEach
    public void setUp() 
    {
        nextNode = new Node<>("Second");
        node = new Node<>("First", nextNode);
    }
    //~Public  Methods ........................................................
    
    /**
     * tests constructor
     */
    @Test
    public void testConstructor() {
        assertEquals("First", node.getData());
        assertEquals(nextNode, node.getNext());
    }

    /**
     * tests constructor 
     */
    @Test
    public void testConstructorWithoutNext() 
    {
        Node<String> newNode = new Node<>("First");

        assertEquals("First", newNode.getData());
        assertNull(newNode.getNext());
    }

    /**
     * tests getter for node data
     */
    @Test
    public void testGetData() 
    {
        assertEquals("First", node.getData());
    }

    /**
     * tests getter for next node
     */
    @Test
    public void testGetNext() 
    {
        assertEquals(nextNode, node.getNext());
    }

    /**
     * tests set next node
     */
    @Test
    public void testSetNext() 
    {
        Node<String> thirdNode = new Node<>("Third");

        node.setNext(thirdNode);

        assertEquals(thirdNode, node.getNext());
    }
}
