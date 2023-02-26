package com.example.shop.controller;


import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.entity.OrderRepository;
import com.example.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @ResponseBody
    @PostMapping
    public Long createOrder(OrderRequest order, HttpSession session) {
        Long id=(Long)session.getAttribute("id");
        return  orderService.createOrder(order,id);
    }
    @ResponseBody
    @GetMapping("/favorite")
    public OrderResponse hasFavorite(HttpSession session, Long proid){
        Long id=(Long)session.getAttribute("id");
        return orderService.hasFavorite(proid,id);
    }



    @ResponseBody
    @DeleteMapping("/favorite")
    public void deleteFavorite(HttpSession session,OrderRequest request){
        Long id=(Long)session.getAttribute("id");
        orderService.deleteFavorite(request.getOrdid());
    }
}
