package com.example.shop.dto;

import com.example.shop.entity.Member;
import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {
    private Product product;
    private Member member;
    private int ord_quantity;

    private Long proid;


    public Order toEntity(){
        return Order.builder().product(product).member(member).ordquantity(ord_quantity).build();
    }
}
