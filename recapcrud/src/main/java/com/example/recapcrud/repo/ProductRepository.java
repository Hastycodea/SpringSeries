package com.example.recapcrud.repo;

import com.example.recapcrud.dtos.ProductDto;
import com.example.recapcrud.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
