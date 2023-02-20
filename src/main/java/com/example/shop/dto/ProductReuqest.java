package com.example.shop.dto;


import com.example.shop.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReuqest {
    private String pro_name;
    private int pro_price;
    private String email;
    private List<MultipartFile> imgList;

    public Product productEntity() {
        return Product.builder().pro_name(pro_name).pro_price(pro_price).email(email).build();
    }
}
