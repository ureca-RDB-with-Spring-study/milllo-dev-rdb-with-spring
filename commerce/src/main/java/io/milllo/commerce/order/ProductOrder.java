package io.milllo.commerce.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    private Integer orderId;
    private Integer customerId;
    private Integer productId;
    private Integer quantity;
    private LocalDateTime orderDate;
    private String status;
}
