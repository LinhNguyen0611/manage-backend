package vn.uit.mobilestore.entities;

import vn.uit.mobilestore.enums.SupplierStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private SupplierStatus supplierStatus = SupplierStatus.PROVIDING;

    @OneToMany(mappedBy = "supplier")
    private List<StockReceivingOrder> stockReceivingOrderList;

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

    public SupplierStatus getSupplierStatus() {
        return supplierStatus;
    }

    public void setSupplierStatus(SupplierStatus supplierStatus) {
        this.supplierStatus = supplierStatus;
    }

    public List<StockReceivingOrder> getStockReceivingOrderList() {
        return stockReceivingOrderList;
    }

    public void setStockReceivingOrderList(List<StockReceivingOrder> stockReceivingOrderList) {
        this.stockReceivingOrderList = stockReceivingOrderList;
    }
}
