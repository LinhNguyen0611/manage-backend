package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Brand;
import vn.uit.mobilestore.entities.Model;

/**
 * Created by Linh Nguyen on 4/9/2018.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {
    @Query("SELECT b.modelList FROM Brand b WHERE b.brandId = :brandId")
    Page<Model> listModelByBrandId(@Param("brandId") Integer brandId, Pageable pageable);
}
