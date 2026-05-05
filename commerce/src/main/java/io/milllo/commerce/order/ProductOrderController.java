package io.milllo.commerce.order;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 응답 반환
@RequestMapping("/orders") // 기본 URL 경로 설정
@RequiredArgsConstructor
public class ProductOrderController {
    private final ProductOrderService orderService;

    //CREATE
    @PostMapping // POST 요청 처리
    // @RequstBody : JSON -> 객체 변환
    public ResponseEntity<String> join(@RequestBody ProductOrder order) {
        orderService.join(order);
        return ResponseEntity.ok("주문 성공");
    }

    //READ - 전체조회
    @GetMapping
    public ResponseEntity<List<ProductOrder>> findAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    //READ - 단건조회
    @GetMapping("/{orderId}")
    // @PathVariable : URL 경로 변수 추출
    public ResponseEntity<ProductOrder> findById(@PathVariable int orderId) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    //UPDATE
    @PutMapping("/{orderId}")
    public ResponseEntity<String> update(@PathVariable int orderId, @RequestBody ProductOrder order) {
        order.setOrderId(orderId);
        orderService.update(order);
        return ResponseEntity.ok("주문수정완료");
    }

    //DELETE
    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> delete(@PathVariable int orderId) {
        orderService.delete(orderId);
        return ResponseEntity.ok("주문삭제완료");
    }
}
