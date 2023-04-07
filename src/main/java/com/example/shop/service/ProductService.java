package com.example.shop.service;


import com.example.shop.dto.*;
import com.example.shop.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Value("${file.Upimg}")
    private String path;  // 이미지 파일이 저장될 디렉토리 주소 , C드라이브에 위치함 application.yml에 등록해놓은 값
    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;
    private final OptionRepository optionRepository;
    private final ReproRepository reproRepository;


    /**
     * 제품생성시 등록한 이미지의 데이터파일을 인자로받아 실제 외부 폴더에 생성하고 DB에 넣어주는 역할을 하는 로직
     * @param file          = 사용자가 입력한 이미지파일을 가지고 있는 객체
     * @param img_type      = 이미지의 상세이미지/대표이미지 의 구분을 위한 값
     * @param productEntity = Product객체
     * @throws IOException
     */
    @Transactional
    public void fileUpload(MultipartFile file, String img_type, Product productEntity) throws IOException {
        String img_original = file.getOriginalFilename(); // 입력한 원본 파일의 이름
        String uuid = String.valueOf(UUID.randomUUID()); // toString 보다는 valueOf를 추천 , NPE에러 예방,
        String extension = img_original.substring(img_original.lastIndexOf(".")); // 원본파일의 파일확장자
        String savedName = uuid + extension; // 랜덤이름 + 확장자
        File converFile = new File(path, savedName); // path = 상품 이미지 파일의 저장 경로가 들어있는 프로퍼티 설정값
        if (!converFile.exists()) {
            converFile.mkdirs();
        }
        file.transferTo(converFile);
        Img uploadImgEntity = Img.builder().imgname(savedName).imgoriginal(img_original).imgtype(img_type).product(productEntity).build();
        imgRepository.save(uploadImgEntity);
    }

    /**
     * 제품 생성 기능을 담당하는 로직
     *
     * @param request = 사용자가 입력한 제품등록 데이터가 저장되어 있는 객체
     * @param id      = 제품을 등록하려고 하는 사용자 아이디의 고유번호
     * @return = 제품 생성이 되었는지 확인을 하기위한 값
     * @throws Exception
     */
    @Transactional
    public Long createProduct(ProductRequest request, Long id) throws Exception {
        Product productEntity = productRepository.save(request.productEntity(id));
        for (MultipartFile imgFile : request.getImgList()) {
            fileUpload(imgFile, StaticType.ProductImg.name(), productEntity);
        }
        for (MultipartFile textimgFile : request.getTextimgList()) {
            fileUpload(textimgFile, StaticType.ProductTextImg.name(), productEntity);
        }
        return productEntity.getProId();

    }

    /**
     * 제품의 상세보기 기능을 담당하는 로직
     *
     * @param proid = 사용자가 보기 위해 선택한 제품의 고유번호
     * @return = 고유번호로 찾아온 제품의 데이터를 응답 전용 객체로 변환시켜 리턴
     */
    public ProductResponse findProduct(Long proid) {
        return new ProductResponse(productRepository.findByProduct(proid));
    }

    /**
     * 제품 생성 완료 후 옵션생성 페이지에서 옵션 생성을 담당하는 로직,
     *
     * @param request = 사용자가 입력한 옵션데이터를 가지고 있는 객체
     */
    @Transactional                                          // 다중옵션은 대분류(opt1) 1개하위에 여러개의 중분류(opt2)가 존재하는 구조
    public void createOption(ProductRequest request) {      // 단일옵션은 대분류(opt1)이 아예 없고 중분류 (opt2)만 여러개 존재하는 구조입니다.
        for (int i = 0; i < request.getOpt2().size(); i++) { // 단일/다중 둘다 opt2가 여러개 존재하기 때문에 opt2를 기준으로 반복문 작성
            Option opt = request.createOptionEntity(i, request);
            optionRepository.save(opt);
        }
    }


    /**
     * 판매자페이지에서 해당 판매자가 판매중인 제품들의 목록을 보여주는 로직입니다
     * @param id = 판매자의 고유번호
     * @param page = 페이징처리를 위한 데이터를 가지고 있는 객체
     * @return = DB에서 페이징 처리를 마치고 나온 조회값을 기반으로 View에 출력해줄 페이지의 버튼을 위한 계산된 값들과 DB의 제품리스트 데이터를 함께 보내주기위한 객체로 리턴  
     */
    public PagingList findSellerProductsList(Long id, Pageable page) {
        Page<ProductResponse> getPagingProduct = productRepository.findAllByid(page, id);
        return PagingList.setPagingList(getPagingProduct);
    }

    /**
     * 판매중인 모든 제품의 리스트를 보여주는 로직입니다. 
     * @param page = 페이징처리를 위한 데이터를 가지고 있는 객체
     * @return = DB에서 페이징 처리를 마치고 나온 조회값을 기반으로 View에 출력해줄 페이지의 버튼을 위한 계산된 값들과 DB의 제품리스트 데이터를 함께 보내주기위한 객체로 리턴  
     */
    public PagingList findProducts(Pageable page) {
        Page<ProductResponse> getPagingProduct = productRepository.findAllProduct(page);
        return PagingList.setPagingList(getPagingProduct);
    }

    /**
     * 판매중인 제품들중 특정 카테고리에 해당하는 상품만 보여주기위한 로직
     * @param proCategory = 사용자가 보려고 하는 카테고리의 값
     * @param page = 페이징처리를 위한 데이터를 가지고 있는 객체
     * @return = DB에서 페이징 처리를 마치고 나온 조회값을 기반으로 View에 출력해줄 페이지의 버튼을 위한 계산된 값들과 DB의 제품리스트 데이터를 함께 보내주기위한 객체로 리턴  
     */
    public PagingList findByCategoryProducts(Pageable page, String proCategory) {
        Page<ProductResponse> getPagingProduct = productRepository.findByCategory(page, proCategory);
        return PagingList.setPagingList(getPagingProduct);
    }

    /**
     * 제품리스트에서 검색창에 검색어를 입력할 경우 해당하는 검색어에 맞는 제품을 찾아주기위한 로직 
     * @param search = 사용자가 입력한 검색어
     * @param page = 페이징처리를 위한 데이터를 가지고 있는 객체
     * @return = DB에서 페이징 처리를 마치고 나온 조회값을 기반으로 View에 출력해줄 페이지의 버튼을 위한 계산된 값들과 DB의 제품리스트 데이터를 함께 보내주기위한 객체로 리턴  
     */
    public PagingList findBySearchProducts(Pageable page, String search) {
        Page<ProductResponse> getPagingProduct = productRepository.findBySearchProducts(page, search);
        return PagingList.setPagingList(getPagingProduct);
    }

    /**
     * 판매자 페이지에서 판매자가 입력한 검색어에 맞는 본인의 제품을 보여주기위한 로직
     * @param id = 판매자의 고유번호
     * @param search = 판매자가 입력한 검색어
     * @param page = 페이징처리를 위한 데이터를 가지고 있는 객체
     * @return = DB에서 페이징 처리를 마치고 나온 조회값을 기반으로 View에 출력해줄 페이지의 버튼을 위한 계산된 값들과 DB의 제품리스트 데이터를 함께 보내주기위한 객체로 리턴  
     */

    public PagingList findSellerProductSearch(Long id, Pageable page, String search) {
        Page<ProductResponse> getPagingProduct = productRepository.findSellerProductSearch(page, id, search);
        return PagingList.setPagingList(getPagingProduct);
    }

    /**
     * 제품 신고기능을 담당하는 로직
     * @param request = 신고사유 / 상세사유 / 신고자의 고유번호 등이 저장되어있는 객체
     */
    @Transactional
    public void createRepro(ReproRequest request) {
        reproRepository.save(request.toEntity());
    }


}
