package io.milllo.commerce.customer;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 응답 반환
@RequestMapping("/customers") // 기본 URL 경로 설정
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    //CREATE
    @PostMapping // POST 요청 처리
    // @RequstBody : JSON -> 객체 변환
    public ResponseEntity<String> join(@RequestBody Customer customer) {
        customerService.join(customer);
        return ResponseEntity.ok("회원가입 성공");
    }

    //READ - 전체조회
    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    //READ - 단건조회
    @GetMapping("/{customerId}")
    // @PathVariable : URL 경로 변수 추출
    public ResponseEntity<Customer> findById(@PathVariable int customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    // 장바구니에 담지 않은 고객 조회
    @GetMapping("/inactive")
    public ResponseEntity<List<CustomerResponseDto>> findInactiveCustomers() {
        return ResponseEntity.ok(customerService.findInactiveCustomers());
    }

    @GetMapping("/report")
    public ResponseEntity<List<CustomerResponseDto>> reportActiveCustomers() {
        return ResponseEntity.ok(customerService.reportActiveCustomers());
    }

    @GetMapping("/report-exists")
    public ResponseEntity<List<CustomerResponseDto>> reportActiveCustomersFromExists() {
        return ResponseEntity.ok(customerService.reportActiveCustomersFromExists());
    }

    @GetMapping("/report-join")
    public ResponseEntity<List<CustomerResponseDto>> reportActiveCustomersFromJoin() {
        return ResponseEntity.ok(customerService.reportActiveCustomersFromJoin());
    }

    //UPDATE
    @PutMapping("/{customerId}")
    public ResponseEntity<String> update(@PathVariable int customerId, @RequestBody Customer customer) {
        customer.setCustomerId(customerId);
        customerService.update(customer);
        return ResponseEntity.ok("수정완료");
    }

    //DELETE
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> delete(@PathVariable int customerId) {
        customerService.delete(customerId);
        return ResponseEntity.ok("삭제완료");
    }
}
