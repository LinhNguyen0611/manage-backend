package vn.uit.mobilestore.entities;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Entity
@Table (name = "models")
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "UPDATE models SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Model extends AbstractEntity {

    @Column
    private String color;

    @Column
    private String description;

    @Column
    private String name;

    @Column
    private String specification;

    @Column
    private Integer type;

    @ManyToOne
    private Brand brand;

    @OneToMany (mappedBy = "model")
    private List<Variant> variantList;

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
