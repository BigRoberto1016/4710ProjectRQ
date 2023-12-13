public class BigClient {
    protected String firstName;
    protected String lastName;
    protected int clientID;
    protected int treeCutted;
    
    // Constructor
    public BigClient(String firstName, String lastName, int clientID, int treeCutted) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientID = clientID;
        this.treeCutted = treeCutted;
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

    public int getTreeCutted() {
        return treeCutted;
    }

    public void setTreeCutted(int treeCutted) {
        this.treeCutted = treeCutted;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "BigClient{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", clientID=" + clientID +
               ", treeCutted=" + treeCutted +
               '}';
    }
}
