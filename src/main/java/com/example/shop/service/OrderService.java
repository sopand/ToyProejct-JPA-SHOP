package com.example.shop.service;

import com.example.shop.dto.ImgResponse;
import com.example.shop.dto.OrderRequest;
import com.example.shop.dto.OrderResponse;
import com.example.shop.entity.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ImgRepository imgRepository;

    /**
     * 제품의 Order테이블과 관련된 생성작업을 하는 로직 (구매,찜하기,장바구니)등 
     * @param request = 사용자가 주문하려는 제품의 정보와 사용자의 주소등이 들어있는 객체
     * @param id = 해당 사용자의 고유번호
     * @return = 생성 여부를 확인하기 위한 Long 고유번호 데이터
     */
    @Transactional
    public Long createOrder(OrderRequest request, Long id) {
        Member member = Member.builder().id(id).build();
        Product product = Product.builder().proId(request.getProId()).build();
        switch (request.getOrdchk()) {
            case "장바구니":
                Option option = Option.builder().optid(request.getOptid()).build();
                return orderRepository.save(request.create(product, member, option)).getOrdid();
            case "구매":
                Option option2 = Option.builder().optid(request.getOptid()).build();
                return orderRepository.save(request.directbuy(product, member, option2)).getOrdid();
            case "찜하기":
                return orderRepository.save(request.favorite(product, member)).getOrdid();
            default:
                return null;
        }
    }

    /**
     * 좋아요,장바구니의 중복 체크를 하기위한 로직
     * @param proid = 제품의 고유번호
     * @param id = 사용자의 고유번호
     * @param check = 중복체크를 하려고하는 버튼이 무엇인지 체크 (장바구니 버튼인지 좋아요 버튼인지 구분하기위함)
     * @return = 없다면 그냥 null 을반환하고 있다면 Order의 고유번호를 반환한다 ( 중복유무를 조회하기 위함 ) null이라면 중복이아님
     */
    public Long hasFavorite(Long proid, Long id, String check) {
        Order order = orderRepository.findByProductProIdAndMemberIdAndOrdchk(proid, id, check).orElseGet(()->null);

        return order==null ?  null:  order.getOrdid();


    }

    /**
     * 좋아요를 삭제처리 하기 위한 기능로직
     * @param ordid = 제거하려는 Order의 고유번호
     */
    public void deleteFavorite(Long ordid) {
        orderRepository.deleteById(ordid);
    }

    /**
     * 장바구니 리스트의 데이터를 출력해주기위한 기능
     * @param id = 사용자의 고유번호
     * @return = 찾아온 사용자의 장바구니데이터를 응답 객체로 변환시켜 리턴
     */

    public List<OrderResponse> findCarts(Long id) {
        List<OrderResponse> carts = orderRepository.findOrderById(id).stream().map(OrderResponse::new).toList();
        carts.stream().forEach(order ->
                order.setImgs(new ImgResponse(imgRepository.findFirstByImgByProId(order.getProduct().getProId())))
        );
        return carts;
    }

    /**
     * 장바구니 페이지에서 구매기능을 작동하기 위한 로직
     * @param request = 구매하려고 하는 제품의 정보가들어 있는 객체 (주소 , 옵션 등등) 
     */
    @Transactional
    public void BuyFromCart(OrderRequest request){
        AtomicInteger count= new AtomicInteger();
            request.getOrdidList().stream().forEach(entity->{
                request.setOrdid(entity);
                request.setOrdquantity(request.getQuantityList().get(count.getAndIncrement()));
                Order order=orderRepository.findByOrdid(request.getOrdid()).orElseThrow(()-> new NoSuchElementException("찾을수 주문 정보입니다 "));
                order.modifyOrderEntity(request);
                    }
            );
    }

    /**
     * 장바구니에 있는 제품을 삭제처리 하기 위한 로직
     * @param ordid = 삭제하려고 하는 장바구니 주문번호의 고유번호
     */
    @Transactional
    public void deleteCarts(List<Long> ordid) {
        ordid.stream().forEach(id -> orderRepository.deleteById(id));
    }




}
