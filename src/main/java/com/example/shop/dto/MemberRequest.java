package com.example.shop.dto;

import com.example.shop.entity.Member;
import lombok.*;

@Data
@NoArgsConstructor
public class MemberRequest {
    private String email;
    private String pwd;
    private String tel;
    private String address;

    private String tel1, tel2, tel3;
    private String addr1, addr2, addr3;



    public String memberTel(String tel1, String tel2, String tel3) {
        return tel1 + "-" + tel2 + "-" + tel3;
    }

    public String memberAddr(String addr1, String addr2, String addr3) {
        return "(우편번호:" + addr1 + ")" + addr2 + "(상세주소 :" + addr3+")";
    }

    public Member memberEntity() {
        return Member.builder().email(email).pwd(pwd).tel(tel).address(address).build();
    }


}
