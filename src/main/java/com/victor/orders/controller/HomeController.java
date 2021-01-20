package com.victor.orders.controller;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import com.victor.orders.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {

    private OrderRepository orderRepository;

    public HomeController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String home(Model model) {
        Sort deliveryDateSort = Sort.by("deliveryDate").descending();
        PageRequest page = PageRequest.of(0, 10, deliveryDateSort);

        List<Order> orders = this.orderRepository.findByStatus(StatusOrder.DELIVERED, page);
        model.addAttribute("orders", orders);
        return "home";
    }

    @GetMapping("{status}")
    public String findByStatus(@PathVariable(name = "status") String status, Model model) {
        PageRequest page = PageRequest.of(0, 10);
        List<Order> orders = this.orderRepository.findByStatus(StatusOrder.valueOf(status.toUpperCase()), page);
        model.addAttribute("orders", orders);
        return "home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleError(){
        return "redirect:home";
    }

}
