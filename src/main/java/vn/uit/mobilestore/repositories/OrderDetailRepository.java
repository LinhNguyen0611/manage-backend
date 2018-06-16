package vn.uit.mobilestore.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import vn.uit.mobilestore.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT order FROM OrderDetail order WHERE order.orderBillID = :orderBillID AND order.isActive = 1 ")
    List<OrderDetail> getOrderDetailByOrderBillID(@Param("orderBillID") Integer orderBillID);

    @Query("SELECT order FROM OrderDetail order WHERE order.orderBillID = :orderBillID")
    List<OrderDetail> getOrderDetailByOrderBillIDAdmin(@Param("orderBillID") Integer orderBillID);
}
