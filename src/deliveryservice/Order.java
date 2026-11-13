package deliveryservice;
/**
 * // -------------------------------------------------------------------------
/**
 *  order class
 * 
 *  @author annak
 *  @version Nov 12, 2026
 */
public class Order
{
    //~ Fields ................................................................
    private int orderNumber;
    private String foodName;
    private String category;
    private String status;
    //~ Constructors ..........................................................
    /**
     * @param orderNumber 
     * @param foodName 
     * @param category 
    * 
    */
    public Order(int orderNumber, String foodName, String category) 
    {
        this.orderNumber = orderNumber;
        this.foodName = foodName;
        this.category = category;
        this.status = "PENDING";
    }
    //~Public  Methods ........................................................
    /**
     * returns order number 
     * @return order number
     */
    public int getOrderNumber() 
    {
        return orderNumber;
    }
    
    /**
     * @return food name
     */
    public String getFoodName() 
    {
        return foodName;
    }
    
    /**
     * @return order category 
     */
    public String getCategory() 
    {
        return category;
    }
    
    /**
     * @return order status
     */
    public String getStatus() 
    {
        return status;
    }
    
    /**
     * sets order status
     * @param status 
     */
    public void setStatus(String status) 
    {
        this.status = status;
    }
}
