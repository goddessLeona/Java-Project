package Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {

    CustomerRepository customerRepository = new CustomerRepository();


    //get all customers
    public ArrayList<Customer> getAllCustomer() throws SQLException {
        System.out.println("\nThis is all customers on record\n");
        return customerRepository.getAll();
    }

    //get customer by id
    public ArrayList<Customer>getCustomerById(int customer_id) throws SQLException{
        return customerRepository.getCustomerByID(customer_id);
    }

    // add new cusomer
    public boolean addNewCustomer(Customer customer) throws SQLException{
        return customerRepository.addCustomer(customer);
    }

    // uppdate email
    public boolean uppdateCustomerEmail(Customer customer) throws SQLException {
        return customerRepository.uppdateEmail(customer);
    }

    // check  if customer id exists
    public boolean validateIfCustomerExist(int customerId) throws SQLException {
        return customerRepository.checkCustomerId(customerId);
    }

    //check if name exist in database
    public boolean checkNameExist(String name) throws SQLException {
        return customerRepository.checkNameDatabase(name);
    }

    //check if email exist in database
    public boolean checkEmailExist(String email) throws SQLException {
        return customerRepository.checkEmailDatabase(email);
    }

    //check if password exist in database.
    public boolean checkPasswordExist(String email,String password) throws SQLException {
        return customerRepository.checkPasswordDatabase(email, password);
    }

    //get customer by email
    public ArrayList<Customer> getCustomerByEmail(String email)throws SQLException{
        return customerRepository.getCustomerByEmail(email);
    }

    public int customerIdByEmail(String email)throws SQLException{
        return customerRepository.getCustomerIdByEmail(email);
    }

}
