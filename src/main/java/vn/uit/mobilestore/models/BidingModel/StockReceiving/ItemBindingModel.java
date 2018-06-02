package vn.uit.mobilestore.models.BidingModel.StockReceiving;

import vn.uit.mobilestore.constants.ItemStatus;
import vn.uit.mobilestore.entities.Item;

import javax.validation.constraints.NotNull;

public class ItemBindingModel {
    @NotNull
    private Integer variantId;
    private String name;
    private String imei;
    private String note;
    private String serializerNumber;
    private ItemStatus status;

    public ItemBindingModel() {
    }

    // Begin toEntity Methods
    // toEntity without stockReceivingItemID
    public Item toEntity() {
        Item item = new Item();
        item.setVariantId(this.variantId);
        item.setName(this.name);
        item.setImei(this.imei);
        item.setNote(this.note);
        item.setSerializerNumber(this.serializerNumber);
        item.setStatus(this.status);
        return item;
    }

    public Item toEntity(Integer stockReceivingItemID) {
        Item item = new Item();
        item.setVariantId(this.variantId);
        item.setName(this.name);
        item.setImei(this.imei);
        item.setNote(this.note);
        item.setSerializerNumber(this.serializerNumber);
        item.setStatus(this.status);
        item.setStockReceivingItemId(stockReceivingItemID);
        return item;
    }
    // End toEntity Methods
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSerializerNumber() {
        return serializerNumber;
    }

    public void setSerializerNumber(String serializerNumber) {
        this.serializerNumber = serializerNumber;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }
}
