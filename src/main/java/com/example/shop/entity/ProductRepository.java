package com.example.shop.entity;


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

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' GROUP BY p.proid",
            countQuery = "select count(p.proid) from Product p")
    Page<Product> findAll(Pageable page);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option o WHERE p.proid = :proid")
    Product findByProduct(Long proid);


    Product findByProid(Long proid);
}
