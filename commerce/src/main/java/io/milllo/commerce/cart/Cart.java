package io.milllo.commerce.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cart {
    private Integer cartId;
    private Integer customerId;
    private Integer productId;
    private Integer price;
    private Integer quantity;
    private LocalDateTime createdAt;
}
