package com.victor.orders.controller;

import com.victor.orders.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("offers")
public class OfferController {

    @GetMapping
    public String getOrdersWaitingOffers() {
        return "offer/offer";
    }

}
