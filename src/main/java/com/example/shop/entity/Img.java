package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgid;


    @Column(name = "img_name")
    private String imgname;

    @Column(name = "img_original")
    private String imgoriginal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pro_id")
    private Product product;

    @Builder
    public Img(String imgname,String imgoriginal,Product product){
        this.imgname=imgname;
        this.imgoriginal=imgoriginal;
        this.product=product;
    }

}
