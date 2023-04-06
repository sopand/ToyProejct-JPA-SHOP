package com.example.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PagingList {

    private Page<?> pagingList;
    private int nowPage;
    private int startPage;
    private int endPage;


    public static PagingList setPagingList(Page<?> getPagingProduct) {
        int nowPage = getPagingProduct.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, getPagingProduct.getTotalPages());
        return PagingList.builder().pagingList(getPagingProduct)
                .nowPage(nowPage).startPage(startPage).endPage(endPage).build();
    }

}
