package com.sparta.springcore.controller;

import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.UserRoleEnum;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

//@RequiredArgsConstructor  //-> @Autowired
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

    // 신규 상품 등록
    @PostMapping("/api/products")
    //public Product createProduct(@RequestBody ProductRequestDto requestDto) throws SQLException {
    public Product createProduct(@RequestBody ProductRequestDto requestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //ProductService productService = new ProductService(); -> DI

        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        //My
        //return productService.createProduct(requestDto);
        //T
        //Product product = productService.createProduct(requestDto);
        Product product = productService.createProduct(requestDto, userId);
        // 응답 보내기
        return product;
    }

    // 설정 가격 변경
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productService.updateProduct(id, requestDto);

        // 응답 보내기 (업데이트된 상품 id)
        return product.getId();
    }

    // 등록된 전체 상품 목록 조회
    /*
    @GetMapping("/api/products")
    public List<Product> getProducts() throws SQLException {
        List<Product> products = productService.getProducts();

        // 응답 보내기
        return products;
    }*/

    // 로그인한 회원이 등록한 관심 상품 조회
    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 로그인 되어 있는 회원 테이블의 ID
        Long userId = userDetails.getUser().getId();

        return productService.getProducts(userId);
    }

    // (관리자용) 등록된 모든 상품 목록 조회
    //@Secured("ROLE_ADMIN")  //여기 안에는 static한 값만 들어가야 됨
    //@Secured(value = UserRoleEnum.Authority.getAuthority())  //방법2
    @Secured(value = UserRoleEnum.Authority.ADMIN)
    @GetMapping("/api/admin/products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

}
