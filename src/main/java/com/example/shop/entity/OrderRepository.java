package com.example.shop.entity;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {

     @Query(value = "SELECT o FROM Order o WHERE o.product.proid = :proid AND o.member.id = :id AND o.ordchk='찜하기' ")
     Order findOrderByProid(@Param("proid") Long proid, @Param("id") Long id);
}
