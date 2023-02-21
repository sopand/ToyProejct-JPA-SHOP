package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;

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
    @Column(name = "pro_id")
    private Long proid;

    @Column(name = "pro_name")
    private String proname;

    @Column(name = "pro_price")
    private int proprice;
    @ManyToOne
    @JoinColumn(name="id")
    private Member member;


    @OneToMany(mappedBy = "product")
    private List<Img> img=new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pro_date")
    private Date prodate;


    @Builder
    public Product(String proname,int proprice,Long id,Member member){
        this.proname=proname;
        this.proprice=proprice;
        this.prodate=new Date();
        this.member=member;
    }
}
