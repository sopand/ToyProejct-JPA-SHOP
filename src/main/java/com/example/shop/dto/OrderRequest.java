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

    public Order create(Long id) {

        return Order.builder()
                .product(createProductEntity())
                .member(createMemberEntity(id))
                .option(createOptionEntity())
                .ordquantity(ordquantity)
                .ordchk(ordchk)
                .build();
    }
    public Order favorite(Long id) {
        return Order.builder()
                .product(createProductEntity())
                .member(createMemberEntity(id))
                .ordchk(ordchk).build();
    }

    public Order directbuy(Long id) {
        return Order.builder()
                .ordchk(ordchk)
                .ordquantity(ordquantity)
                .ordaddress(ordaddress)
                .ordhuname(ordhuname)
                .product(createProductEntity())
                .member(createMemberEntity(id))
                .option(createOptionEntity()).build();
    }

    public Option createOptionEntity(){
        return Option.builder().optid(optid).build();
    }
    public Member createMemberEntity(Long id){
        return Member.builder().id(id).build();
    }public Product createProductEntity(){
        return Product.builder().proId(proId).build();
    }



}
