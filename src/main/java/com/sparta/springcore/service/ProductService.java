package com.sparta.springcore.service;

import com.sparta.springcore.dto.ProductMypriceRequestDto;
import com.sparta.springcore.dto.ProductRequestDto;
import com.sparta.springcore.model.Product;
import com.sparta.springcore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
//@NoArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    // 스프링에 의해 DI(의존성 주입) 방법1 -> 방법2 @RequiredArgsConstructor
//    @Autowired
//    public ProductService(){
//        this.productRepository = new ProductRepository();
//    }

    // DI 방법3
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 방법4. 빈 수동으로 가져오기
//    @Autowired
//    public ProductService(ApplicationContext context) {
//        // 1.'빈' 이름으로 가져오기
//        ProductRepository productRepository = (ProductRepository) context.getBean("productRepository");
//        // 2.'빈' 클래스 형식으로 가져오기
//        // ProductRepository productRepository = context.getBean(ProductRepository.class);
//        this.productRepository = productRepository;
//    }


    public Product save(ProductRequestDto productRequestDto) throws SQLException {
        Product product = new Product(productRequestDto);
        //ProductRepository productRepository = new ProductRepository();

        //return productRepository.insertDB(product);
        productRepository.save(product);  //JPA
        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        //방법1
        //Optional<Product> product = productRepository.findById(id);
        //방법2 - orElseThrow
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);  //myprice만 변경됨

        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();

        return products;
    }

}
