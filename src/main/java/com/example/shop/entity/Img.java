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
    private Long img_id;

    private String img_name;
    private String img_original;

    @ManyToOne
    @JoinColumn(name = "pro_id")
    private Product product;

    @Builder
    public Img(String img_name,String img_original,Product product){
        this.img_name=img_name;
        this.img_original=img_original;
        this.product=product;
    }

}
