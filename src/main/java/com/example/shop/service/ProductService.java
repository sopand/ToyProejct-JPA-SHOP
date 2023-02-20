package com.example.shop.service;

import com.example.shop.dto.ProductResponse;
import com.example.shop.dto.ProductReuqest;
import com.example.shop.entity.Img;
import com.example.shop.entity.ImgRepository;
import com.example.shop.entity.Product;
import com.example.shop.entity.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${file.Upimg}")
    private String path;
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;

    public void createProduct(ProductReuqest request) throws IOException {
       Product p=productRepository.save(request.productEntity());
        for (MultipartFile file : request.getImgList()) {
            if (!file.isEmpty()) {
                String img_original = file.getOriginalFilename(); // 입력한 원본 파일의 이름
                String uuid = String.valueOf(UUID.randomUUID()); // toString 보다는 valueOf를 추천 , NPE에러 예방,
                String extension = img_original.substring(img_original.lastIndexOf(".")); // 원본파일의 파일확장자
                String savedName = uuid + extension; // 랜덤이름 + 확장자
                File converFile = new File(path, savedName); // path = 상품 이미지 파일의 저장 경로가 들어있는 프로퍼티 설정값
                if (!converFile.exists()) {
                    converFile.mkdirs();
                }
                file.transferTo(converFile); // --- 실제로 저장을 시켜주는 부분 , 해당 경로에 접근할 수 있는 권한이 없으면 에러 발생
                Img i=Img.builder().img_name(savedName).img_original(img_original).product(p).build();
                imgRepository.save(i);
            }
        }
    }

    public ProductResponse findProducts(PageRequest page) {
        Page<Product> pli=productRepository.findAll(page);

        return null;

    }


}
