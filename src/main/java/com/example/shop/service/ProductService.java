package com.example.shop.service;


import com.example.shop.dto.*;
import com.example.shop.entity.*;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${file.Upimg}")
    private String path;
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;
    private final OptionRepository optionRepository;

    private final ReproRepository reproRepository;




    @Transactional
    public void fileUpload(MultipartFile file, String img_type, Product p) throws IOException {
        String img_original = file.getOriginalFilename(); // 입력한 원본 파일의 이름
        String uuid = String.valueOf(UUID.randomUUID()); // toString 보다는 valueOf를 추천 , NPE에러 예방,
        String extension = img_original.substring(img_original.lastIndexOf(".")); // 원본파일의 파일확장자
        String savedName = uuid + extension; // 랜덤이름 + 확장자
        File converFile = new File(path, savedName); // path = 상품 이미지 파일의 저장 경로가 들어있는 프로퍼티 설정값
        if (!converFile.exists()) {
            converFile.mkdirs();
        }
        file.transferTo(converFile);
        Img i = Img.builder().imgname(savedName).imgoriginal(img_original).imgtype(img_type).product(p).build();
        imgRepository.save(i);
    }

    @Transactional
    public Long createProduct(ProductRequest request, Long id) throws IOException {
        Product p = productRepository.save(request.productEntity(id));
        for (MultipartFile imgFile : request.getImgList()) {
            fileUpload(imgFile, StaticType.ProductImg.name(), p);
        }
        for (MultipartFile textimgFile : request.getTextimgList()) {
            fileUpload(textimgFile, StaticType.ProductTextImg.name(), p);
        }
        return p.getProId();

    }

    public ProductResponse findProduct(Long proid) {
        return new ProductResponse(productRepository.findByProduct(proid));
    }

    @Transactional
    public void createOption(ProductRequest request) {

        Product findProduct = productRepository.findByProId(request.getProId());
        if (request.getOpt1() == null) {
            for (int i = 0; i < request.getOpt2().size(); i++) {
                Option opt = Option.builder().opt2(request.getOpt2().get(i)).optquantity(request.getOptquantity().get(i)).product(findProduct).build();
                optionRepository.save(opt);
            }
        } else {
            for (int i = 0; i < request.getOpt2().size(); i++) {
                Option opt = Option.builder().opt1(request.getOpt1()).opt2(request.getOpt2().get(i)).optquantity(request.getOptquantity().get(i)).product(findProduct).build();
                optionRepository.save(opt);
            }
        }


    }

    @Transactional
    public void createRepro(ReproRequest request){
        reproRepository.save(request.toEntity());
    }


    public PagingList findSeller(Long id,String email, Pageable page){
        Page<ProductResponse> getPagingProduct = productRepository.findAllByid(page,id,email);
        return PagingList.setPagingList(getPagingProduct);
    }

    public PagingList findProducts(Pageable page) {
        Page<ProductResponse> getPagingProduct = productRepository.findAllProduct(page);
        return PagingList.setPagingList(getPagingProduct);
    }
    public PagingList findByCategoryProducts(Pageable page,String procategory) {
        Page<ProductResponse> getPagingProduct = productRepository.fidByCategory(page,procategory);
        return PagingList.setPagingList(getPagingProduct);
    }


    public PagingList findSellerSearch(Long id,String email, Pageable page,String search){
        Page<ProductResponse> getPagingProduct = productRepository.findSearch(page,id,email,search);
        return PagingList.setPagingList(getPagingProduct);
    }


}
