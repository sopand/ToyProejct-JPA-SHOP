package com.example.shop.dto;


import com.example.shop.entity.Member;
import com.example.shop.entity.Option;
import com.example.shop.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String proName;

    private Long proId;
    private int proPrice;

    private String proCategory;
    private Member member;
    private String opt1;
    private List<String> opt2;
    private List<Integer> optquantity;
    private List<MultipartFile> imgList;
    private List<MultipartFile> textimgList;



    public Product productEntity(Long id) {
        this.member=MemberRequest.addMemberIdEntity(id);
        return Product.builder().proName(proName).proPrice(proPrice).proCategory(proCategory).member(member).build();
    }
    public Option fullOptionEntity(String opt1,String opt2,int optquantity,Long proId){
        return  Option.builder().opt1(opt1).opt2(opt2).optquantity(optquantity).product(addProductIDEntity(proId)).build();
    }
    public Option opt1NoOptionEntity(String opt2,int optquantity,Long proId){
        return  Option.builder().opt2(opt2).optquantity(optquantity).product(addProductIDEntity(proId)).build();
    }

    public Product addProductIDEntity(Long proId){
        return Product.builder().proId(proId).build();
    }
}
