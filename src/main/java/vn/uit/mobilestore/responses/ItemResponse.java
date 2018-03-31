package vn.uit.mobilestore.responses;

import vn.uit.mobilestore.entities.Item;

public class ItemResponse {
    private Integer itemId;
    private String imei;
    private Integer modelFromSupplierId;
    private Integer modelId;
    private String name;
    private String note;
    private String serializerNumber;
    private String status;

    public ItemResponse() {
    }

    public ItemResponse(Item item) {
        this.itemId = item.getItemId();
        this.imei = item.getImei();
        this.modelFromSupplierId = item.getModelFromSupplierId();
        this.modelId = item.getModelId();
        this.name = item.getName();
        this.note = item.getNote();
        this.serializerNumber = item.getSerializerNumber();
        this.status = item.getStatus();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
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
