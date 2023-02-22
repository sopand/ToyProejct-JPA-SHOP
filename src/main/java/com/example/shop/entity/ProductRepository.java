package com.example.shop.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select DISTINCT p from Product p left join fetch p.img GROUP BY p.proid",
            countQuery = "select count(p.proid) from Product p")
    Page<Product> findAll(Pageable page);

    @Query(value = "select p from Product p left join fetch p.img WHERE p.proid = :proid")
    Product findByProid(Long proid);
}
