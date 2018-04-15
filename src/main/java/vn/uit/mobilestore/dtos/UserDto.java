package vn.uit.mobilestore.dtos;

/**
 * Created by HieuNP on 15/04/2018.
 */
public class UserDto extends AbstractDto {

    private String username;

    private String email;

    private String fullName;

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
}
