package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
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
    public Option(Long optid,int optquantity,String opt1,String opt2,Product product){
        this.optid=optid;
        this.optquantity=optquantity;
        this.opt1=opt1;
        this.opt2=opt2;
        this.product=product;
    }

    public void modifyOptionQuantity(int quantity){
        System.out.println("기존 갯수"+optquantity +"뺄 갯수"+quantity);
        this.optquantity=optquantity-quantity;
    }

}
