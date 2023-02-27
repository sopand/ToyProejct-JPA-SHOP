package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED )
@Getter
public class Repro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rep_id")
    private Long rep_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Member member;

    private String rep_text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date rep_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Product product;

    private String rep_chk;

    private String rep_select;

    @Builder
    public Repro(Long rep_id,Member member, Product product,String rep_text,String rep_chk,String rep_select){
        this.rep_id=rep_id;
        this.rep_text=rep_text;
        this.product=product;
        this.rep_chk=rep_chk;
        this.rep_date=new Date();
        this.rep_select=rep_select;
        this.member=member;
    }
}
