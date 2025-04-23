import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();

    public ArrayList<Customer> getAllCustomer() throws SQLException {
        System.out.println("This is our logical layer");
        System.out.println();
        return customerRepository.getAll();
    }
}
