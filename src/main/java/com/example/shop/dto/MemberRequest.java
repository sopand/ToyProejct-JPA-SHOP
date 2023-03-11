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

    public Member memberEntity() {

        return Member.builder()
                .email(email)
                .pwd(pwd)
                .tel( tel1 + "-" + tel2 + "-" + tel3)
                .address("(우편번호:" + addr1 + ")" + addr2 + "(상세주소 :" + addr3+")").build();
    }


}
