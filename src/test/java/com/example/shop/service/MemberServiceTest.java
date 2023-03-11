package com.example.shop.service;

import com.example.shop.dto.MemberRequest;
import com.example.shop.dto.MemberResponse;
import com.example.shop.entity.Member;
import com.example.shop.entity.MemberRepository;
import com.example.shop.entity.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
class MemberServiceTest {

    @InjectMocks
    private MemberService service;

    @Mock
    private MemberRepository repository;

    @Test
    public void createMember(){
        //given
        MemberRequest memberRequest= new MemberRequest();
        memberRequest.setEmail("aaa");
        memberRequest.setPwd("bbb");
        //stub

        when(repository.save(any())).thenReturn(memberRequest.memberEntity());

        //when
        String email=service.createMember(memberRequest);
        //then
        assertThat(memberRequest.getEmail()).isEqualTo(email);
    }

    @Test
    public void findMember(){
        //given
        MemberRequest request=new MemberRequest();
        request.setEmail("aaa");
        request.setPwd("bbb");
        //stub
        Optional<Member> member=Optional.of(Member.builder().email("aaa").pwd("bbb").build());
        when(repository.findByEmail(request.getEmail())).thenReturn(member);

        //when
        MemberResponse response=service.hasMember(request);
        //then
        assertThat(response.getEmail()).isEqualTo(response.getEmail());
    }
}