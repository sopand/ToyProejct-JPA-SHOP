package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "opt")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optid;
    private int optquantity;
    private String opt1;
    private String opt2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Product product;

    @Builder
    public Option(int optquantity,String opt1,String opt2,Product product){
        this.optquantity=optquantity;
        this.opt1=opt1;
        this.opt2=opt2;
        this.product=product;
    }

}
