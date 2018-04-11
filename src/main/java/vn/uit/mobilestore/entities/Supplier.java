package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Supplier")
public class Supplier extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SupplierID", nullable = false)
    private Integer supplierID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Address", nullable = true)
    private String address;

    @Column(name = "Phone", nullable = true)
    private String phone;

    @Column(name = "Email", nullable = true)
    private String email;

    @Column(name = "Status", nullable = true)
    private Integer status;

    @OneToMany(mappedBy = "supplier")
    private List<StockReceivingOrder> stockReceivingOrderList;

    public Supplier() {
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<StockReceivingOrder> getStockReceivingOrderList() {
        return stockReceivingOrderList;
    }

    public void setStockReceivingOrderList(List<StockReceivingOrder> stockReceivingOrderList) {
        this.stockReceivingOrderList = stockReceivingOrderList;
    }
}
