package com.example.shop.controller;


import com.example.shop.dto.PagingList;
import com.example.shop.dto.ProductRequest;
import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.ReproRequest;
import com.example.shop.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("")
    public String loadProudctAdd() {
        return "productadd";
    }

    @PostMapping("")
    public String createProduct(ProductRequest request, HttpSession session) throws IOException {
        Long proId = productService.createProduct(request, (Long) session.getAttribute("id"));
        return "redirect:/products/option/" + proId;
    }

    @GetMapping("/list")
    public String findProducts(Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findProducts(pageable);
        model.addAttribute("pagingProducts", pagingProducts);
        return "productlist";
    }

    @GetMapping("/list/{procategory}")
    public String findByCategoryProducts(@PathVariable String procategory, Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findByCategoryProducts(pageable,procategory);
        model.addAttribute("pagingProducts", pagingProducts);
        return "productlist";
    }
    @GetMapping("/list/search")
    public String findBySearchProducts(String search, Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findBySearchProducts(pageable,search);
        model.addAttribute("pagingProducts", pagingProducts);

        return "productlist";
    }

    @GetMapping("/{proid}")
    public String findProduct(@PathVariable Long proid, Model model) {
        ProductResponse findProduct = productService.findProduct(proid);
        model.addAttribute("findProduct", findProduct);
        return "productdetail";
    }

    @GetMapping("/option/{proid}")
    public String leadOptionForm(@PathVariable Long proid, Model model) {
        model.addAttribute("proid", proid);
        return "optadd";
    }


    @ResponseBody
    @PostMapping("/option")
    public String createOption(ProductRequest request) {
        productService.createOption(request);
        return "성공";
    }

    @PostMapping("/return")
    public String createRepro(ReproRequest request, HttpSession session) {
        request.setId((Long) session.getAttribute("id"));
        productService.createRepro(request);
        return "redirect:/products/" + request.getProId();
    }

    @GetMapping("/list/{id}/{email}")
    public String findSeller(String search, Model model, @PathVariable("id") Long id, @PathVariable("email") String email, @PageableDefault(page = 0, size = 5, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts;
        if (search == null) {
            pagingProducts = productService.findSeller(id, email, pageable);
        } else {
            pagingProducts = productService.findSellerSearch(id, email, pageable, search);
        }
        model.addAttribute("pagingProducts", pagingProducts);
        return "seller";
    }

}
