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

    /**
     * 모든 제품에 대한 리스트를 가져오는 쿼리, 페이징 처리를 위해 Pageable객체를 인자로 받는다
     * @param page = 페이징에 대한 설정 값이 들어있는 객체,
     * @return = Product를 ProductReponse객체로 리턴하고있다.
     */
    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findAllProduct(Pageable page);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.proCategory = :proCategory GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findByCategory(Pageable page,String proCategory);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.member.id = :id  GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findAllByid(Pageable page ,Long id);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.member.id = :id AND p.proName = :search GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findSellerProductSearch(Pageable page , Long id,String search);


    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option where i.imgtype='ProductImg' AND p.proName LIKE %:search% GROUP BY p.proId",
            countQuery = "select count(p.proId) from Product p")
    Page<ProductResponse> findBySearchProducts(Pageable page,String search);

    @Query(value = "select p from Product p left join fetch p.img i left join fetch p.option o WHERE p.proId = :proId")
    Product findByProduct(Long proId);




}
