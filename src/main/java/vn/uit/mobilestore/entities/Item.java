package vn.uit.mobilestore.entities;

import vn.uit.mobilestore.models.ItemModel;

import javax.persistence.*;

/**
 * Class detail
 */
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ItemID", nullable = false)
    private Integer itemId;

    @Column(name = "StockReceivingItemID")
    private Integer stockReceivingItemId;

    @Column(name = "VariantID", nullable = false)
    private Integer variantId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "IMEI", nullable = false)
    private String imei;

    @Column(name = "SerializerNumber", nullable = false)
    private String serializerNumber;

    @Column(name = "Note", nullable = false)
    private String note;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn (name = "VariantID", insertable = false, updatable = false)
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "StockReceivingItemID", insertable = false, updatable = false)
    private StockReceivingItem stockReceivingItem;

    public Item() {
    }

    public Item updateItem(ItemModel itemModel) {
        this.imei = itemModel.getImei();
        this.stockReceivingItemId = itemModel.getStockReceivingItemId();
        this.variantId = itemModel.getVariantId();
        this.name = itemModel.getName();
        this.note = itemModel.getNote();
        this.serializerNumber = itemModel.getSerializerNumber();
        this.status = itemModel.getStatus();
        return this;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getStockReceivingItemId() {
        return stockReceivingItemId;
    }

    public void setStockReceivingItemId(Integer stockReceivingItemId) {
        this.stockReceivingItemId = stockReceivingItemId;
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

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
