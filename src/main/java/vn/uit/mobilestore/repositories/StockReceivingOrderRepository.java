package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.uit.mobilestore.entities.StockReceivingOrder;

@Repository
public interface StockReceivingOrderRepository extends JpaRepository<StockReceivingOrder, Integer> {

}
