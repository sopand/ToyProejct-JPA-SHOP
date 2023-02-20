package com.example.shop.controller;


import com.example.shop.dto.ProductReuqest;
import com.example.shop.entity.Product;
import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("")
    public String loadProudctAdd(){
        return "productadd";
    }
    @PostMapping("")
    public String createProduct(ProductReuqest request) throws IOException {
        productService.createProduct(request);
        return "index";
    }
    @GetMapping("/list")
    public String findProducts(){
        PageRequest page = PageRequest.of(0, 10, Sort.by("pro_id").descending());
        productService.findProducts(page);
        return "productlist";
    }
}
