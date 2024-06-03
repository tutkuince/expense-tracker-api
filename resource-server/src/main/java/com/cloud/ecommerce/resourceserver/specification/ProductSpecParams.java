package com.cloud.ecommerce.resourceserver.specification;

import lombok.Data;

@Data
public class ProductSpecParams {
    private int pageSize;
    private String search;
    private String sort;
    private int brandId;
    private int categoryId;
    private final int maxPageSize = 20;

    public void setMaxPageSize(int pageSize) {
        if (pageSize > maxPageSize) {
            this.pageSize = maxPageSize;
        }
        this.pageSize = pageSize;
    }

    public void setSearch(String search) {
        this.search = search.toLowerCase();
    }
}
