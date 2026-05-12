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
            rs.getInt("stock_quantity"),
            rs.getInt("category_id")
    );

    private final RowMapper<ProductResponseDto> productResponseDtoRowMapper = (rs, rowNum) -> new ProductResponseDto(
            rs.getInt("product_id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getInt("price"),
            rs.getInt("stock_quantity"),
            rs.getInt("category_id"),
            rs.getString("category_name")
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
    public Optional<Product> findById(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        List<Product> result = jdbcTemplate.query(query, productRowMapper, productId);
        return result.stream().findFirst();
    }

    // READ - 전체 상품 목록(카테고리 이름 포함)
    public List<ProductResponseDto> findAll() {
        String query = """
                SELECT
                    p.product_id,
                    p.name,
                    p.description,
                    p.price,
                    p.stock_quantity,
                    p.category_id,
                    c.name AS category_name
                FROM products p
                INNER JOIN categories c ON p.category_id = c.category_id
                ORDER BY p.name ASC
                """;
        return jdbcTemplate.query(query, productResponseDtoRowMapper);
    }

    // READ - 카테고리별 상품 목록
    public List<ProductResponseDto> findByCategory(String categoryName) {
        String query = """
                SELECT
                    p.product_id,
                    p.name,
                    p.description,
                    p.price,
                    p.stock_quantity,
                    p.category_id,
                    c.name AS category_name
                FROM products p
                INNER JOIN categories c ON p.category_id = c.category_id
                WHERE c.name = ?
                ORDER BY p.name ASC
                """;
        return jdbcTemplate.query(query, productResponseDtoRowMapper, categoryName);
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
