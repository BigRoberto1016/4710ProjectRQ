public class RevenueStatistics {
    private double totalIncome;
    private double amountAwaited;

    // Constructor
    public RevenueStatistics(double totalIncome, double amountAwaited) {
        this.totalIncome = totalIncome;
        this.amountAwaited = amountAwaited;
    }

    // Getter for totalIncome
    public double getTotalIncome() {
        return totalIncome;
    }

    // Setter for totalIncome
    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    // Getter for amountAwaited
    public double getAmountAwaited() {
        return amountAwaited;
    }

    // Setter for amountAwaited
    public void setAmountAwaited(double amountAwaited) {
        this.amountAwaited = amountAwaited;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "RevenueStatistics{" +
               "totalIncome=" + totalIncome +
               ", amountAwaited=" + amountAwaited +
               '}';
    }
}
