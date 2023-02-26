package com.example.shop.service;

import com.example.shop.dto.OrderRequest;
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
    public void createBuy(OrderRequest request, Long id) {
        Option option = Option.builder().optid(request.getOptid()).build();
        Member member = Member.builder().id(id).build();
        Product product = Product.builder().proid(request.getProid()).build();
        orderRepository.save(Order.builder().option(option).member(member).product(product).ordquantity(request.getOrd_quantity()).build());
    }
}
