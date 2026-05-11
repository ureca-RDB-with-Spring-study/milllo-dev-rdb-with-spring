package io.milllo.commerce.order;

import io.milllo.commerce.customer.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductOrderRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductOrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper: DB 결과 -> ProductOrder 객체로 변환
    private final RowMapper<ProductOrder> orderRowMapper = (rs, rowNum) -> new ProductOrder(
            rs.getInt("order_id"),
            rs.getInt("customer_id"),
            rs.getInt("product_id"),
            rs.getInt("quantity"),
            rs.getTimestamp("order_date").toLocalDateTime(),
            rs.getString("status"),
            rs.getInt("total_price")
    );

    //CREATE
    public int save(ProductOrder order) {
        String query = "INSERT INTO orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";
        return jdbcTemplate.update(
                query,
                order.getCustomerId(),
                order.getProductId(),
                order.getQuantity()
        );
    }

    //READ - 전체 조회
    public List<ProductOrder> findAll() {
        String query = "SELECT * FROM orders";
        return jdbcTemplate.query(query, orderRowMapper);
    }

    //READ - 단건 조회
    public Optional<ProductOrder> findById(int orderId) {
        String query = "SELECT * FROM orders WHERE order_id = ?";
        List<ProductOrder> result = jdbcTemplate.query(query, orderRowMapper, orderId);
        return result.stream().findFirst();
    }

    //UPDATE
    public int update(ProductOrder order) {
        String query = "UPDATE orders SET quantity = ? WHERE order_id = ?";
        return jdbcTemplate.update(
                query,
                order.getQuantity(),
                order.getOrderId()
        );
    }

    //DELETE
    public int delete(int orderId) {
        String query = "DELETE FROM orders WHERE order_id = ?";
        return jdbcTemplate.update(query, orderId);
    }
}
