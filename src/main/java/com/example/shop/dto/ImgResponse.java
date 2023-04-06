package com.example.shop.dto;

import com.example.shop.entity.Img;
import com.example.shop.entity.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImgResponse {
    private String imgname;
    private String imgoriginal;
    private Long proid;
    private Long imgid;

    private String imgtype;
    public ImgResponse(Img entity){
        this.imgname=entity.getImgname();
        this.proid=entity.getProduct().getProId();
        this.imgoriginal=entity.getImgoriginal();
        this.imgid=entity.getImgid();
        this.imgtype=entity.getImgtype();
    }

}
