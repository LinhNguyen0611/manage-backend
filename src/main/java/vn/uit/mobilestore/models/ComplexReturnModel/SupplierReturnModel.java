package vn.uit.mobilestore.models.ComplexReturnModel;

import vn.uit.mobilestore.entities.Supplier;

import javax.validation.constraints.NotNull;

public class SupplierReturnModel {

    @NotNull
    private String name;

    private String address;

    private String phone;

    private String email;

    private Integer status;

    public SupplierReturnModel() {

    }

    public static SupplierReturnModel create(Supplier supplier) {
        SupplierReturnModel supplierReturnModel = new SupplierReturnModel();
        supplierReturnModel.setName(supplier.getName());
        supplierReturnModel.setAddress(supplier.getAddress());
        supplierReturnModel.setPhone(supplier.getPhone());
        supplierReturnModel.setEmail(supplier.getEmail());
        supplierReturnModel.setStatus(supplier.getStatus());

        return supplierReturnModel;
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
