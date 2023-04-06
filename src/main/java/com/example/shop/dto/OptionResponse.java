package com.example.shop.dto;


import com.example.shop.entity.Option;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OptionResponse {
    private String opt1;
    private String opt2;
    private int opquantity;
    private Long proId;

    private Long optid;

    public OptionResponse(Option entity){
        this.opquantity=entity.getOptquantity();
        this.proId=entity.getProduct().getProId();
        this.opt2=entity.getOpt2();
        this.opt1=entity.getOpt1();
        this.optid=entity.getOptid();
    }
}
