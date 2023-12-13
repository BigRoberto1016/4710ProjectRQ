import java.sql.Date; // Import for handling SQL Date

public class ClientStatistics {
    private int clientID;
    private String firstName;
    private String lastName;
    private double totalDueAmount;
    private double totalPaidAmount;
    private Date finishDate;

    // Constructor
    public ClientStatistics(int clientID, String firstName, String lastName, double totalDueAmount, double totalPaidAmount, Date finishDate) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalDueAmount = totalDueAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.finishDate = finishDate;
    }

    // Getters
    public int getClientID() {
        return clientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    // Setters
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "ClientStatistics{" +
               "clientID=" + clientID +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", totalDueAmount=" + totalDueAmount +
               ", totalPaidAmount=" + totalPaidAmount +
               ", finishDate=" + finishDate +
               '}';
    }
}
