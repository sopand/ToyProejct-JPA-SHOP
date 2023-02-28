package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ord")
public class Order {

    @Id
    @Column(name = "ord_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordid;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pro_id")
    private Product product;


    @OneToOne
    @JoinColumn(name="optid")
    private Option option;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    private Member member;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ord_date")
    private Date orddate;

    @Column(name = "ord_quantity")
    private int ordquantity;

    @Column(name="ord_chk")
    private String ordchk;

    @Builder
    public Order(Option option,Product product,Member member,int ordquantity, String ordchk){
        this.product=product;
        this.option=option;
        this.member=member;
        this.ordchk=ordchk;
        this.orddate=new Date();
        this.ordquantity=ordquantity;

    }



}
