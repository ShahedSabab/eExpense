package backbencers.nub.dailycostcalc.model;

public class Debit {
    private Double debitAmount;
    private String debitCategory;
    private String debitDate;
    private String debitDescription;
    private int debitId;
    private String month;
    private String year;

    public Debit(String debitDate2, String debitCategory2, String debitDescription2, Double debitAmount2) {
        this.debitDate = debitDate2;
        this.debitCategory = debitCategory2;
        this.debitDescription = debitDescription2;
        this.debitAmount = debitAmount2;
    }

    public Debit(int debitId2, String debitDate2, String debitCategory2, String debitDescription2, Double debitAmount2) {
        this.debitId = debitId2;
        this.debitDate = debitDate2;
        this.debitCategory = debitCategory2;
        this.debitDescription = debitDescription2;
        this.debitAmount = debitAmount2;
    }

    public Debit(String debitDate2, String month2, String year2, String debitCategory2, String debitDescription2, Double debitAmount2) {
        this.debitDate = debitDate2;
        this.month = month2;
        this.year = year2;
        this.debitCategory = debitCategory2;
        this.debitDescription = debitDescription2;
        this.debitAmount = debitAmount2;
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(String month2) {
        this.month = month2;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year2) {
        this.year = year2;
    }

    public int getDebitId() {
        return this.debitId;
    }

    public void setDebitId(int debitId2) {
        this.debitId = debitId2;
    }

    public String getDebitDate() {
        return this.debitDate;
    }

    public void setDebitDate(String debitDate2) {
        this.debitDate = debitDate2;
    }

    public String getDebitCategory() {
        return this.debitCategory;
    }

    public void setDebitCategory(String debitCategory2) {
        this.debitCategory = debitCategory2;
    }

    public String getDebitDescription() {
        return this.debitDescription;
    }

    public void setDebitDescription(String debitDescription2) {
        this.debitDescription = debitDescription2;
    }

    public Double getDebitAmount() {
        return this.debitAmount;
    }

    public void setDebitAmount(Double debitAmount2) {
        this.debitAmount = debitAmount2;
    }
}
