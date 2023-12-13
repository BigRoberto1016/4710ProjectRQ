public class Bills
{
	protected String billID;
	protected String orderID;
	protected String clientID;
	protected String price;
	protected String dateGenerated;
	protected String datePaid;
	protected String status;
	
	
	//Constructors
	public Bills() {}
	
	public Bills(String billID)
	{
		this.billID = billID;
	}
	
	public Bills(String billID, String orderID, String clientID, String price, String dateGenerated, String datePaid, String status)
	{
		this(orderID, clientID, price, dateGenerated, datePaid, status);
		this.billID = billID;
	}
	
	public Bills(String orderID, String clientID, String price, String dateGenerated, String datePaid, String status)
	{
		this.orderID = orderID;
		this.clientID = clientID;
		this.price = price;
		this.dateGenerated = dateGenerated;
		this.datePaid = datePaid;
		this.status = status;
	}
	
	 // Getters and Setters
    public String getBillID() 
    {
        return billID;
    }

    public void setBillID(String billID) 
    {
        this.billID = billID;
    }

    public String getOrderID() 
    {
        return orderID;
    }

    public void setOrderID(String orderID) 
    {
        this.orderID = orderID;
    }
    
    public String getClientID()
    {
    	return clientID;
    }
    
    public void setClientID(String clientID)
    {
    	this.clientID = clientID;
    }

    public String getPrice() 
    {
        return price;
    }

    public void setPrice(String price) 
    {
        this.price = price;
    }

    public String getDateGenerated() 
    {
        return dateGenerated;
    }

    public void setDateGenerated(String dateGenerated) 
    {
        this.dateGenerated = dateGenerated;
    }

    public String getDatePaid() 
    {
        return datePaid;
    }

    public void setDatePaid(String datePaid) 
    {
        this.datePaid = datePaid;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }
}