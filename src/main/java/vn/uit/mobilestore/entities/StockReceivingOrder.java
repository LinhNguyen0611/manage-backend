package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stock_receiving_orders")
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
