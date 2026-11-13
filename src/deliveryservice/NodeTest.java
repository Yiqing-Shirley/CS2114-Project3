package deliveryservice;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NodeTest
{

    //~ Fields ................................................................
    private Node<String> node;
    private Node<String> nextNode;


    //~ Constructors ..........................................................
    @BeforeEach
    public void setUp() 
    {
        nextNode = new Node<>("Second");
        node = new Node<>("First", nextNode);
    }
    //~Public  Methods ........................................................
    
    @Test
    public void testConstructor() {
        assertEquals("First", node.getData());
        assertEquals(nextNode, node.getNext());
    }

    @Test
    public void testConstructorWithoutNext() 
    {
        Node<String> newNode = new Node<>("First");

        assertEquals("First", newNode.getData());
        assertNull(newNode.getNext());
    }

    @Test
    public void testGetData() 
    {
        assertEquals("First", node.getData());
    }

    @Test
    public void testGetNext() 
    {
        assertEquals(nextNode, node.getNext());
    }

    @Test
    public void testSetNext() 
    {
        Node<String> thirdNode = new Node<>("Third");

        node.setNext(thirdNode);

        assertEquals(thirdNode, node.getNext());
    }
}
