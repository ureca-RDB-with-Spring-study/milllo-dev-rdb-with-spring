package io.milllo.commerce.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

// JdbcTemplate로 SQL을 직접 작성하는 레이어
@Repository
public class CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper: DB 결과 -> Customer 객체로 변환
    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> new Customer(
            rs.getInt("customer_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("address"),
            rs.getTimestamp("join_date").toLocalDateTime()
    );

    //CREATE
    public int save(Customer customer) {
        String query = "INSERT INTO customers (name, email, password, address) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                query,
                customer.getName(),
                customer.getEmail(),
                customer.getPassword(),
                customer.getAddress()
        );
    }

    //READ - 전체 조회
    public List<Customer> findAll() {
        String query = "SELECT * FROM customers";
        return jdbcTemplate.query(query, customerRowMapper);
    }

    //READ - 단건 조회
    public Optional<Customer> findById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        List<Customer> result = jdbcTemplate.query(query, customerRowMapper, customerId);
        return result.stream().findFirst();
    }

    // 장바구니에 담지 않은 고객 조회 (LEFT JOIN + IS NULL)
    public List<CustomerResponseDto> findInactiveCustomers() {
        String sql = """
            SELECT
                c.customer_id,
                c.name,
                c.email
            FROM customers c
            LEFT JOIN carts ca ON c.customer_id = ca.customer_id
            WHERE ca.cart_id IS NULL
            """;
        return jdbcTemplate.query(sql, (rs, rowNum) -> new CustomerResponseDto(
                rs.getInt("customer_id"),
                rs.getString("name"),
                rs.getString("email")
        ));
    }

    //UPDATE
    public int update(Customer customer) {
        String query = "UPDATE customers SET name = ?, address = ? WHERE customer_id = ?";
        return jdbcTemplate.update(
                query,
                customer.getName(),
                customer.getAddress(),
                customer.getCustomerId()
        );
    }

    //DELETE
    public int delete(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        return jdbcTemplate.update(query, customerId);
    }
}
