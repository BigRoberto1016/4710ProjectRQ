public class Quote 
{
	protected String quoteID;
	protected String initialPrice;
	protected String scheduleStart;
	protected String scheduleEnd;
	protected String status;
	protected String clientID;
	protected String contractorID;
	
	//Constructors
	public Quote() {}
	
	public Quote(String quoteID) 
	{
		this.quoteID = quoteID;
	}
	
	public Quote(String quoteID, String initialPrice, String scheduleStart,String scheduleEnd, String status, String clientID, String contractorID)
	{
		this(initialPrice, scheduleStart, scheduleEnd, status, clientID, contractorID);
		this.quoteID = quoteID;
		
	}
	
	public Quote(String initialPrice, String scheduleStart,String scheduleEnd, String status, String clientID, String contractorID)
	{
		this.initialPrice = initialPrice;
		this.scheduleStart = scheduleStart;
		this.scheduleEnd = scheduleEnd;
		this.status = status;
		this.clientID = clientID;
		this.contractorID = contractorID;
	}
	
	//Getter and setter methods
	public String getQuoteID() {
		return quoteID;
	}
	
	public void setQuoteID(String quoteID)
	{
		this.quoteID = quoteID;
	}
	
	public String getInitialPrice()
	{
		return initialPrice;
	}
	
	public void setInitialPrice(String initialPrice)
	{
		this.initialPrice = initialPrice;
	}
	
	public String getScheduleStart()
	{
		return scheduleStart;
	}
	
	public void setScheduleStart(String scheduleStart)
	{
		this.scheduleStart = scheduleStart;
	}
	
	public String getScheduleEnd()
	{
		return scheduleEnd;
	}
	
	public void setScheduleEnd(String scheduleEnd)
	{
		this.scheduleEnd = scheduleEnd;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getClientID()
	{
		return clientID;
	}
	
	public void setClientID(String clientID)
	{
		this.clientID = clientID;
	}
	
	public String getContractorID()
	{
		return contractorID;
	}
	
	public void setContractorID(String contractorID)
	{
		this.contractorID = contractorID;
	}
}