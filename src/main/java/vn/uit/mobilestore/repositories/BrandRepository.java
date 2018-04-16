package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
