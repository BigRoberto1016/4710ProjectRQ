public class QuoteMessages
{
	protected String quoteMessageID;
	protected String userID;
	protected String quoteID;
	protected String msgTime;
	protected String note;
	
	//Constructors
	public QuoteMessages() {}
	
	public QuoteMessages(String quoteMessageID)
	{
		this.quoteMessageID = quoteMessageID;
	}
	
	public QuoteMessages(String quoteMessageID, String userID, String quoteID, String msgTime, String note)
	{
		this(userID, quoteID, msgTime, note);
		this.quoteMessageID = quoteMessageID;
	}
	
	public QuoteMessages(String userID, String quoteID, String msgTime, String note)
	{
		this.userID = userID;
		this.quoteID = quoteID;
		this.msgTime = msgTime;
		this.note = note;
	}
	
	// Getter and setter for quoteMessageID
    public String getQuoteMessageID() {
        return quoteMessageID;
    }

    public void setQuoteMessageID(String quoteMessageID) {
        this.quoteMessageID = quoteMessageID;
    }

    // Getter and setter for userID
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Getter and setter for quoteID
    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    // Getter and setter for msgTime
    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    // Getter and setter for note
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}