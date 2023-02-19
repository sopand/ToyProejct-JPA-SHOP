package com.example.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String pro_name;
    @Builder
    public Img(String img_name,String img_original,String pro_name){
        this.img_name=img_name;
        this.img_original=img_original;
        this.pro_name=pro_name;
    }
}
