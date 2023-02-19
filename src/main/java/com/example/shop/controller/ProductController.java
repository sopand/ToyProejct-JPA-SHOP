package com.example.shop.controller;


import com.example.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createProduct(){

        return "index";
    }
}
