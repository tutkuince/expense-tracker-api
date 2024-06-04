package com.cloud.ecommerce.resourceserver.service;

import com.cloud.ecommerce.resourceserver.dto.ProductResponseDto;
import com.cloud.ecommerce.resourceserver.dto.ProductResponseListDto;
import com.cloud.ecommerce.resourceserver.model.Product;
import com.cloud.ecommerce.resourceserver.repository.ProductRepository;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecParams;
import com.cloud.ecommerce.resourceserver.specification.ProductSpecificationTitleBrandCategory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${pagination.page.size.default}")
    private Integer defaultPageSize;

    private final ProductRepository productRepository;
    private final ProductSpecificationTitleBrandCategory productSpecification;


    public ProductServiceImpl(ProductRepository productRepository, ProductSpecificationTitleBrandCategory productSpecification) {
        this.productRepository = productRepository;
        this.productSpecification = productSpecification;
    }

    @Override
    public ProductResponseDto getProductById(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductResponseDto responseDto = new ProductResponseDto();
            responseDto.populateDto(optionalProduct.get());
            return responseDto;
        }
        return null;
    }

    @Override
    public ProductResponseListDto getProductList(ProductSpecParams specParams) {
        List<Product> productList = null;
        Page<Product> pages = null;

        if (specParams.getPageIndex() == 0) {
            productList = productRepository.findAll(productSpecification.getProducts(specParams));
            if (!Objects.isNull(productList) && !productList.isEmpty()) {
                ProductResponseListDto listDto = new ProductResponseListDto();
                listDto.setTotalCount(productList.size());
                listDto.setProductResponseDtoList(new ArrayList<>());
                productList.forEach(product -> {
                    ProductResponseDto responseDto = new ProductResponseDto();
                    responseDto.populateDto(product);
                    listDto.getProductResponseDtoList().add(responseDto);
                });
                return listDto;
            }
        } else {
            if (specParams.getPageSize() == 0) {
                specParams.setPageSize(defaultPageSize);
            }
            Pageable paging = PageRequest.of(specParams.getPageIndex() - 1, specParams.getPageSize());
            pages = productRepository.findAll(productSpecification.getProducts(specParams), paging);
            if (pages != null && pages.getContent() != null) {
                productList = pages.getContent();
                if (productList != null && productList.size() > 0) {
                    ProductResponseListDto dto = new ProductResponseListDto();
                    dto.setTotalPages(pages.getTotalPages());
                    dto.setTotalCount(pages.getTotalElements());
                    dto.setPageIndex(pages.getNumber() + 1);
                    dto.setProductResponseDtoList(new ArrayList<>());
                    productList.forEach(product -> {
                        ProductResponseDto responseDto = new ProductResponseDto();
                        responseDto.populateDto(product);
                        dto.getProductResponseDtoList().add(responseDto);
                    });
                    return dto;
                }
            }
        }
        return null;
    }
}
