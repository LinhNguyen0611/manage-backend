package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.UserRole;

import javax.validation.constraints.NotNull;

public class UserRoleModel {

    private Integer id;

    @NotNull
    private Integer userID;

    @NotNull
    private Integer roleID;

    public UserRoleModel()
    {
        //dummy constructor
    }

    public UserRoleModel(Integer userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole toEntity()
    {
        UserRole userRole = new UserRole();
        userRole.setId(this.id);
        userRole.setUserID(this.userID);
        userRole.setRoleID(this.roleID);
        return userRole;
    }
}
