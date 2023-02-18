package com.example.shop.service;

import com.example.shop.dto.MemberRequest;
import com.example.shop.entity.Member;
import com.example.shop.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void createMember(MemberRequest request){
        request.setAddress(request.memberAddr(request.getAddr1(), request.getAddr2(), request.getAddr3()));
        request.setTel(request.memberTel(request.getTel1(),request.getTel2(),request.getTel3()));
        System.out.println("request = " + request);
        memberRepository.save(request.memberEntity());
    }
}
