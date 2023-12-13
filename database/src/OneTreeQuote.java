import java.sql.Date; 

public class OneTreeQuote {
    private int quoteID;
    private double initialPrice;
    private Date scheduleStart;
    private Date scheduleEnd;
    private String status;

    // Constructor
    public OneTreeQuote(int quoteID, double initialPrice, Date scheduleStart, Date scheduleEnd, String status) {
        this.quoteID = quoteID;
        this.initialPrice = initialPrice;
        this.scheduleStart = scheduleStart;
        this.scheduleEnd = scheduleEnd;
        this.status = status;
    }

    // Getters and Setters
    public int getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(int quoteID) {
        this.quoteID = quoteID;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Date getScheduleStart() {
        return scheduleStart;
    }

    public void setScheduleStart(Date scheduleStart) {
        this.scheduleStart = scheduleStart;
    }

    public Date getScheduleEnd() {
        return scheduleEnd;
    }

    public void setScheduleEnd(Date scheduleEnd) {
        this.scheduleEnd = scheduleEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OneTreeQuote{" +
               "quoteID=" + quoteID +
               ", initialPrice=" + initialPrice +
               ", scheduleStart=" + scheduleStart +
               ", scheduleEnd=" + scheduleEnd +
               ", status='" + status + '\'' +
               '}';
    }
}
