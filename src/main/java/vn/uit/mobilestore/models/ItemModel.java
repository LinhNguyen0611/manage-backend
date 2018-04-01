package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Item;

import javax.validation.constraints.NotNull;

public class ItemModel {
    private String imei;
    @NotNull
    private Integer modelFromSupplierId;
    @NotNull
    private Integer modelId;
    private String name;
    private String note;
    private String serializerNumber;
    private String status;

    public ItemModel() {
    }

    public Item toEntity() {
        Item item = new Item();
        item.setImei(this.imei);
        item.setModelFromSupplierId(this.modelFromSupplierId);
        item.setModelId(this.modelId);
        item.setName(this.name);
        item.setNote(this.note);
        item.setSerializerNumber(this.serializerNumber);
        item.setStatus(this.status);
        return item;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getModelFromSupplierId() {
        return modelFromSupplierId;
    }

    public void setModelFromSupplierId(Integer modelFromSupplierId) {
        this.modelFromSupplierId = modelFromSupplierId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
