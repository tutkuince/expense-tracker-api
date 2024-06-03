package com.cloud.ecommerce.resourceserver.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseListDto {
    private int totalPages;
    private long totalCount;
    private int pageIndex;
    private List<ProductResponseDto> productResponseDtoList;

    public ProductResponseListDto() {
        productResponseDtoList = new ArrayList<>();
    }
}
