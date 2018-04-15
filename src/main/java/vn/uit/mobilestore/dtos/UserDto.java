package vn.uit.mobilestore.dtos;

import vn.uit.mobilestore.entities.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HieuNP on 15/04/2018.
 */
public class UserDto extends AbstractDto {

    private String username;

    private String email;

    private String fullName;

    private List<RoleDto> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
