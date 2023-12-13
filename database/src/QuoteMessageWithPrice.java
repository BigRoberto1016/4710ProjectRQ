public class QuoteMessageWithPrice {
    private String quoteMessageID;
    private String userID;
    private String quoteID;
    private String msgTime;
    private String note;
    private double initialPrice;

    // Constructor
    public QuoteMessageWithPrice(String quoteMessageID, String userID, String quoteID, String msgTime, String note, double initialPrice) {
        this.quoteMessageID = quoteMessageID;
        this.userID = userID;
        this.quoteID = quoteID;
        this.msgTime = msgTime;
        this.note = note;
        this.initialPrice = initialPrice;
    }

    // Getters and setters
    public String getQuoteMessageID() {
        return quoteMessageID;
    }

    public void setQuoteMessageID(String quoteMessageID) {
        this.quoteMessageID = quoteMessageID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }
}
