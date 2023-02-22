package com.example.shop.controller;


import com.example.shop.dto.OrderRequest;
import com.example.shop.entity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {


    @PostMapping
    public String createOrder(OrderRequest order) {

        return "null";
    }

}
