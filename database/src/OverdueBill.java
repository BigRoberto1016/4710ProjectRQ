import java.sql.Date; // Make sure to import java.sql.Date for SQL date compatibility

public class OverdueBill {
    private int billID;
    private String orderID;
    private int clientID;
    private double price;
    private Date dateGenerated;
    private Date datePaid;
    private String status;

    // Constructor
    public OverdueBill(int billID, String orderID, int clientID, double price, Date dateGenerated, Date datePaid, String status) {
        this.billID = billID;
        this.orderID = orderID;
        this.clientID = clientID;
        this.price = price;
        this.dateGenerated = dateGenerated;
        this.datePaid = datePaid;
        this.status = status;
    }

    // Getters
    public int getBillID() {
        return billID;
    }

    public String getOrderID() {
        return orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public double getPrice() {
        return price;
    }

    public Date getDateGenerated() {
        return dateGenerated;
    }

    public Date getDatePaid() {
        return datePaid;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setBillID(int billID) {
        this.billID = billID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDateGenerated(Date dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "OverdueBill{" +
               "billID=" + billID +
               ", orderID='" + orderID + '\'' +
               ", clientID=" + clientID +
               ", price=" + price +
               ", dateGenerated=" + dateGenerated +
               ", datePaid=" + datePaid +
               ", status='" + status + '\'' +
               '}';
    }
}
