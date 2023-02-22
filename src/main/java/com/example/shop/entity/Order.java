package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ord")
public class Order {

    @Id
    @Column(name = "ord_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Member member;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ord_date")
    private Date orddate;

    @Column(name = "ord_quantity")
    private int ordquantity;

    @Builder
    public Order(Product product,Member member,int ordquantity){
        this.product=product;
        this.member=member;
        this.ordquantity=ordquantity;

    }



}
