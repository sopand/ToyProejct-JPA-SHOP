package com.example.shop.controller;


import com.example.shop.dto.ImgResponse;
import com.example.shop.dto.MemberResponse;
import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.ProductReuqest;
import com.example.shop.entity.Img;
import com.example.shop.entity.Product;
import com.example.shop.service.MemberService;
import com.example.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final MemberService memberService;
    @GetMapping("")
    public String loadProudctAdd(){
        return "productadd";
    }
    @PostMapping("")
    public String createProduct(ProductReuqest request,HttpSession session) throws IOException {
        productService.createProduct(request,(Long)session.getAttribute("id"));

        return "index";
    }
    @GetMapping("/list")
    public String findProducts(Model model,@PageableDefault(page = 0,size =9 ,sort ="proid",direction = Sort.Direction.DESC) Pageable pageable){
        Map<String,Object> pagingProducts= productService.findProducts(pageable);
        System.out.println("pa = " + pageable.getPageNumber());

        model.addAttribute("pagingProducts",pagingProducts);
        return "productlist";
    }

    @GetMapping("/{proid}/detail")
    public String findProduct(@PathVariable Long proid,Model model){
            ProductResponse findProduct=productService.findProduct(proid);
            model.addAttribute("findProduct",findProduct);
            return "productdetail";
    }
}
