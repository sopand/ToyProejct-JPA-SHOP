package com.example.shop.dto;

import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderResponse {

    private Long ordid;
    private Product product;
    private String ordchk;

    private int ordquantity;

    private ImgResponse imgs;


    public OrderResponse(Order entity){
        this.ordid=entity.getOrdid();
        this.product=entity.getProduct();
        this.ordchk=entity.getOrdchk();
        this.ordquantity=entity.getOrdquantity();
    }



}
