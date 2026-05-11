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
    public List<Product> findAll() { return productRepository.findAll(); }

    // READ
    public Product findById(int productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
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
