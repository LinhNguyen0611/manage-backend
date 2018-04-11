package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Entity
@Table(name = "variant")
public class Variant extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VariantID", nullable = false)
    private Integer variantId;

    @Column(name = "ModelID", nullable = false)
    private Integer modelID;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "Storage", nullable = false)
    private String storage;

    @Column(name = "PriceSold", nullable = false)
    private Long pricesold;

    @ManyToOne
    @JoinColumn (name = "ModelID", insertable = false, updatable = false)
    private Model model;

    @OneToMany (mappedBy = "variant")
    private List<Item> itemList;

    public Variant() {
    }

    public Integer getVariantId() {
        return variantId;
    }

    public void setVariantId(Integer variantId) {
        this.variantId = variantId;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Long getPricesold() {
        return pricesold;
    }

    public void setPricesold(Long pricesold) {
        this.pricesold = pricesold;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
