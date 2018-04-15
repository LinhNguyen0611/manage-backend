package vn.uit.mobilestore.requests;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class RoleRequest extends AbstractRequest {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
