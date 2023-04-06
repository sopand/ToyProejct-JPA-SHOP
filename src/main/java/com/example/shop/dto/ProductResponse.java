package com.example.shop.dto;

import com.example.shop.entity.Img;
import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
import com.example.shop.entity.Product;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ProductResponse {

    private Long proId;
    private String proName;
    private int proPrice;

    private String proCategory;
    private List<ImgResponse> img=new ArrayList<>();

    private List<OptionResponse> option=new ArrayList<>();
    private Date proDate;

    private List<String> option1=new ArrayList<>();

    public ProductResponse(Product entity){
        this.proId=entity.getProId();
        this.proName=entity.getProName();
        this.proPrice=entity.getProPrice();
        this.proDate=entity.getProDate();
        this.proCategory= entity.getProCategory();
        this.img=entity.getImg().stream().map(ImgResponse::new).toList();
        this.option=entity.getOption().stream().map(OptionResponse::new).toList();
        if(option.get(0).getOpt1()!=null){
            List<String> opt1List=option.stream().map(opt->new String(opt.getOpt1())).toList();
            this.option1=opt1List.stream().distinct().toList();
        }

    }





}
