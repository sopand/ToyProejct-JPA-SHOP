package com.example.shop.dto;


import com.example.shop.entity.Member;
import com.example.shop.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReuqest {
    private String proname;
    private int proprice;
    private Member member;
    private List<MultipartFile> imgList;

    public Product productEntity() {
        return Product.builder().proname(proname).proprice(proprice).member(member).build();
    }
}
