package com.example.shop.dto;

import com.example.shop.entity.Order;
import com.example.shop.entity.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderResponse {

    private Long ordid;
    private Product product;
    private String ordchk;

    private int ordquantity;

    private ImgResponse imgs;

    private Long ordpostCode;
    private String ordpostCompanyKey;

    private String ordaddress;
    private String ordhuname;

    private Date orddate;

    public OrderResponse(Order entity){
        this.orddate=entity.getOrddate();
        this.ordid=entity.getOrdid();
        this.product=entity.getProduct();
        this.ordchk=entity.getOrdchk();
        this.ordquantity=entity.getOrdquantity();
        this.ordaddress=entity.getOrdaddress();
        this.ordhuname=entity.getOrdhuname();
        this.ordpostCode=entity.getOrdpostCode();
        this.ordpostCompanyKey=entity.getOrdpostCompanyKey();
    }




}
