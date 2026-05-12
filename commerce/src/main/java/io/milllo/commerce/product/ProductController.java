package io.milllo.commerce.product;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // CREATE
    @PostMapping
    public ResponseEntity<String> add(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.ok("주문 성공");
    }

    // READ
    // 전체 또는 카테고리별 조회
    // GET /products
    // GET /products?category=노트북
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll(
            @RequestParam(required = false) String category
    ) {
        if(category != null) {
            return ResponseEntity.ok(productService.findByCategory(category));
        }
        return ResponseEntity.ok(productService.findAll());
    }

    // UPDATE
    @PutMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable int productId) {
        productService.delete(productId);
        return ResponseEntity.ok("상품 삭제 완료");
    }

}
