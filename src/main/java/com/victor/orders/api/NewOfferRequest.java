package com.victor.orders.api;

import com.victor.orders.model.Offer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class NewOfferRequest {

    private Long orderId;
    private String deliveryDate;
    private BigDecimal value;
    private String comment;

    public Offer toOffer() {
        Offer offer = new Offer();
        offer.setValue(this.value);
        offer.setDeliveryDate(LocalDate.parse(this.deliveryDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        offer.setComment(this.comment);
        return offer;
    }
}
