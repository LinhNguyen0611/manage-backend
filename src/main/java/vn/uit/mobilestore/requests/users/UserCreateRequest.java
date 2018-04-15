package vn.uit.mobilestore.requests.users;

import vn.uit.mobilestore.requests.UserRequest;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class UserCreateRequest extends UserRequest {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
