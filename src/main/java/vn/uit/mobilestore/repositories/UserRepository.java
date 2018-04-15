package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.User;

import java.util.Optional;

/**
 * Created by Linh Nguyen on 4/15/2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);


}
