package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/13/2018.
 */
@Entity
@Where(clause = "is_active=1")
@Table(name = "Customer")
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID", nullable = false)
    private Integer customerID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "Address")
    private String address;

    @Column(name = "Birthday")
    private Date birthday;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<OrderBill> orderBillList;

    public Customer() {
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<OrderBill> getOrderList() {
        return this.orderBillList;
    }

    public void setOrderList(List<OrderBill> orderBillList) {
        this.orderBillList = orderBillList;
    }
}
