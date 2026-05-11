package io.milllo.commerce.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper: DB -> Product 객체로 변환
    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
            rs.getInt("product_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getInt("price"),
            rs.getInt("stock_quantity")
    );

    // CREATE
    public int save(Product product) {
        String query = "INSERT INTO products (name, description, price, stock_quantity) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
          query,
          product.getName(),
          product.getDescription(),
          product.getPrice(),
          product.getStockQuantity()
        );
    }

    // READ
    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        return jdbcTemplate.query(query, productRowMapper);
    }

    // READ
    public Optional<Product> findById(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        List<Product> result = jdbcTemplate.query(query, productRowMapper, productId);
        return result.stream().findFirst();
    }

    // UPDATE
    public int update(Product product) {
        String query = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
        return jdbcTemplate.update(
                query,
                product.getStockQuantity(),
                product.getProductId()
        );
    }

    public int delete(int productId) {
        String query = "DELETE FROM products WHERE product_id = ?";
        return jdbcTemplate.update(query, productId);
    }
}
