package vn.uit.mobilestore.entities;

import javax.persistence.*;

@Entity
@Table(name="user_role")
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="user_id")
    private Integer userID;

    @Column(name="role_id")
    private Integer roleID;

    public UserRole(){

    }

    public UserRole(Integer id, Integer userID, Integer roleID) {
        this.id = id;
        this.userID = userID;
        this.roleID = roleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
