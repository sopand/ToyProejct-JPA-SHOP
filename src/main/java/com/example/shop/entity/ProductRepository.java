package com.example.shop.entity;


import com.example.shop.dto.ProductResponse;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findAllProduct(Pageable page);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.proCategory = :proCategory GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> fidByCategory(Pageable page,String proCategory);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.member.id = :id AND p.member.email = :email GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findAllByid(Pageable page ,Long id,String email);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.member.id = :id AND p.member.email = :email AND p.proName = :search GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findSearch(Pageable page , Long id, String email, String search);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option o WHERE p.proId = :proId")
    Product findByProduct(Long proId);


    Product findByProId(Long proId);


}
