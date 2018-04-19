package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "is_active=1")
@Table(name = "StockReceivingItem")
public class StockReceivingItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StockReceivingItemID", nullable = false)
    private Integer stockReceivingItemID;

    @Column(name = "PriceBought", nullable = false)
    private Long priceBought;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "StockReceivingOrderID", nullable = false)
    private Integer stockReceivingOrderID;

    @ManyToOne
    @JoinColumn(name = "StockReceivingOrderID", insertable = false, updatable = false)
    private StockReceivingOrder stockReceivingOrder;

    @JsonIgnore
    @OneToMany(mappedBy = "stockReceivingItem")
    private List<Item> itemList;

    public StockReceivingItem() {
    }

    public Integer getStockReceivingItemID() {
        return stockReceivingItemID;
    }

    public void setStockReceivingItemID(Integer stockReceivingItemID) {
        this.stockReceivingItemID = stockReceivingItemID;
    }

    public Long getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(Long priceBought) {
        this.priceBought = priceBought;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStockReceivingOrderID() {
        return stockReceivingOrderID;
    }

    public void setStockReceivingOrderID(Integer stockReceivingOrderID) {
        this.stockReceivingOrderID = stockReceivingOrderID;
    }

    public StockReceivingOrder getStockReceivingOrder() {
        return stockReceivingOrder;
    }

    public void setStockReceivingOrder(StockReceivingOrder stockReceivingOrder) {
        this.stockReceivingOrder = stockReceivingOrder;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
