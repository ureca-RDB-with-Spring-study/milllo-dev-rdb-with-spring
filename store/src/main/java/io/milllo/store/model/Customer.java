import java.time.LocalDateTime;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private LocalDateTime joinDate;

    public Customer(
            String name,
            String email,
            String password,
            String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.joinDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void changeAddress(String address) {

        this.address = address;

    }
}
