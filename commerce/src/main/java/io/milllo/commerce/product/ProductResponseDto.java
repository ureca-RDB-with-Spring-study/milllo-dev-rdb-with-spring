package io.milllo.commerce.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Integer productId;
    private String name;
    private String description;
    private Integer price;
    private Integer stockQuantity;
    private String categoryName;  // JOIN으로 가져온 카테고리 이름
}