package com.example.shop.controller;

import com.example.shop.dto.MemberRequest;
import com.example.shop.dto.MemberResponse;
import com.example.shop.entity.Member;
import com.example.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String loadMemberForm() {

        return "addmember";
    }

    @PostMapping("")
    public String createMember(MemberRequest request) {
        memberService.createMember(request);
        return "index";
    }

    @PostMapping("/login")
    public String hasMember(MemberRequest request, HttpServletRequest req) {
        HttpSession session = req.getSession();
        MemberResponse findMember = memberService.hasMember(request);
        if (findMember !=null) {
            session.setAttribute("email", findMember.getEmail());
            return "redirect:/index";
        }else{
            return "login";
        }


    }
    @PostMapping("/logout")
    public String deleteLogin(String email,HttpSession session){

        session.removeAttribute("email");
        return "index";
    }

}
