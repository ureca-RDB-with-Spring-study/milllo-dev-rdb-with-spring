package io.milllo.commerce;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final JdbcTemplate jdbcTemplate;
    private final Random random = new Random();

    @Override
    public void run(ApplicationArguments args) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM orders", Integer.class);

        if (count != null && count > 0) {
            System.out.println("데이터 이미 존재, 적재 스킵");
            return;
        }

        insertCustomers(1000);
        insertProducts();
        insertOrders(100000);
        System.out.println("더미 데이터 적재 완료!");
    }

    private void insertCustomers(int count) {
        String sql = "INSERT INTO customers (name, email, password, address) VALUES (?, ?, ?, ?)";
        for (int i = 1; i <= count; i++) {
            jdbcTemplate.update(sql,
                    "고객" + i,
                    "user" + i + "@test.com",
                    "1234",
                    "주소" + i
            );
        }
    }

    private void insertProducts() {
        String sql = "INSERT INTO products (name, price, stock_quantity) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, List.of(
                new Object[]{"맥북 프로", 3000000, 10},
                new Object[]{"아이폰 15", 1500000, 20},
                new Object[]{"에어팟 프로", 350000, 50},
                new Object[]{"아이패드", 1200000, 15},
                new Object[]{"애플워치", 550000, 30}
        ));
    }

    private void insertOrders(int count) {
        String sql = "INSERT INTO orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            batchArgs.add(new Object[]{
                    random.nextInt(1000) + 1,
                    random.nextInt(5) + 1,
                    random.nextInt(5) + 1
            });
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);  // 한 번에 전송
    }
}
