package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Where(clause = "is_active=1")
@Table(name = "StockReceivingOrder")
public class StockReceivingOrder extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StockReceivingOrderID", nullable = false)
    private Integer stockReceivingOrderID;

    @Column(name = "Date", nullable = false)
    private Date date;

    @Column(name = "SupplierID", nullable = false)
    private Integer supplierID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SupplierID", insertable = false, updatable = false)
    private Supplier supplier;

    @JsonIgnore
    @OneToMany(mappedBy = "stockReceivingOrder")
    private List<StockReceivingItem> stockReceivingItemList;


    public StockReceivingOrder() {
    }

    public Integer getStockReceivingOrderID() {
        return stockReceivingOrderID;
    }

    public void setStockReceivingOrderID(Integer stockReceivingOrderID) {
        this.stockReceivingOrderID = stockReceivingOrderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<StockReceivingItem> getStockReceivingItemList() {
        return stockReceivingItemList;
    }

    public void setStockReceivingItemList(List<StockReceivingItem> stockReceivingItemList) {
        this.stockReceivingItemList = stockReceivingItemList;
    }
}
