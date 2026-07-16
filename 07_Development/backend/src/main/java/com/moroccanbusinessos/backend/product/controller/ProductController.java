package com.moroccanbusinessos.backend.product.controller;

import com.moroccanbusinessos.backend.product.dto.ProductRequest;
import com.moroccanbusinessos.backend.product.dto.ProductResponse;
import com.moroccanbusinessos.backend.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}