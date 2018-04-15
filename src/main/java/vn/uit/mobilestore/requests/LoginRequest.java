package vn.uit.mobilestore.requests;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class LoginRequest extends AbstractRequest {

    private String credential;

    private String password;

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
