package vn.uit.mobilestore.models.BindingModel.StockReceiving;

import java.util.List;

import vn.uit.mobilestore.entities.StockReceivingItem;

public class StockReceivingItemBindingModel {

    private Long priceBought;

    private Integer quantity;

    private List<ItemBindingModel> itemList;

    // Contructors
    public StockReceivingItemBindingModel() {

    }

    // Get Method
    public Long getPriceBought() {
        return this.priceBought;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public List<ItemBindingModel> getItemList() {
        return itemList;
    }

    // Set Method
    public void setPriceBought(Long priceBought) {
        this.priceBought = priceBought;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setItemList(List<ItemBindingModel> itemList) {
        this.itemList = itemList;
    }


    // ToEntity method
    // Base toEntity method without stockReceivingOrderID
    public StockReceivingItem toEntity() {
        StockReceivingItem stockReceivingItem = new StockReceivingItem();
        stockReceivingItem.setPriceBought(this.priceBought);
        stockReceivingItem.setQuantity(this.quantity);

        return stockReceivingItem;
    }

    // toEntity with stockReceivingOrderID
    public StockReceivingItem toEntity(Integer stockReceivingOrderID) {
        StockReceivingItem stockReceivingItem = new StockReceivingItem();
        stockReceivingItem.setPriceBought(this.priceBought);
        stockReceivingItem.setQuantity(this.quantity);
        stockReceivingItem.setStockReceivingOrderID(stockReceivingOrderID);

        return stockReceivingItem;
    }
}
