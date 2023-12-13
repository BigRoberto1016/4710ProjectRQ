public class Order {
    protected String orderID;
    protected String quoteID;
    protected String clientID;
    protected String price;   
    protected String scheduleStart; 
    protected String scheduleEnd; 
    protected String status;

    // Constructors
    public Order() {
    }

    public Order(String orderID) {
        this.orderID = orderID;
    }

    public Order(String orderID, String quoteID, String clientID, String price, String scheduleStart, String scheduleEnd, String status) {
        this.orderID = orderID;
        this.quoteID = quoteID;
        this.clientID = clientID;
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.status = status;
    }

    public Order(String quoteID, String clientID, String price, String scheduleStart, String scheduleEnd, String status) {
        this.quoteID = quoteID;
        this.clientID = clientID;
        this.price = price;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.status = status;
    }

    // Getters and setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }
    
    public String getClientID() {
    	return clientID;
    }
    
    public void setClientID(String clientID) {
    	this.clientID = clientID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(String scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public String getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(String scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }
    
    public String getStatus() {
    	return status;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
}
