package com.example.shop.service;

import com.example.shop.dto.ImgResponse;
import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ImgRepository imgRepository;
    @Transactional
    public Long createOrder(OrderRequest request, Long id) {
        Member member = Member.builder().id(id).build();
        Product product = Product.builder().proId(request.getProId()).build();
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

    public Long hasFavorite(Long proid, Long id, String check) {
        Order order = orderRepository.findByProductProIdAndMemberIdAndOrdchk(proid, id, check).orElseGet(()->null);

        return order==null ?  null:  order.getOrdid();


    }

    public void deleteFavorite(Long ordid) {
        orderRepository.deleteById(ordid);
    }

    public List<OrderResponse> findCarts(Long id) {
        List<OrderResponse> carts = orderRepository.findOrderById(id).stream().map(OrderResponse::new).toList();
        carts.stream().forEach(order ->
                order.setImgs(new ImgResponse(imgRepository.findFirstByImgByProId(order.getProduct().getProId())))
        );
        return carts;
    }

    @Transactional
    public void BuyFromCart(OrderRequest request){
        AtomicInteger count= new AtomicInteger();
            request.getOrdidList().stream().forEach(entity->{
                request.setOrdid(entity);
                request.setOrdquantity(request.getQuantityList().get(count.getAndIncrement()));
                Order order=orderRepository.findByOrdid(request.getOrdid()).orElseThrow(()-> new NoSuchElementException("찾을수 주문 정보입니다 "));
                order.modifyOrderEntity(request);
                    }
            );
    }

    @Transactional
    public void deleteCarts(List<Long> ordid) {
        ordid.stream().forEach(id -> orderRepository.deleteById(id));
    }

    public List<OrderResponse> findDelivery(Long id){
        return orderRepository.findOrderByOrdChk(id).stream().map(OrderResponse::new).toList();
    }



}
