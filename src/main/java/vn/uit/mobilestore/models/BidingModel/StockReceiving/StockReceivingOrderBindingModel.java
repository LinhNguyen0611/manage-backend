package vn.uit.mobilestore.models.BidingModel.StockReceiving;

import java.util.Date;
import java.util.List;

import vn.uit.mobilestore.entities.StockReceivingOrder;
import vn.uit.mobilestore.models.BidingModel.StockReceiving.StockReceivingItemBindingModel;


public class StockReceivingOrderBindingModel {
    private Date date;

    private Integer supplierID;

    private List<StockReceivingItemBindingModel> stockReceivingItemList;

    // Contructors
    public StockReceivingOrderBindingModel() {

    }

    // To Entity function
    public StockReceivingOrder toEntity() {
        StockReceivingOrder stockReceivingOrder = new StockReceivingOrder();
        stockReceivingOrder.setDate(this.date);
        stockReceivingOrder.setSupplierID(this.supplierID);

        return stockReceivingOrder;
    }


    // Get Function
    public Date getDate() {
        return this.date;
    }

    public Integer getSupplierID() {
        return this.supplierID;
    }

    public List<StockReceivingItemBindingModel> getStockReceivingItemList() {
        return this.stockReceivingItemList;
    }

    // Set function

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStockReceivingItemList(List<StockReceivingItemBindingModel> stockReceivingItemList) {
        this.stockReceivingItemList = stockReceivingItemList;
    }

}
