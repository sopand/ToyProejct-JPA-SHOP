package com.example.shop.entity;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p from Product p left join fetch p.img",
            countQuery = "select count(p.proid) from Product p")
    Page<Product> findAll(Pageable page);
}
