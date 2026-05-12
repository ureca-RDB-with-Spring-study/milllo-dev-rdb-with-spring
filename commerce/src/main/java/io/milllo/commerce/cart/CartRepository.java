package io.milllo.commerce.cart;

import io.milllo.commerce.customer.Customer;
import io.milllo.commerce.product.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CartRepository {
    private final JdbcTemplate jdbcTemplate;

    public CartRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    // RowMapper: DB 결과 -> Cart 객체로 변환
    private final RowMapper<Cart> cartRowMapper = (rs, rowNum) -> new Cart(
            rs.getInt("cart_id"),
            rs.getInt("customer_id"),
            rs.getInt("product_id"),
            rs.getInt("price"),
            rs.getInt("quantity"),
            rs.getTimestamp("created_at").toLocalDateTime()
    );

    private final RowMapper<CartResponseDTO> cartResponseDtoRowMapper = (rs, rowNum) -> new CartResponseDTO(
            rs.getInt("cart_id"),
            rs.getInt("customer_id"),
            rs.getString("customer_name"),
            rs.getInt("product_id"),
            rs.getString("product_name"),
            rs.getInt("price"),
            rs.getInt("quantity"),
            rs.getInt("total_price")
    );

    public List<CartResponseDTO> findByCustomerId(int customerId) {
        String query = """
                SELECT
                    ca.cart_id,
                    ca.customer_id,
                    c.name AS customer_name,
                    p.product_id,
                    p.name AS product_name,
                    p.price,
                    ca.quantity,
                    p.price * ca.quantity AS total_price
                FROM carts ca
                INNER JOIN customers c ON ca.customer_id = c.customer_id
                INNER JOIN products p ON ca.product_id = p.product_id
                WHERE ca.customer_id = ?
                """;
        return jdbcTemplate.query(query, cartResponseDtoRowMapper, customerId);
    }
}
