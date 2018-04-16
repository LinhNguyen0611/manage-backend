package vn.uit.mobilestore.entities;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_receiving_items")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "UPDATE stock_receiving_items SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class StockReceivingItem extends AbstractEntity {

    @Column(nullable = false)
    private Long priceBought;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne
    private StockReceivingOrder stockReceivingOrder;

    @OneToMany(mappedBy = "stockReceivingItem")
    private List<Item> itemList;

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
