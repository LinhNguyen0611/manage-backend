package vn.uit.mobilestore.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class RoleDto extends AbstractDto {

    private String name;

    private String displayName;

    private List<UserDto> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
