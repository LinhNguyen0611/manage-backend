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

    @Column(name = "IMEI", nullable = false)
    private String imei;

    @Column(name = "ModelFromSupplierID", nullable = false)
    private Integer modelFromSupplierId;

    @Column(name = "ModelID", nullable = false)
    private Integer modelId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Note", nullable = false)
    private String note;

    @Column(name = "SerializerNumber", nullable = false)
    private String serializerNumber;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "ModelID", insertable = false, updatable = false)
    private Model parentModel;


    public Item updateItem(ItemModel itemModel) {
        this.imei = itemModel.getImei();
        this.modelFromSupplierId = itemModel.getModelFromSupplierId();
        this.modelId = itemModel.getModelId();
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

    public Model getParentModel() {
        return parentModel;
    }

    public void setParentModel(Model parentModel) {
        this.parentModel = parentModel;
    }
}
