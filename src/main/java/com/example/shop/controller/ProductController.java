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

import static com.example.shop.controller.MemberController.memberId;


/**
 * 제품의 생성 / 수정 /삭제 및 옵션관련 등 제품관련 모든 처리들을 담당하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/products") // 해당 컨트롤러의 맵핑을 /products ~~~ 로 통일하기 위해서 컨트롤러에 선언해놓은 기본 맵핑주소
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
     * @return = ADD기능은 forward방식으로 페이지전환시 F5를 클릭하면 등록한 객체가 무한반복 될 수 있는 오류가 있어 redirec로 옵션 생성페이지로 이동
     * @throws Exception
     */
    @PostMapping("")
    public String createProduct(ProductRequest request) throws Exception {
        Long proId = productService.createProduct(request, memberId);
        return "redirect:/products/option/" + proId;
    }

    /**
     *등록되어있는 판매 제품들의 리스트를 보여주기 위한 맵핑,
     * @param model = View페이지에 출력시켜줄 데이터들을 설정해주는 객체
     * @param pageable = 리스트의 페이징처리를 하기위한 기본 설정값들을 가지고 있는 객체
     * @return
     */
    @GetMapping("/list")
    public String findProducts(Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findProducts(pageable);
        model.addAttribute("pagingProducts", pagingProducts);
        return "productlist";
    }

    /**
     * 제품의 카테고리별로 리스트를 출력시켜주는 맵핑
     * @param proCategory = 사용자가 선택한 카테고리의 이름 
     * @param model = View페이지에 출력시켜줄 데이터를 설정하는 객체
     * @param pageable = 페이징처리 데이터를 가지고 있는 객체
     * @return
     */
    @GetMapping("/list/{proCategory}")
    public String findByCategoryProducts(@PathVariable String proCategory, Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findByCategoryProducts(pageable,proCategory);
        model.addAttribute("pagingProducts", pagingProducts);
        return "productlist";
    }

    /**
     * 제품 리스트에서 검색창에 검색어를 입력하게되면 작동하는 맵핑
     * @param search = 사용자가 검색창에 입력한 검색어
     * @param model = View페이지에 출력시켜줄 데이터를 설정하는 객체
     * @param pageable = 페이징처리 데이터를 가지고 있는 객체
     * @return
     */
    @GetMapping("/list/search")
    public String findBySearchProducts(String search, Model model, @PageableDefault(page = 0, size = 9, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {
        PagingList pagingProducts = productService.findBySearchProducts(pageable,search);
        model.addAttribute("pagingProducts", pagingProducts);

        return "productlist";
    }

    /**
     * 단일 상품의 상세페이지를 보여주는 맵핑
     * @param proId = 사용자가 선택한 제품의 고유번호
     * @param model = View페이지에 출력시켜줄 데이터를 설정하는 객체
     * @return
     */
    @GetMapping("/{proId}")
    public String findProduct(@PathVariable Long proId, Model model) {
        ProductResponse findProduct = productService.findProduct(proId);
        model.addAttribute("findProduct", findProduct);
        return "productdetail";
    }

    /**
     * 제품생성페이지에서 제품 데이터를 생성한 후 옵션관련 데이터를 작성하기 위해 옵션생성 폼으로 이동하는 맵핑
     * @param proId = 앞에서 생성한 제품의 고유번호 (FK로 입력하기 위해 필요)
     * @param model = View페이지에 출력시켜줄 데이터를 설정하는 객체
     * @return
     */
    @GetMapping("/option/{proId}")
    public String leadOptionForm(@PathVariable Long proId, Model model) {
        model.addAttribute("proId", proId);
        return "optadd";
    }


    /**
     * 옵션의 생성을 담당하는 맵핑
     * @param request = 사용자가 입력한 옵션관련 데이터들이 저장되어있는 객체
     * @return
     */
    @ResponseBody
    @PostMapping("/option")
    public String createOption(ProductRequest request) {
        productService.createOption(request);
        return "성공";
    }


    /**
     * 판매자의 전용페이지로 이동하는 맵핑, 판매자의 제품들이 출력된다 
     * @param search = 판매자 페이지에서 검색창에 입력시 들어오는 값,
     * @param model = View페이지에 출력시켜줄 데이터를 설정하는 객체
     * @param id = 판매자의 ID 고유번호
     * @param pageable = 페이징데이터가 저장되어있는 객체
     * @return
     */
    @GetMapping("/list/seller/{id}")
    public String findSeller(String search, Model model, @PathVariable("id") Long id, @PageableDefault(page = 0, size = 5, sort = "proId", direction = Sort.Direction.DESC) Pageable pageable) {

        PagingList pagingProducts = search == null ? productService.findSellerProductsList(id,pageable) : productService.findSellerProductSearch(id,pageable,search);
        model.addAttribute("pagingProducts", pagingProducts);
        return "seller";
    }

    /**
     * 제품 상세페이지에서 신고기능을 사용 할 경우 작동되는 맵핑
     * @param request = 사용자가 입력한 신고 이유 ,상세 사유 , 신고한 제품의 번호등이 들어있는 객체
     * @param session = 현재 신고하려는 사람의 아이디 고유번호를 알아내기위한 session
     * @return
     */
    @PostMapping("/return")
    public String createRepro(ReproRequest request, HttpSession session) {
        request.setId((Long) session.getAttribute("id"));
        productService.createRepro(request);
        return "redirect:/products/" + request.getProId();
    }

}
