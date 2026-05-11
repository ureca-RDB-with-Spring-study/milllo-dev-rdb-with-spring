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
    @GetMapping
    public ResponseEntity<List<Product>> findAll() { return ResponseEntity.ok(productService.findAll()); }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable int productId) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    // UPDATE
    @PutMapping("/{productId}")
    public ResponseEntity<String> delete(@PathVariable int productId) {
        productService.delete(productId);
        return ResponseEntity.ok("상품 삭제 완료");
    }

}
