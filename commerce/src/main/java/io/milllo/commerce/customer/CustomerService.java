package io.milllo.commerce.customer;

import org.springframework.stereotype.Service;
import java.util.List;

// Service, Repository는 spring이 관리하는 컴포넌트 -> 생성자를 직접 호출해서 의존성을 주입해줘야 한다
// @RequiredArgsConstructor는 final 필드만 골라서 생성자를 만들어준다.
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //CREATE
    public void join(Customer customer) {
        customerRepository.save(customer);
    }

    //READ - 전체조회
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    //READ - 단건조회
    public Customer findById(int customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 고객입니다."));
    }

    // 장바구니에 담지 않은 고객 조회
    public List<CustomerResponseDto> findInactiveCustomers() {
        return customerRepository.findInactiveCustomers();
    }

    //UPDATE
    public void update(Customer customer) {
        findById(customer.getCustomerId()); // 존재여부 확인
        customerRepository.update(customer);
    }

    //DELETE
    public void delete(int customerId) {
        findById(customerId);
        customerRepository.delete(customerId);
    }
}
