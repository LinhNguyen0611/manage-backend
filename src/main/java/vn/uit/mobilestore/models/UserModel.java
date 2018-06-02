package vn.uit.mobilestore.models;

import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class UserModel {

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserModel(Integer userID, String userName, String password, List<Role> roles) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public UserModel(){

    }

    public User toEntity()
    {
        User user = new User();
        user.setId(this.userID);
        user.setUserName(this.userName);
        user.setPassword((this.password));
        user.setRoles(this.roles);
        return user;
    }

    private Integer userID;
    @NotNull
    private String userName;
    @NotNull
    private String password;

    private String address;

    private String email;

    private String phoneNumber;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    private List<Role> roles;
}
