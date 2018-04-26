package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.Item;
import vn.uit.mobilestore.entities.Model;
import vn.uit.mobilestore.entities.Variant;

/**
 * Created by Linh Nguyen on 4/11/2018.
 */
@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
    @Query("SELECT v.itemList FROM Variant v WHERE v.variantId = :variantId")
    Page<Item> listItemByVariantId(@Param("variantId") Integer variantId, Pageable pageable);
}
