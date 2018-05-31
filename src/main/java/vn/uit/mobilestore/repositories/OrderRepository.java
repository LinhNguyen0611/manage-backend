package vn.uit.mobilestore.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.uit.mobilestore.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
