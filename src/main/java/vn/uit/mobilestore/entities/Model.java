package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Where;
import vn.uit.mobilestore.models.ItemModel;
import vn.uit.mobilestore.models.ModelModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Entity
@Where(clause = "is_active=1")
@Table (name = "Model")
public class Model extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ModelID", nullable = false)
    private Integer modelID;

    @Column(name = "BrandID", nullable = false)
    private Integer brandID;

    @Column(name = "Color")
    private String color;

    @Column(name = "Description")
    private String description;

    @Column(name = "Name")
    private String name;

    @Column(name = "Specification")
    private String specification;

    @Column(name = "Type")
    private Integer type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BrandID", insertable = false, updatable = false)
    private Brand brand;

    @JsonIgnore
    @OneToMany (mappedBy = "model")
    private List<Variant> variantList;

    public Model() {
    }


    public Model updateModel(ModelModel modelModel) {
        this.brandID = modelModel.getBrandID();
        this.color = modelModel.getColor();
        this.description = modelModel.getDescription();
        this.name = modelModel.getName();
        this.specification = modelModel.getSpecification();
        this.type = modelModel.getType();
        return this;
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Variant> getVariantList() {
        return variantList;
    }

    public void setVariantList(List<Variant> variantList) {
        this.variantList = variantList;
    }
}
