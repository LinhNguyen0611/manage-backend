package vn.uit.mobilestore.models.BindingModel.OrderBill;

import java.util.List;

import vn.uit.mobilestore.entities.OrderBill;
import vn.uit.mobilestore.entities.User;

import vn.uit.mobilestore.enums.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class OrderBillBindingModel {
    private Date date;

    @NotNull
    private Long total;

    @NotNull
    private String address;

    @NotNull
    private String customerName;

    @NotNull
    private String phone;

    private String email;

    private String note;

    private OrderStatus status;

    private List<OrderDetailBindingModel> orderDetailBindingModelList;

//    private Integer userID;

    public OrderBillBindingModel() {

    }

    // Get Methods
    public Date getDate() {
        return this.date;
    }

    public Long getTotal() {
        return this.total;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getNote() {
        return this.note;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public List<OrderDetailBindingModel> getOrderDetailBindingModelList() {
        return this.orderDetailBindingModelList;
    }

    // set Methods
    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setOrderDetailBindingModelList(List<OrderDetailBindingModel> orderDetailBindingModelList) {
        this.orderDetailBindingModelList = orderDetailBindingModelList;
    }

    // To Entity
    public OrderBill toEntity(User user) {
        OrderBill orderBill = new OrderBill();

        orderBill.setDate(this.date);
        orderBill.setTotal(this.total);
        orderBill.setAddress(this.address);
        orderBill.setCustomerName(this.customerName);
        orderBill.setPhone(this.phone);
        orderBill.setEmail(this.email);
        orderBill.setNote(this.note);
        orderBill.setStatus(this.status);
        orderBill.setUserID(user.getId());

        return orderBill;
    }
}
