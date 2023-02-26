package com.example.shop.controller;


import com.example.shop.dto.OrderRequest;
import com.example.shop.entity.OrderRepository;
import com.example.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @ResponseBody
    @PostMapping
    public String createBuy(OrderRequest order, HttpSession session) {
        Long id=(Long)session.getAttribute("id");
        orderService.createOrder(order,id);
        
        return "추가 완료";
    }

}
