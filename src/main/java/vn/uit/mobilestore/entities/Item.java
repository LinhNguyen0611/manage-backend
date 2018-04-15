package vn.uit.mobilestore.entities;

import javax.persistence.*;

/**
 * Class detail
 */
@Entity
@Table(name = "items")
public class Item extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imei;

    @Column(nullable = false)
    private String serializerNumber;

    @Column(nullable = false)
    private String note;

    @Column( nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn (insertable = false, updatable = false)
    private Variant variant;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private StockReceivingItem stockReceivingItem;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSerializerNumber() {
        return serializerNumber;
    }

    public void setSerializerNumber(String serializerNumber) {
        this.serializerNumber = serializerNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }

    public StockReceivingItem getStockReceivingItem() {
        return stockReceivingItem;
    }

    public void setStockReceivingItem(StockReceivingItem stockReceivingItem) {
        this.stockReceivingItem = stockReceivingItem;
    }
}
