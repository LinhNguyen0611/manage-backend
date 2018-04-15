package vn.uit.mobilestore.requests;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
public class RoleRequest extends AbstractRequest {

    private String name;

    private String displayName;

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
}
