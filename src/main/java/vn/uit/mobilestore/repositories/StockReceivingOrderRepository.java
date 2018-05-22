package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.StockReceivingOrder;
import vn.uit.mobilestore.entities.StockReceivingItem;

@Repository
public interface StockReceivingOrderRepository extends JpaRepository<StockReceivingOrder, Integer> {
    @Query("SELECT stockReceivingOrder.stockReceivingItemList FROM StockReceivingOrder stockReceivingOrder WHERE stockReceivingOrder.stockReceivingOrderID = :stockReceivingOrderId")
    Page<StockReceivingItem> listStockReceivingItemByOrderId(@Param("stockReceivingOrderId") Integer stockReceivingOrderId, Pageable pageable);

}
