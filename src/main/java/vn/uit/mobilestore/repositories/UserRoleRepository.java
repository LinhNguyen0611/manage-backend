package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Role;
import vn.uit.mobilestore.entities.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
//    @Query("SELECT i FROM UserRole i WHERE i.user_id = :id")
//    Role getRole(@Param("id") Integer id);
}
