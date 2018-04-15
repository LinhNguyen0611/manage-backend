package vn.uit.mobilestore.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column( nullable = false)
    private String country;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "brand")
    private List<Model> modelList;

    public Brand() {
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
