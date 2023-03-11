package com.example.shop.service;

import com.example.shop.dto.MemberRequest;
import com.example.shop.dto.MemberResponse;
import com.example.shop.entity.Member;
import com.example.shop.entity.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(MemberRequest request){
        return memberRepository.save(request.memberEntity()).getEmail();
    }

    public MemberResponse hasMember(MemberRequest request){
           return memberRepository.findByEmail(request.getEmail())
                .filter(member -> member.getPwd().equals(request.getPwd()))
                   .map(member -> new MemberResponse(member)).orElse(null);
    }
}
