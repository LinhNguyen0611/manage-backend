package vn.uit.mobilestore.entities;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Where(clause = "is_deleted = 0")
@SQLDelete(sql = "UPDATE roles SET is_deleted = 1 WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Role extends AbstractEntity {

    @Column(unique = true)
    private String name;

    @Column
    private String displayName;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
