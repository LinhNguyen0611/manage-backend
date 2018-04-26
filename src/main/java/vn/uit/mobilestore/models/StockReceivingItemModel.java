package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.StockReceivingItem;


public class StockReceivingItemModel {
    private Long priceBought;

    private Integer quantity;

    private Integer stockReceivingOrderID;

    // Contructors
    public StockReceivingItemModel() {

    }

    // Get Method
    public Long getPriceBought() {
        return this.priceBought;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Integer getStockReceivingOrderID() {
        return this.stockReceivingOrderID;
    }

    // Set Method
    public void setPriceBought(Long priceBought) {
        this.priceBought = priceBought;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setStockReceivingOrderID(Integer stockReceivingOrderID) {
        this.stockReceivingOrderID = stockReceivingOrderID;
    }

    // ToEntity method
    public StockReceivingItem toEntity() {
        StockReceivingItem stockReceivingItem = new StockReceivingItem();
        stockReceivingItem.setPriceBought(this.priceBought);
        stockReceivingItem.setQuantity(this.quantity);
        stockReceivingItem.setStockReceivingOrderID(this.stockReceivingOrderID);

        return stockReceivingItem;
    }
}
