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
public class CartResponseDTO {
    private Integer cartId;
    private Integer customerId;
    private String customerName;
    private Integer productId;
    private String productName;
    private Integer productPrice;
    private Integer cartQuantity;
    private Integer totalPrice;
}
