package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Model;

import javax.validation.constraints.NotNull;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
public class ModelModel {
    @NotNull
    private Integer branchID;
    private String color;
    private String description;
    private String name;
    private String specification;
    private Integer type;

    public Integer getBranchID() {
        return branchID;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
