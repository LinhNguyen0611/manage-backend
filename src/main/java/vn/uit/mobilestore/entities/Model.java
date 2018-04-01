package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Entity
@Table (name = "Model")
public class Model extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ModelID", nullable = false)
    private Integer modelID;

    @Column(name = "BranchID", nullable = false)
    private Integer branchID;

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
    @OneToMany(mappedBy = "parentModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

    public Model() {
    }

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
    }

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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
