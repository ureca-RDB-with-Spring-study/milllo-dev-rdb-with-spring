package io.milllo.commerce.cart;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartResponseDTO> findByCustomerId(int customerId) {
        return cartRepository.findByCustomerId(customerId);
    }
}
