package io.milllo.commerce.product;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}

    // CREATE
    public void add(Product product) { productRepository.save(product); }

    // READ
    public List<ProductResponseDto> findAll() { return productRepository.findAll(); }

    // READ
    public Product findById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    // READ
    public List<ProductResponseDto> findByCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }

    // READ - 평균값 이상 상품 조회 (서브쿼리)
    public List<ProductResponseDto> findAboveAveragePrice() {
        return productRepository.findAboveAveragePrice();
    }

    // READ - 평균값 이상 상품 조회 (JOIN)
    public List<ProductResponseDto> findAvoveAveragePriceWithJoin() {
        return productRepository.findAvoveAveragePriceWithJoin();
    }

    // UPDATE
    public void update(Product product) {
        findById(product.getProductId());
        productRepository.update(product);
    }

    // DELETE
    public void delete(int productId) {
        findById(productId);
        productRepository.delete(productId);
    }
}
