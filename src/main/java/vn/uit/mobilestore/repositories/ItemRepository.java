package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.uit.mobilestore.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import vn.uit.mobilestore.constants.ItemStatus;

/**
 * The interface Item repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Additional repository method. The insert, get list, get one, update, delete is provided
    @Query("SELECT i FROM Item i WHERE i.itemId = :id")
    Item getItem(@Param("id") Integer id);

    @Query("SELECT i FROM Item i WHERE i.status = :status AND i.variantId = :variantID AND i.isActive = 1 ")
    List<Item> getItemByVariantId(@Param("status") ItemStatus status, @Param("variantID") Integer variantID);

    Item findByItemId(Integer itemId);
}
