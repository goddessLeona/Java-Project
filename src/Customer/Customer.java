package Customer;

public class Customer {

    private int custermerId;
    private String name;
    private String email;
    private String phoneNummber;
    private String address;
    private String password;

    public Customer() {
        // Empty constructor
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(int custermerId) {
        this.custermerId = custermerId;
    }

    public Customer(int custermerId, String name, String email, String phoneNummber, String address, String password) {
        this.custermerId = custermerId;
        this.name = name;
        this.email = email;
        this.phoneNummber = phoneNummber;
        this.address = address;
        this.password = password;
    }

    public int getCustermerId() {
        return custermerId;
    }

    public void setCustermerId(int custermerId) {
        this.custermerId = custermerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNummber() {
        return phoneNummber;
    }

    public void setPhoneNummber(String phoneNummber) {
        this.phoneNummber = phoneNummber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custermerId=" + custermerId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNummber='" + phoneNummber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
