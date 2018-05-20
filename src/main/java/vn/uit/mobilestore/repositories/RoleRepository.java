package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.uit.mobilestore.entities.Role;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.clear
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT i FROM Role i WHERE i.id = :id")
    Role getRole(@Param("id") Integer id);

    @Query("SELECT i from Role i WHERE i.roleName = :name")
    Role getRoleByName(@Param("name") String name);

    @Query("SELECT i from Role i")
    List<Role> getRoles();

}