package com.example.shop.dto;

import com.example.shop.entity.Img;
import com.example.shop.entity.Member;
import com.example.shop.entity.Product;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@ToString
public class ProductResponse {

    private Long pro_id;
    private String pro_name;
    private int pro_price;
    private String email;
    private List<Img> img=new ArrayList<>();
    private Date pro_date;

    public ProductResponse(Product entity){
        this.pro_id=entity.getPro_id();
        this.pro_name=entity.getPro_name();
        this.pro_price=entity.getPro_price();
        this.email=entity.getEmail();
        this.pro_date=entity.getPro_date();
        this.img=entity.getImg();
    }


}
