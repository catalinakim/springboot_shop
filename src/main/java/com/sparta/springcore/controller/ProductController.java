package com.sparta.springcore.controller;

import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

//@RequiredArgsConstructor
@RestController  // JSON으로 데이터를 주고받음을 선언합니다.
public class ProductController {

    private final ProductService productService;
    // Parameter 0 of constructor in com.sparta.springcore.
    // ProductController required a bean of type 'com.sparta.springcore.service.ProductService' that could not be found.
    // -> service 클래스에 @Service 붙여주니 됨

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
        //ProductService productService = new ProductService();
        return productService.save(requestDto);
        //T
        //Product product = productService.createProduct(requestDto);
        // 응답 보내기
        //return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productService.updateProduct(id, requestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
        List<Product> products = productService.getProducts();

        // 응답 보내기
        return products;
    }

}
