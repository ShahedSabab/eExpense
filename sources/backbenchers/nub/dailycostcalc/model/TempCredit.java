package backbencers.nub.dailycostcalc.model;

public class TempCredit {
    private double amount;
    private String date;

    public TempCredit(String date2, double amount2) {
        this.date = date2;
        this.amount = amount2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount2) {
        this.amount = amount2;
    }
}
