package com.example.shop.entity;

import com.example.shop.dto.MemberRequest;
import com.example.shop.dto.MemberResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void createMember(){
        //given
        Member mre=Member.builder().email("cd").pwd("dd").build();

        //when
        Member member=memberRepository.save(mre);

        //then
        assertThat(member.getEmail()).isEqualTo(mre.getEmail());
    }

    @Test
    public void findByEmail(){
        //given
        String email="cds";

        //when
        Member member=memberRepository.findByEmail(email).get();

        //then
        assertThat(email).isEqualTo(member.getEmail());
        System.out.println(member.getEmail());
    }
}