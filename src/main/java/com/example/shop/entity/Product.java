package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pro_id;
    private String pro_name;
    private int pro_price;
    private String email;

    @OneToMany( mappedBy = "product")
    private List<Img> img=new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    private Date pro_date;


    @Builder
    public Product(String pro_name,int pro_price,String email){
        this.pro_name=pro_name;
        this.pro_price=pro_price;
        this.email=email;
        this.pro_date=new Date();
    }
}
