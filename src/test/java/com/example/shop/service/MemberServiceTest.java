package com.example.shop.service;

import com.example.shop.dto.MemberRequest;
import com.example.shop.entity.Member;
import com.example.shop.entity.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

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
        service.createMember(memberRequest);
        //then
    }
}