package com.victor.orders.controller;

import com.victor.orders.dto.InputNewOrder;
import com.victor.orders.model.Order;
import com.victor.orders.model.User;
import com.victor.orders.repository.OrderRepository;
import com.victor.orders.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private UserRepository userRepository;

    public OrderController(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("form")
    public String form(InputNewOrder inputNewOrder) {
        return "order/form";
    }

    @PostMapping("new")
    public String createOrder(@Valid InputNewOrder inputNewOrder, BindingResult result) {

        if (result.hasErrors()) {
            return "order/form";
        }

        Order order = inputNewOrder.convertToOrder();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = this.userRepository.findByUsername(username);
        order.setUser(user);

        this.orderRepository.save(order);
        return "home";
    }


}
