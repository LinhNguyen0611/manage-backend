package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Model;

import javax.validation.constraints.NotNull;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
public class ModelModel {
    @NotNull
    private Integer brandID;
    private String color;
    private String description;
    private String name;
    private String specification;
    private Integer type;

    public ModelModel() {
    }

    public Model toEntity() {
        Model model = new Model();
        model.setBrandID(this.brandID);
        model.setColor(this.color);
        model.setDescription(this.description);
        model.setName(this.name);
        model.setSpecification(this.specification);
        model.setType(this.type);
        return model;
    }

    public Integer getBrandID() {
        return brandID;
    }

    public void setBrandID(Integer brandID) {
        this.brandID = brandID;
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
