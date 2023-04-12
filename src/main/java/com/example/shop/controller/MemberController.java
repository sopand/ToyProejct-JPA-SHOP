package com.example.shop.controller;

import com.example.shop.dto.MemberRequest;
import com.example.shop.dto.MemberResponse;
import com.example.shop.service.EmailService;
import com.example.shop.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 유저와 관련된 기능을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/members")// "/members/ ~~~URL 에 통일성을 주기 위해 RequestMapping 처리
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    public static Long memberId;

    private final EmailService emailService;

    /**
     * 유저 생성을 위한 From 페이지로 이동시켜주는 맵핑
     *
     * @return
     */
    @GetMapping("")
    public String loadMemberForm() {
        return "addmember";
    }

    /**
     * 유저 생성의 기능을 담당하는 맵핑
     *
     * @param request = 사용자가 입력한 유저에 대한 데이터가 저장되어있는 객체
     * @return = 회원가입 이후 메인페이지로 이동 하기 위한 index
     */
    @PostMapping("")
    public String createMember(MemberRequest request) {
        memberService.createMember(request);
        return "index";
    }

    /**
     * 유저의 로그인 기능을 담당하는 맵핑,
     * @param request = 유저가 로그인을 위해 입력한 PWD,ID가 들어있는 객체
     * @return = 로그인에 성공했다면 메인페이지로 아니라면 login페이지로 다시 이동합니다.
     */

    @PostMapping("/login")
    public String hasMember(MemberRequest request,HttpSession session) {
        MemberResponse findMember = memberService.hasMember(request);
        if (findMember != null) {
            session.setAttribute("email", findMember.getEmail());
            session.setAttribute("id", findMember.getId());
            memberId=findMember.getId();
            return "redirect:/index";
        }
        return "login";

    }

    /**
     * 유저의 로그인 정보를 없애기 위한 로그아웃기능을 하는 맵핑입니다.
     * @param email = 유저의 이메일 정보
     * @param session = 로그인 세션을 삭제하기 위함
     * @return
     */
    @PostMapping("/logout")
    public String deleteLogin(String email, HttpSession session) {
        session.removeAttribute("email");
        session.removeAttribute("id");
        return "index";
    }


    /**
     * 동일한 이메일의 존재유무를 파악하고 이메일 인증코드를 발송해주는 맵핑입니다.
     * @param email = 사용자가 입력한 이메일 데이터
     * @return = 존재하지 않는 이메일이라면 인증코드가 전송 , 존재 한다면 Null을 반환
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/email/checking")
    public String createEmailCode(String email) throws Exception {
        MemberResponse getEmailAddChk=memberService.findMember(email);
        if(getEmailAddChk==null){
            return emailService.sendSimpleMessage(email);
        }
        return null;
    }

}
