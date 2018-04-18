package vn.uit.mobilestore.entities;

import vn.uit.mobilestore.models.BrandModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Entity
@Table(name = "brand")
public class Brand extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandId", nullable = false)
    private Integer brandId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "Description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Model> modelList;

    public Brand() {
    }

    public Brand updateBrand(BrandModel brandModel) {
        this.country = brandModel.getCountry();
        this.description = brandModel.getDescription();
        this.name = brandModel.getName();
        return this;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
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

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
