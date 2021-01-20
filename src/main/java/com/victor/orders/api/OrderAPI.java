package com.victor.orders.api;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import com.victor.orders.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {

    private OrderRepository orderRepository;

    public OrderAPI(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("waiting")
    public List<Order> getWaitingOrders() {
        return this.orderRepository.findByStatus(StatusOrder.WAITING, PageRequest.of(0, 10));
    }

}
