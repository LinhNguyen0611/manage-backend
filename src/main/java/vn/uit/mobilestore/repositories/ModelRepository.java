package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Model;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Repository
public interface ModelRepository extends JpaRepository <Model, Integer> {
}
