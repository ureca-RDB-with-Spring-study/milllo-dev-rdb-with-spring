import java.time.LocalDateTime;

public class Order {
    private int orderId;
    private int customerId;
    private int productId;
    private int quantity;
    private LocalDateTime orderDate;
    private String status;

    public Order(
            int orderId,
            int customerId,
            int productId,
            int quantity,
            String status) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = LocalDateTime.now();
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getProudctId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
