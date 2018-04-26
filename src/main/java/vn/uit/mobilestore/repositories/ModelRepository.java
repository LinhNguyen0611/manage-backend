package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;

/**
 * Created by Linh Nguyen on 4/1/2018.
 */
@Repository
public interface ModelRepository extends JpaRepository <Model, Integer> {
    @Query("SELECT m.variantList FROM Model m WHERE m.modelID = :modelId")
    Page<Variant> listVariantByModelId(@Param("modelId") Integer modelId, Pageable pageable);
}
