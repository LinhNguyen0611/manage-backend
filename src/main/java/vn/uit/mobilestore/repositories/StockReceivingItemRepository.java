package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.StockReceivingItem;

@Repository
public interface StockReceivingItemRepository extends JpaRepository<StockReceivingItem, Integer> {
    
}
