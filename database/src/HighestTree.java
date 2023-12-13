public class HighestTree {
    private int treeID;
    private double height;
    private String location;

    // Constructor
    public HighestTree(int treeID, double height, String location) {
        this.treeID = treeID;
        this.height = height;
        this.location = location;
    }

    // Getters
    public int getTreeID() {
        return treeID;
    }

    public double getHeight() {
        return height;
    }

    public String getLocation() {
        return location;
    }

    // Setters
    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "HighestTree{" +
               "treeID=" + treeID +
               ", height=" + height +
               ", location='" + location + '\'' +
               '}';
    }
}
