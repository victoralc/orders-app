package com.victor.orders.repository;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import com.victor.orders.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
