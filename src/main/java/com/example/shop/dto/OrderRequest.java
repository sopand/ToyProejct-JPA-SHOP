package com.example.shop.dto;

import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
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

    private Option option;
    private int ordquantity;

    private String ordchk;
    private Long proid;
    private Long optid;

    private Long ordid;


    public Order create(Product product, Member member, Option option){
        return Order.builder().product(product).member(member).option(option).ordquantity(ordquantity).ordchk(ordchk).build();
    }
    public Order favorite(Product product, Member member){
        return Order.builder().product(product).member(member).ordchk(ordchk).build();
    }

}
