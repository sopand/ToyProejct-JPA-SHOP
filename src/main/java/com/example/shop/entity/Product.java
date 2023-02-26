package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Many;
import org.hibernate.annotations.BatchSize;

import java.time.LocalDate;
import java.util.*;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Member member;

    @Column(name="pro_category")
    private String procategory;



    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @OrderBy("imgid asc")
    private Set<Img> img=new LinkedHashSet<>();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="pro_date")
    private Date prodate;


    @OneToMany(mappedBy ="product",cascade = CascadeType.ALL)
    @OrderBy("optid asc")
    private Set<Option> option=new LinkedHashSet<>();


    @Builder
    public Product(Long proid,String proname,int proprice,Long id,String procategory,Member member){
        this.proname=proname;
        this.proid=proid;
        this.proprice=proprice;
        this.prodate=new Date();
        this.procategory=procategory;
        this.member=member;
    }
}
