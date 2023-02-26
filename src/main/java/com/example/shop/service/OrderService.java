package com.example.shop.service;

import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OptionRepository optionRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createOrder(OrderRequest request, Long id) {

        Member member = Member.builder().id(id).build();
        Product product = Product.builder().proid(request.getProid()).build();
        if(!request.getOrdchk().equals("찜하기")){
            Option option = Option.builder().optid(request.getOptid()).build();
           return orderRepository.save(request.create(product,member,option)).getOrdid();
        }else{
           return orderRepository.save(request.favorite(product,member)).getOrdid();
        }

    }
    public OrderResponse hasFavorite(Long proid, Long id){
        Order order=orderRepository.findOrderByProid(proid,id);
        if(order !=null){
            return new OrderResponse(order);
        }
        return null;
    }

    public void deleteFavorite(Long ordid){
         orderRepository.deleteById(ordid);

    }

}
