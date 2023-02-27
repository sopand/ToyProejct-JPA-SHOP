package com.example.shop.dto;

import com.example.shop.entity.Member;
import com.example.shop.entity.Product;
import com.example.shop.entity.Repro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReproRequest {
    private String rep_chk;
    private Long proid;

    private Long id;
    private String rep_select;
    private String rep_text;


    public Repro toEntity(){
        Member member= Member.builder().id(id).build();
        Product pro= Product.builder().proid(proid).build();
        return Repro.builder().rep_chk(rep_chk).rep_select(rep_select).rep_text(rep_text).product(pro).member(member).build();
    }
}
