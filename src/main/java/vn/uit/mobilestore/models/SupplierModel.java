package vn.uit.mobilestore.models;

import javax.validation.constraints.NotNull;

import vn.uit.mobilestore.entities.Supplier;

public class SupplierModel {

    @NotNull
    private String name;

    private String address;

    private String phone;

    private String email;

    private Integer status;

    public SupplierModel() {

    }

    public Supplier toEntity() {
        Supplier supplier = new Supplier();
        supplier.setName(this.name);
        supplier.setAddress(this.address);
        supplier.setPhone(this.phone);
        supplier.setEmail(this.email);
        supplier.setStatus(this.status);
        return supplier;
    }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    public String getAddress() { return this.address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return this.phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return this.email; }

    public void setEmail(String email) { this.email = email; }

    public Integer getStatus() { return this.status; }

    public void setStatus(Integer status) { this.status = status; }
}
