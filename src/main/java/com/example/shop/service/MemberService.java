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

    /**
     * 멤버를 생성하기위한 로직 
     * @param request = 사용자가 생성하려는 아이디에 대한 정보가 들어있는 객체 
     * @return = 생성된 아이디의 Email을 리턴함 ( 생성성공 여부 )
     */
    @Transactional
    public String createMember(MemberRequest request){
        return memberRepository.save(request.memberEntity()).getEmail();
    }

    /**
     * 로그인기능을 작동하기 위한 로직
     * @param request = 로그인창에 사용자가 입력한 이메일과 패스워드를 저장하고 있는 객체
     * @return = 해당이메일로 찾아온 데이터가 존재하면 패스워드를 비교하여 DB의 패스워드와 입력한 패스워드가 동일하면 member응답객체를 리턴
     * 아니라면 null을 리턴
     */
    public MemberResponse hasMember(MemberRequest request){
           return memberRepository.findByEmail(request.getEmail())
                .filter(member -> member.getPwd().equals(request.getPwd()))
                   .map(member -> new MemberResponse(member)).orElse(null);
    }
}
