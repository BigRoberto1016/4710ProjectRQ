public class EasyClient {
    protected String firstName;
    protected String lastName;
    protected int clientID;

    // Constructor
    public EasyClient(String firstName, String lastName, int clientID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientID = clientID;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "EasyClient{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", clientID=" + clientID +
               '}';
    }
}
