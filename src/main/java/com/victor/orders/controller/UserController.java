package com.victor.orders.controller;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import com.victor.orders.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("users")
public class UserController {

    private OrderRepository orderRepository;

    public UserController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("orders")
    public String findAll(Model model, Principal principal) {
        List<Order> orders = this.orderRepository.findAllByUser(principal.getName());
        model.addAttribute("orders", orders);

        return "user/home";
    }

    @GetMapping("orders/{status}")
    public String findByStatus(@PathVariable(name = "status") String status, Model model, Principal principal) {
        List<Order> orders = this.orderRepository.findAllByUserAndStatus(principal.getName(),
                StatusOrder.valueOf(status.toUpperCase()));
        model.addAttribute("orders", orders);
        return "user/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleError(){
        return "redirect:/user/home";
    }
}
