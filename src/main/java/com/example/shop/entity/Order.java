package com.example.shop.entity;

import com.example.shop.dto.OrderRequest;
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

    @Column(name="ord_address")
    private String ordaddress;
    @Column(name = "ord_huname")
    private String ordhuname;

    @Column(name="ord_post_code")

    private Long ordpostCode;

    @Column(name="ord_post_company_key")
    private String ordpostCompanyKey;

    @Builder
    public Order(Option option,Product product,Member member,int ordquantity, String ordchk,String ordaddress,String ordhuname,String ordpostCompanyKey,Long ordpostCode){
        this.product=product;
        this.option=option;
        this.member=member;
        this.ordchk=ordchk;
        this.orddate=new Date();
        this.ordquantity=ordquantity;
        this.ordaddress=ordaddress;
        this.ordhuname=ordhuname;
        this.ordpostCompanyKey=ordpostCompanyKey;
        this.ordpostCode=ordpostCode;
    }


    public void modifyOrderEntity(OrderRequest request){
        this.ordchk=request.getOrdchk();
        this.ordquantity=request.getOrdquantity();
        this.ordhuname=request.getOrdhuname();
        this.ordaddress=request.getOrdaddress();
    }






}
