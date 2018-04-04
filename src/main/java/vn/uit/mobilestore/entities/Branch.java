package vn.uit.mobilestore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import vn.uit.mobilestore.models.BranchModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/3/2018.
 */
@Entity
@Table(name = "branch")
public class Branch extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BranchID", nullable = false)
    private Integer branchId;

    @Column(name = "Country", nullable = false)
    private String country;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "parentBranch", cascade = CascadeType.ALL)
    private List<Model> modelList;

    public Branch() {
    }

    public Branch updateBranch(BranchModel branchModel) {
        this.country = branchModel.getCountry();
        this.description = branchModel.getDescription();
        this.name = branchModel.getName();
        return this;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }
}
