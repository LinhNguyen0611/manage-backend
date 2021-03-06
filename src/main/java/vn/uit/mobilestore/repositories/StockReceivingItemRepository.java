package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.uit.mobilestore.entities.StockReceivingItem;
import vn.uit.mobilestore.entities.Item;

@Repository
public interface StockReceivingItemRepository extends JpaRepository<StockReceivingItem, Integer> {
    @Query("SELECT stockReceivingItem.itemList FROM StockReceivingItem stockReceivingItem WHERE stockReceivingItem.stockReceivingItemID = :stockReceivingItemId")
    Page<Item> listItemByStockReceivingItemId(@Param("stockReceivingItemId") Integer stockReceivingItemId, Pageable pageable);
}
