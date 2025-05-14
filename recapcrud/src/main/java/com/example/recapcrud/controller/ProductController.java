package com.example.recapcrud.controller;

import com.example.recapcrud.dtos.ProductDto;
import com.example.recapcrud.repo.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestHeader(name = "x-auth-token", required = false) String authtoken
    ) {
        System.out.println(authtoken);
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDto(product.getName(), product.getPrice()))
                .toList();
    }
}
