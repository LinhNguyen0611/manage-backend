package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.uit.mobilestore.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Item repository.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
