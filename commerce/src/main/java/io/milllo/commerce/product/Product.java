package io.milllo.commerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private Integer categoryId;
}
