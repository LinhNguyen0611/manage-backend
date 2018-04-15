package vn.uit.mobilestore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HieuNP on 15/04/2018.
 */
@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @ManyToMany
    private List<User> users = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
