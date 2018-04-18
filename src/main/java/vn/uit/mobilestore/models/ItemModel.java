package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Item;

import javax.validation.constraints.NotNull;

public class ItemModel {
    private Integer stockReceivingItemId;
    @NotNull
    private Integer variantId;
    private String name;
    private String imei;
    private String note;
    private String serializerNumber;
    private String status;

    public ItemModel() {
    }

    public Item toEntity() {
        Item item = new Item();
        item.setStockReceivingItemId(this.stockReceivingItemId);
        item.setVariantId(this.variantId);
        item.setName(this.name);
        item.setImei(this.imei);
        item.setNote(this.note);
        item.setSerializerNumber(this.serializerNumber);
        item.setStatus(this.status);
        return item;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
