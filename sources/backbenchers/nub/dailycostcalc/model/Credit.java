package backbencers.nub.dailycostcalc.model;

public class Credit {
    private Double creditAmount;
    private String creditCategory;
    private String creditDate;
    private String creditDescription;
    private int creditId;
    private int creditTimestamp;
    private String month;
    private String year;

    public Credit() {
    }

    public Credit(String creditDate2, String creditCategory2, String creditDescription2, Double creditAmount2, int creditTimestamp2) {
        this.creditDate = creditDate2;
        this.creditCategory = creditCategory2;
        this.creditDescription = creditDescription2;
        this.creditAmount = creditAmount2;
        this.creditTimestamp = creditTimestamp2;
    }

    public Credit(int creditId2, String creditDate2, String creditCategory2, String creditDescription2, Double creditAmount2, int creditTimestamp2) {
        this.creditId = creditId2;
        this.creditDate = creditDate2;
        this.creditCategory = creditCategory2;
        this.creditDescription = creditDescription2;
        this.creditAmount = creditAmount2;
        this.creditTimestamp = creditTimestamp2;
    }

    public Credit(String creditDate2, String month2, String year2, String creditCategory2, String creditDescription2, Double creditAmount2, int creditTimestamp2) {
        this.creditId = this.creditId;
        this.creditDate = creditDate2;
        this.month = month2;
        this.year = year2;
        this.creditCategory = creditCategory2;
        this.creditDescription = creditDescription2;
        this.creditAmount = creditAmount2;
        this.creditTimestamp = creditTimestamp2;
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

    public int getCreditId() {
        return this.creditId;
    }

    public void setCreditId(int creditId2) {
        this.creditId = creditId2;
    }

    public String getCreditDate() {
        return this.creditDate;
    }

    public void setCreditDate(String creditDate2) {
        this.creditDate = creditDate2;
    }

    public String getCreditCategory() {
        return this.creditCategory;
    }

    public void setCreditCategory(String creditCategory2) {
        this.creditCategory = creditCategory2;
    }

    public String getCreditDescription() {
        return this.creditDescription;
    }

    public void setCreditDescription(String creditDescription2) {
        this.creditDescription = creditDescription2;
    }

    public Double getCreditAmount() {
        return this.creditAmount;
    }

    public void setCreditAmount(Double creditAmount2) {
        this.creditAmount = creditAmount2;
    }

    public int getCreditTimestamp() {
        return this.creditTimestamp;
    }

    public void setCreditTimestamp(int creditTimestamp2) {
        this.creditTimestamp = creditTimestamp2;
    }
}
