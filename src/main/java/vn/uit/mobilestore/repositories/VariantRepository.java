package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;

/**
 * Created by Linh Nguyen on 4/11/2018.
 */
@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
}
