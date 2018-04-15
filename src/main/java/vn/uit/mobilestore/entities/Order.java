package vn.uit.mobilestore.entities;

import vn.uit.mobilestore.enums.OrderStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/13/2018.
 */
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @Column(nullable = false)
    private Long total;

    @Column
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column
    private Date receivedDate;

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    private Customer customer;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
