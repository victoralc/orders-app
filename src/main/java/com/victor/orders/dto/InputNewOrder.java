package com.victor.orders.dto;

import com.victor.orders.model.Order;
import com.victor.orders.model.StatusOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InputNewOrder {

    @NotBlank
    private String nomeProduto;

    @NotBlank
    private String urlProduto;

    @NotBlank
    private String urlImagem;

    private String descricao;

    public Order convertToOrder(){
        Order order = new Order();
        order.setProductName(this.nomeProduto);
        order.setUrlImage(this.urlImagem);
        order.setUrlProduct(this.urlProduto);
        order.setDescription(this.descricao);
        order.setStatus(StatusOrder.WAITING);
        return order;
    }

}
