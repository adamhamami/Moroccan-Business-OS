package com.moroccanbusinessos.backend.product.service;

import com.moroccanbusinessos.backend.product.dto.ProductRequest;
import com.moroccanbusinessos.backend.product.dto.ProductResponse;
import com.moroccanbusinessos.backend.product.entity.Product;
import com.moroccanbusinessos.backend.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse create(ProductRequest request) {

        if (productRepository.existsByName(request.getName())) {
            throw new RuntimeException("Product already exists.");
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .build();

        product = productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .build();
    }
}