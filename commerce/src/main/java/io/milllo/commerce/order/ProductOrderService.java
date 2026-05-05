package io.milllo.commerce.order;

import org.springframework.stereotype.Service;
import java.util.List;

// Service, Repository는 spring이 관리하는 컴포넌트 -> 생성자를 직접 호출해서 의존성을 주입해줘야 한다
// @RequiredArgsConstructor는 final 필드만 골라서 생성자를 만들어준다.
@Service
public class ProductOrderService {
    private final ProductOrderRepository orderRepository;
    public ProductOrderService(ProductOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    //CREATE
    public void join(ProductOrder order) {
        orderRepository.save(order);
    }

    //READ - 전체조회
    public List<ProductOrder> findAll() {
        return orderRepository.findAll();
    }

    //READ - 단건조회
    public ProductOrder findById(int orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문건입니다."));
    }

    //UPDATE
    public void update(ProductOrder order) {
        findById(order.getOrderId()); // 존재여부 확인
        orderRepository.update(order);
    }

    //DELETE
    public void delete(int orderId) {
        findById(orderId);
        orderRepository.delete(orderId);
    }
}
