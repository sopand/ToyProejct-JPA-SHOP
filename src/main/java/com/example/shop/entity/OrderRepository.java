package com.example.shop.entity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {



    Optional<Order> findByProductProIdAndMemberIdAndOrdchk(Long prdId,Long id,String check);
     
     @Query(value = "SELECT o FROM Order o  WHERE o.member.id = :id AND o.ordchk='장바구니'")
     List<Order> findOrderById(@Param("id")Long id);



     Optional<Order> findByOrdid(Long ordId);



}
