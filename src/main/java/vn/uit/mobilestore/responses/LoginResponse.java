package vn.uit.mobilestore.responses;

import vn.uit.mobilestore.dtos.UserDto;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class LoginResponse extends Response {

    private UserDto user;

    private String token;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
