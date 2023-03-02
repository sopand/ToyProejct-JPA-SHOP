package com.example.shop.service;

import com.example.shop.dto.ImgResponse;
import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final ImgRepository imgRepository;

    @Transactional
    public Long createOrder(OrderRequest request, Long id) {
        Member member = Member.builder().id(id).build();
        Product product = Product.builder().proid(request.getProid()).build();
        switch (request.getOrdchk()) {
            case "장바구니":
                Option option = Option.builder().optid(request.getOptid()).build();
                return orderRepository.save(request.create(product, member, option)).getOrdid();
            case "구매":
                Option option2 = Option.builder().optid(request.getOptid()).build();
                return orderRepository.save(request.directbuy(product, member, option2)).getOrdid();
            case "찜하기":
                return orderRepository.save(request.favorite(product, member)).getOrdid();
            default:
                return null;
        }
    }

    public String hasFavorite(Long proid, Long id, String check) {
        Order order = orderRepository.hasFavorite(proid, id, check);
        if (order != null) {
            if (check.equals("장바구니")) {
                return "이미 장바구니에 존재하는 제품입니다";
            } else {
                return "Has Favorite";
            }
        }
        return null;
    }

    public void deleteFavorite(Long ordid) {
        orderRepository.deleteById(ordid);
    }

    public List<OrderResponse> findCarts(Long id) {
        List<OrderResponse> carts = orderRepository.findOrderById(id).stream().map(OrderResponse::new).toList();
        List<ImgResponse> imgs = new ArrayList<>();
        carts.stream().forEach(order ->
                order.setImgs(new ImgResponse(imgRepository.findFirstByImgByProId(order.getProduct().getProid())))
        );

        return carts;
    }

    @Transactional
    public void modifyCartAndBuy(OrderRequest request) {
        for (int i = 0; i < request.getOrdidList().size(); i++) {
            request.setOrdid(request.getOrdidList().get(i));
            request.setOrdquantity(request.getQuantityList().get(i));
            orderRepository.modifyCartAndBuy(request);
        }
    }

    @Transactional
    public void deleteCarts(List<Long> ordid) {
        ordid.stream().forEach(id -> orderRepository.deleteById(id));
    }

    public List<OrderResponse> findDelivery(Long id){
        List<OrderResponse> delivery=orderRepository.findOrderByOrdChk(id).stream().map(OrderResponse::new).toList();
        System.out.println();
        return delivery;
    }

}
