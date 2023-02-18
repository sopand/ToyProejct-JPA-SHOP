package com.example.shop.controller;

import com.example.shop.dto.MemberRequest;
import com.example.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("")
    public String loadMemberForm(){

        return "addmember";
    }

    @PostMapping("")
    public String createMember(MemberRequest request){
        memberService.createMember(request);
        return "index";
    }
}
