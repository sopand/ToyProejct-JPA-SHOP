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

    private Long proid;
    private String proname;
    private int proprice;
    private List<Img> img=new ArrayList<>();
    private Date prodate;

    public ProductResponse(Product entity){
        this.proid=entity.getProid();
        this.proname=entity.getProname();
        this.proprice=entity.getProprice();
        this.prodate=entity.getProdate();
        this.img=entity.getImg();
    }



}
