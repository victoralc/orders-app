package com.victor.orders.repository;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Cacheable("orders")
    List<Order> findByStatus(StatusOrder status, Pageable pageable);

    @Query("SELECT o from Order o join o.user u WHERE u.username = :username")
    List<Order> findAllByUser(@Param("username") String username);

    @Query("SELECT o from Order o join o.user u WHERE u.username = :username and o.status = :status")
    List<Order> findAllByUserAndStatus(@Param("username") String username, @Param("status") StatusOrder status);
}
