package backbencers.nub.dailycostcalc.model;

public class Category {
    private int categoryId;
    private String categoryName;

    public Category(int categoryId2, String categoryName2) {
        this.categoryId = categoryId2;
        this.categoryName = categoryName2;
    }

    public Category(String categoryName2) {
        this.categoryName = categoryName2;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName2) {
        this.categoryName = categoryName2;
    }
}
