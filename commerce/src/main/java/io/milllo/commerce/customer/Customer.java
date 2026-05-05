package io.milllo.commerce.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer customerId;
    private String name;
    private String email;
    private String password;
    private String address;
    private LocalDateTime joinDate;
}
