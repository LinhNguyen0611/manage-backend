package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;

import javax.validation.constraints.NotNull;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
public class BrandModel {
    @NotNull
    private String name;
    private String country;
    private String description;

    public BrandModel() {
    }

    public Brand toEntity() {
        Brand brand = new Brand();
        brand.setName(this.name);
        brand.setCountry(this.country);
        brand.setDescription(this.description);
        return brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
