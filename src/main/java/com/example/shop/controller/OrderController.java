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

import static com.example.shop.controller.MemberController.memberId;

/**
 * 주문과 관련된 모든 처리를 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    /**
     * 주문을 생성하는 기능을 담당하는 맵핑
     * @param order = 주문에 대한 정보가 저장되어있는 객체
     * @return = 제대로 주문이 되었는지 확인하기위한 생성된 Order의 고유번호를 리턴
     * @throws Exception
     */
    @ResponseBody
    @PostMapping
    public Long createOrder(OrderRequest order) throws Exception {
        return  orderService.createOrder(order,memberId);
    }

    /**
     * 제품의 좋아요여부를 체크하기 위한 맵핑.
     * @param proId
     * @param check
     * @return
     */
    @ResponseBody
    @GetMapping("/favorite/{check}")
    public Long hasFavorite(Long proId,@PathVariable("check") String check){
        return orderService.hasFavorite(proId,memberId,check);
    }
    @ResponseBody
    @DeleteMapping("/favorite")
    public void deleteFavorite(OrderRequest request){
        orderService.deleteFavorite(request.getOrdid());
    }

    @GetMapping("/cart")
    public String findCarts( Model model){
        List<OrderResponse> carts=orderService.findCarts(memberId);
        model.addAttribute("carts",carts);
        return "cart";
    }

    @ResponseBody
    @PutMapping("/cart")
    public String BuyFromCart(OrderRequest request){
        orderService.BuyFromCart(request);
        return "구매완료";
    }

    @ResponseBody
    @DeleteMapping("/cart")
    public String deleteCarts(OrderRequest request){
        orderService.deleteCarts(request.getOrdidList());

        return "장바구니 제거완료";
    }


}
