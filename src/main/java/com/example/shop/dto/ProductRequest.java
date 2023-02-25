package com.example.shop.dto;


import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
import com.example.shop.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String proname;

    private Long proid;
    private int proprice;

    private String procategory;
    private Member member;
    private String opt1;
    private List<String> opt2;
    private List<Integer> optquantity;
    private List<MultipartFile> imgList;
    private List<MultipartFile> textimgList;



    public Product productEntity() {
        return Product.builder().proname(proname).proprice(proprice).procategory(procategory).member(member).build();
    }
}
