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

    // READ
    // 전체 상품 평균값 이상 상품 조회 (서브쿼리)
    @GetMapping("/above-average")
    public ResponseEntity<List<ProductResponseDto>> findAboveAveragePrice() {
        long start = System.currentTimeMillis();
        List<ProductResponseDto> result = productService.findAboveAveragePrice();
        long end = System.currentTimeMillis();
        System.out.println("서브쿼리 실행시간 : " + (end - start) + "ms");
        return ResponseEntity.ok(result);
    }

    // JOIN 방식 (성능 비교용)
    @GetMapping("/above-average/join")
    public ResponseEntity<List<ProductResponseDto>> findAboveAveragePriceWithJoin() {
        long start = System.currentTimeMillis();
        List<ProductResponseDto> result = productService.findAvoveAveragePriceWithJoin();
        long end = System.currentTimeMillis();
        System.out.println("JOIN 실행시간: " + (end - start) + "ms");
        return ResponseEntity.ok(result);
    }

    // UPDATE
    @PutMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable int productId) {
        productService.delete(productId);
        return ResponseEntity.ok("상품 삭제 완료");
    }

}
