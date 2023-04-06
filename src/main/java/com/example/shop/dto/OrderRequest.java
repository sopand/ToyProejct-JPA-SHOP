package com.example.shop.dto;

import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long proId;
    private Long optid;

    private String ordaddress;

    private Long ordid;
    private String ordhuname;

    private List<Long> ordidList;
    private List<Integer> quantityList;

    public Order create(Product product, Member member, Option option) {

        return Order.builder().product(product).member(member).option(option).ordquantity(ordquantity).ordchk(ordchk).build();
    }

    public Order favorite(Product product, Member member) {
        return Order.builder().product(product).member(member).ordchk(ordchk).build();
    }

    public Order directbuy(Product product, Member member, Option option) {
        return Order.builder().ordchk(ordchk).ordquantity(ordquantity).ordaddress(ordaddress).ordhuname(ordhuname).product(product).member(member).option(option).build();
    }


}
