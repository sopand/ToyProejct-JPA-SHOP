package com.example.shop.entity;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImgRepository extends JpaRepository<Img,Long> {

    @Query(nativeQuery = true ,value = "select * from Img i WHERE i.pro_id = :proId AND i.img_type='ProductImg' LIMIT 1")
    Img findFirstByImgByProId(Long proId);

}
