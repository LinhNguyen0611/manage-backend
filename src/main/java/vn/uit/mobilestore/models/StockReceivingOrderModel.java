package vn.uit.mobilestore.models;

import java.util.Date;

import vn.uit.mobilestore.entities.StockReceivingOrder;

public class StockReceivingOrderModel {
    private Date date;

    private Integer supplierId;

    // Contructors
    public StockReceivingOrderModel() {

    }

    // To Entity function
    public StockReceivingOrder toEntity() {
        StockReceivingOrder stockReceivingOrder = new StockReceivingOrder();
        stockReceivingOrder.setDate(this.date);
        stockReceivingOrder.setSupplierID(this.supplierId);

        return stockReceivingOrder;
    }


    // Get, Set Function
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSupplierID() {
        return this.supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
}
