package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

import vn.uit.mobilestore.enums.OrderStatus;

/**
 * Created by Sinh Nguyen on 31/5/2018.
 */
@Entity
@Where(clause = "is_active=1")
@Table (name = "OrderBill")
public class OrderBill extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderBillID", nullable = false)
    private Integer orderBillID;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "Total", nullable = false)
    private Long total;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "CustomerName", nullable = false)
    private String customerName;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Note")
    private String note;

    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "UserID", nullable = false)
    private Integer UserID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", insertable = false, updatable = false)
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "orderBill")
    private List<OrderDetail> orderDetailList;

    // Contructor
    public OrderBill() {

    }

    // Get methods
    public Integer getOrderBillID() {
        return this.orderBillID;
    }

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

    public List<OrderDetail> getOrderDetailList() {
        return this.orderDetailList;
    }

    // Set methods
    public void setOrderBillID(Integer orderBillID) {
        this.orderBillID = orderBillID;
    }

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

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
