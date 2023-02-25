package com.example.shop.dto;

import com.example.shop.entity.Img;
import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
import com.example.shop.entity.Product;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
public class ProductResponse {

    private Long proid;
    private String proname;
    private int proprice;

    private String procategory;
    private List<ImgResponse> img=new ArrayList<>();

    private List<OptionResponse> option=new ArrayList<>();
    private Date prodate;

    public ProductResponse(Product entity){
        this.proid=entity.getProid();
        this.proname=entity.getProname();
        this.proprice=entity.getProprice();
        this.prodate=entity.getProdate();
        this.procategory= entity.getProcategory();
        this.img=entity.getImg().stream().map(ImgResponse::new).toList();
        this.option=entity.getOption().stream().map(OptionResponse::new).toList();
    }



}
