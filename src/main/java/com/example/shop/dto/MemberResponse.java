package com.example.shop.dto;

import com.example.shop.entity.Member;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MemberResponse {
    private String email;
    private String pwd;
    private String tel;
    private String address;

    private Long id;
    public MemberResponse(Member entity){
        this.email=entity.getEmail();
        this.pwd=entity.getPwd();
        this.tel=entity.getTel();
        this.address=entity.getAddress();
        this.id=entity.getId();
    }
}
