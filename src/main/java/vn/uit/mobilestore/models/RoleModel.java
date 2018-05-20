package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Role;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;

    public RoleModel(){

    }

    public Role toEntity()
    {
        Role role = new Role();
        role.setId(this.id);
        role.setDescription(this.description);
        role.setRoleName(this.roleName);
        return role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
