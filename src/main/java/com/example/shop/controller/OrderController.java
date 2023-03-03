package com.example.shop.controller;


import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.service.OrderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/favorite/{check}")
    public String hasFavorite(HttpSession session, Long proid,@PathVariable("check") String check){
        Long id=(Long)session.getAttribute("id");
        return orderService.hasFavorite(proid,id,check);
    }
    @ResponseBody
    @DeleteMapping("/favorite")
    public void deleteFavorite(HttpSession session,OrderRequest request){
        Long id=(Long)session.getAttribute("id");
        orderService.deleteFavorite(request.getOrdid());
    }

    @GetMapping("/cart")
    public String findCarts(HttpSession session, Model model){
        Long id=(Long)session.getAttribute("id");
        List<OrderResponse> carts=orderService.findCarts(id);
        model.addAttribute("carts",carts);
        return "cart";
    }

    @ResponseBody
    @PutMapping("/cart")
    public String modifyCartAndBuy(OrderRequest request){
        orderService.modifyCartAndBuy(request);
        return "구매완료";
    }

    @ResponseBody
    @DeleteMapping("/cart")
    public String deleteCarts(HttpSession session,OrderRequest request){
        orderService.deleteCarts(request.getOrdidList());

        return "장바구니 제거완료";
    }
    @GetMapping("/drives")
    public String findOrdersParcel(HttpSession session,Model model){
        Long id=(Long)session.getAttribute("id");
        model.addAttribute("delivery", orderService.findDelivery(id));
        return "post";
    }

}
