package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

import vn.uit.mobilestore.models.OrderDetailModel;

/**
 * Created by Sinh Nguyen on 31/5/2018.
 */
@Entity
@Where(clause = "is_active=1")
@Table (name = "OrderDetail")
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID", nullable = false)
    private Integer orderDetailID;

    @Column(name = "Price", nullable = false)
    private Long price;

    // Order Foreign Key
    @Column(name = "OrderBillID", nullable = false)
    private Integer orderBillID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "OrderBillID", insertable = false, updatable = false)
    private OrderBill orderBill;

    // Item ForeignKey
    @Column(name = "ItemID", nullable = false)
    private Integer itemID;

//    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ItemID", insertable = false, updatable = false)
    private Item item;

    // Constructor
    public OrderDetail() {

    }

    // Get Methods
    public Integer getOrderDetailID() {
        return this.orderDetailID;
    }

    public Long getPrice() {
        return this.price;
    }

    public Integer getOrderBillID() {
        return this.orderBillID;
    }

    public OrderBill getOrderBill() {
        return this.orderBill;
    }

    public Integer getItemID() {
        return this.itemID;
    }

    public Item getItem() {
        return this.item;
    }

    // Set methods
    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setOrderBillID (Integer orderBillID) {
        this.orderBillID = orderBillID;
    }

    public void setOrderBill(OrderBill orderBill) {
        this.orderBill = orderBill;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // Method
    // Update Entity
    public OrderDetail updateOrderDetail(OrderDetailModel orderDetailModel) {
        this.price = orderDetailModel.getPrice();
        this.orderBillID = orderDetailModel.getOrderBillID();
        this.itemID = orderDetailModel.getItemID();

        return this;
    }

    // Static methods
    public static OrderDetail CreateNewOrderDetail(Item item, OrderBill orderBill, Long price) {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setPrice(price);
        orderDetail.setItemID(item.getItemId());
        orderDetail.setOrderBillID(orderBill.getOrderBillID());

        return  orderDetail;
    }
}
