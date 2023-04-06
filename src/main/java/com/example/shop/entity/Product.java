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
    private Long proId;
    private String proName;
    private int proPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Member member;

    private String proCategory;



    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @OrderBy("imgid asc")
    private List<Img> img;
    @Temporal(TemporalType.TIMESTAMP)
    private Date proDate;


    @OneToMany(mappedBy ="product",cascade = CascadeType.ALL)
    @OrderBy("optid asc")
    private Set<Option> option=new LinkedHashSet<>();


    @Builder
    public Product(Long proId,String proName,int proPrice,String proCategory,Member member){
        this.proName=proName;
        this.proId=proId;
        this.proPrice=proPrice;
        this.proDate=new Date();
        this.proCategory=proCategory;
        this.member=member;
    }
}
