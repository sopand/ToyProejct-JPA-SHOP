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

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    /**
     * 제품판매 글을 등록해주는 페이지로 이동시켜주는 맵핑
     * @return = productadd.html View로 이동
     */
    @GetMapping("")
    public String loadProudctAdd() {
        return "productadd";
    }

    /**
     * 제품판매글 등록 페이지에서 입력한 데이터를 DB에 생성시켜주기 위한 맵핑
     * @param request = 사용자가 입력한 게시글의 데이터가 들어있는 객체
     * @param session = 해당 사용자의 로그인 아이디를 가져오기 위한 세션객체
     * @return = ADD기능은 forward방식으로 페이지전환시 F5를 클릭하면 등록한 객체가 무한반복 될 수 있는 오류가 있어 redirec로 옵션 생성페이지로 이동
     * @throws Exception
     */
    @PostMapping("")
    public String createProduct(ProductRequest request, HttpSession session) throws Exception {
        Long proId = productService.createProduct(request, (Long) session.getAttribute("id"));
        return "redirect:/products/option/" + proId;
    }

    /**
     *
     * @param model
     * @param pageable
     * @return
     */
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

    @GetMapping("/{proId}")
    public String findProduct(@PathVariable Long proId, Model model) {
        ProductResponse findProduct = productService.findProduct(proId);
        model.addAttribute("findProduct", findProduct);
        return "productdetail";
    }

    @GetMapping("/option/{proId}")
    public String leadOptionForm(@PathVariable Long proId, Model model) {
        model.addAttribute("proId", proId);
        return "optadd";
    }


    @ResponseBody
    @PostMapping("/option")
    public String createOption(ProductRequest request) {
        productService.createOption(request);
        return "성공";
    }


    @GetMapping("/list/seller/{id}")
    public String findSeller(String search, Model model, @PathVariable("id") Long id, @PageableDefault(page = 0, size = 5, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts;
        if (search == null) {
            pagingProducts = productService.findSellerProductsList(id, pageable);
        } else {
            pagingProducts = productService.findSellerProductSearch(id,pageable, search);
        }
        model.addAttribute("pagingProducts", pagingProducts);
        return "seller";
    }

    @PostMapping("/return")
    public String createRepro(ReproRequest request, HttpSession session) {
        request.setId((Long) session.getAttribute("id"));
        productService.createRepro(request);
        return "redirect:/products/" + request.getProId();
    }

}
