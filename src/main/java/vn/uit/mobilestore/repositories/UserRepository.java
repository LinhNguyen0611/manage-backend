package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
//public interface UserRepository extends CrudRepository<User, Long> {
//    User findByUsername(String username);
//}
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT i FROM User i  WHERE i.id = :id")
    User getUser(@Param("id") Integer id);

    @Query("SELECT i FROM User i WHERE i.userName = :name")
    User getUserByName(@Param("name") String name);

    @Query("SELECT i FROM User i")
    List<User> getAllUser();

    @Query("SELECT i FROM User i, Role r WHERE r.roleName = :role")
    List<User> getUserOfRole(@Param("role") String role);
}