package com.example.shop.entity;

import com.example.shop.dto.OrderRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order,Long> {

     @Query(value = "SELECT o FROM Order o WHERE o.product.proId = :proId AND o.member.id = :id AND o.ordchk = :check")
     Order hasFavorite(@Param("proId") Long proId, @Param("id") Long id ,@Param("check")String check);
     
     @Query(value = "SELECT o FROM Order o  WHERE o.member.id = :id AND o.ordchk='장바구니'")
     List<Order> findOrderById(@Param("id")Long id);

     @Modifying(clearAutomatically = true)
     @Query(value ="update Order o SET o.ordchk = :#{#dto.ordchk},o.ordquantity = :#{#dto.ordquantity},o.ordhuname = :#{#dto.ordhuname}, o.ordaddress = :#{#dto.ordaddress} WHERE o.ordid = :#{#dto.ordid}" )
     void modifyCartAndBuy(@Param("dto")OrderRequest dto);

     @Query(value = "SELECT o FROM Order o left join fetch o.product p left join fetch p.img i WHERE o.member.id = :id AND p.member.id = :id AND o.ordchk='구매' AND i.imgtype='ProductImg' GROUP BY o.ordid")
     List<Order> findOrderByOrdChk(Long id);

}
