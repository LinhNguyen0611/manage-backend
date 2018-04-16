package vn.uit.mobilestore.entities;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_receiving_orders")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "UPDATE stock_receiving_orders SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class StockReceivingOrder extends AbstractEntity {

    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "stockReceivingOrder")
    private List<StockReceivingItem> stockReceivingItemList;

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
