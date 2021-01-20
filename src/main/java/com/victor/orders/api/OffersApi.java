package com.victor.orders.api;

import com.victor.orders.model.Offer;
import com.victor.orders.model.Order;
import com.victor.orders.repository.OrderRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/offers")
public class OffersApi {

    private OrderRepository orderRepository;

    public OffersApi(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public Offer createOffer(@RequestBody NewOfferRequest offerRequest) {
        Optional<Order> optionalOrder = this.orderRepository.findById(offerRequest.getOrderId());
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("No order found for id: " + offerRequest.getOrderId());
        }

        Order order = optionalOrder.get();
        Offer offer = offerRequest.toOffer();
        offer.setOrder(order);
        order.getOffers().add(offer);
        this.orderRepository.save(order);

        return offer;
    }

}
