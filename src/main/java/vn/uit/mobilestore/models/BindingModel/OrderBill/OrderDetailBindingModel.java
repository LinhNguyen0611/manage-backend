package vn.uit.mobilestore.models.BindingModel.OrderBill;

public class OrderDetailBindingModel {
    private Integer variantID;

    private Integer countNumber;

    private Long priceEachUnit;

    // Constructor
    public OrderDetailBindingModel() {

    }

    // Get Methods
    public Integer getVariantID() {
        return this.variantID;
    }

    public Integer getCountNumber() {
        return this.countNumber;
    }

    public Long getPriceEachUnit() {
        return this.priceEachUnit;
    }

    // Set methods
    public void setVariantID(Integer variantID) {
        this.variantID = variantID;
    }

    public void setCountNumber(Integer countNumber) {
        this.countNumber = countNumber;
    }

    public void setPriceEachUnit(Long priceEachUnit) {
        this.priceEachUnit = priceEachUnit;
    }
}
