package com.example.shop.service;

import com.example.shop.dto.ProductReuqest;
import com.example.shop.entity.ImgRepository;
import com.example.shop.entity.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{

    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;

    public void createProduct(ProductReuqest request){
        productRepository.save(request.productEntity());
    }

    public void createImg(List<MultipartFile> filelist){
        for(MultipartFile file:filelist){
            if(!file.isEmpty()){

            }
        }
    }
}
