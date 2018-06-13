package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.OrderDetail;

public class OrderDetailModel {

    private Long price;

    // Order Foreign Key
    private Integer orderBillID;

    // Item ForeignKey
    private Integer itemID;

    // Get methods
    public Long getPrice() {
        return this.price;
    }

    public Integer getOrderBillID() {
        return this.orderBillID;
    }

    public Integer getItemID() {
        return this.itemID;
    }

    // Set methods
    public void setPrice(Long price) {
        this.price = price;
    }

    public void setOrderBillID(Integer orderBillID) {
        this.orderBillID = orderBillID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    // ToEntity methods
    public OrderDetail toEntity() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setPrice(this.price);
        orderDetail.setOrderBillID(this.orderBillID);
        orderDetail.setItemID(this.itemID);

        return orderDetail;
    }
}
