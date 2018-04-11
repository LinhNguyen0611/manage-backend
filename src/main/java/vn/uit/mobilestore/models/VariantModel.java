package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;

import javax.validation.constraints.NotNull;

/**
 * Created by Linh Nguyen on 4/11/2018.
 */
public class VariantModel {

    @NotNull
    private Integer modelID;
    private String name;
    private String color;
    private String storage;
    private Long pricesold;

    public VariantModel() {
    }

    public Variant toEntity() {
        Variant variant = new Variant();
        variant.setModelID(this.modelID);
        variant.setName(this.name);
        variant.setColor(this.color);
        variant.setStorage(this.storage);
        variant.setPricesold(this.pricesold);
        return variant;
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
}
