package com.example.shop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 여러 기능들에 해당하지 않는 간단 폼들에대한 이동을 담당하는 컨트롤러
 */
@Controller
public class IndexController {

    /**
     * 메인페이지로 이동하기 위한 맵핑
     * @return
     */
    @GetMapping({"/", "/index"})
    public String loadMain() {
        return "index";
    }

    /**
     * 로그인 페이지로 이동하기 위한 맵핑
     * @return
     */
    @GetMapping("/login")
    public String loadLogin(){
        return "login";
    }
}
