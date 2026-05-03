public class Product {
    private int id;
    private String name;
    private String description;
    private int price;
    private int stockQuantity;

    public Product(
            int id,
            String name,
            String description,
            int price,
            int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }
}
