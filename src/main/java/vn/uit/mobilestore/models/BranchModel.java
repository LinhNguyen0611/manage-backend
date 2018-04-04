package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Branch;
import vn.uit.mobilestore.entities.Model;

import javax.validation.constraints.NotNull;

/**
 * Created by Linh Nguyen on 4/3/2018.
 */
public class BranchModel {
    private String country;
    private String description;
    private String name;

    public BranchModel() {
    }

    public Branch toEntity() {
        Branch branch = new Branch();
        branch.setCountry(this.country);
        branch.setDescription(this.description);
        branch.setName(this.name);
        return branch;
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
}
