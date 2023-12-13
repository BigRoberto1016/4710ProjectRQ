public class ProspectiveClient {
    private int clientID;
    private String firstName;
    private String lastName;

    // Constructor
    public ProspectiveClient(int clientID, String firstName, String lastName) {
        this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
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


    @Override
    public String toString() {
        return "ProspectiveClient{" +
               "clientID=" + clientID +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               '}';
    }
}
