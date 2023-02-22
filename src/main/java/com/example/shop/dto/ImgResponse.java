package com.example.shop.dto;

import com.example.shop.entity.Img;
import com.example.shop.entity.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImgResponse {
    private String img_name;
    private String img_original;
    private Long pro_id;

    private Long img_id;
    public ImgResponse(Img entity){
        this.img_name=entity.getImgname();
        this.pro_id=entity.getProduct().getProid();
        this.img_original=entity.getImgoriginal();
        this.img_id=entity.getImgid();
    }
}
