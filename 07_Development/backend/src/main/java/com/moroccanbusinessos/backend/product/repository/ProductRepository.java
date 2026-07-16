package com.moroccanbusinessos.backend.product.repository;

import com.moroccanbusinessos.backend.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

}