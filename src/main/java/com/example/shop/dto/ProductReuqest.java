package com.example.shop.dto;


import com.example.shop.entity.Member;
import com.example.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductReuqest {
    private String pro_name;
    private int pro_price;
    private String email;


    public Product productEntity() {
        return Product.builder().pro_name(pro_name).pro_price(pro_price).email(email).build();
    }
}
