package com.example.shop.dto;

import com.example.shop.entity.Order;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OrderResponse {

    private Long ordid;
    private Long pro_id;
    private String ordchk;


    public OrderResponse(Order entity){
        this.ordid=entity.getOrdid();
        this.pro_id=entity.getProduct().getProid();
        this.ordchk=entity.getOrdchk();
    }



}
