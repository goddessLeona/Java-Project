package Customer;

import java.sql.*;
import java.util.ArrayList;

public class  CustomerRepository {

    public static final String URL = "jdbc:sqlite:webbutiken.db";

    // Get all customer
    public ArrayList<Customer> getAll() throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                Customer customer = new Customer
                        (rs.getInt("customer_id"),rs.getString("name"),
                        rs.getString("email"),rs.getString("phone"),
                        rs.getString("address"),rs.getString("password"));

                customers.add(customer);
            }
        }
        return customers;
    }

    // Add customer
    // this got a bit messy, first one I did, before we did it with bill, if time I will look over.
    public boolean addCustomer(Customer customer) throws SQLException {

        String sql = "INSERT INTO customers(name,email,phone,address,password) VALUES(?,?,?,?,?)";

        try
            (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setString(1, customer.getName());
            pstms.setString(2, customer.getEmail());
            pstms.setString(3, customer.getPhoneNummber());
            pstms.setString(4, customer.getAddress());
            pstms.setString(5, customer.getPassword());

            int rowsAffected = pstms.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedkeys = pstms.getGeneratedKeys()) {
                    if (generatedkeys.next()) {
                        int generatedCustomerId = generatedkeys.getInt(1);
                        System.out.println("Generated Customer id: " + generatedCustomerId);
                        customer.setCustermerId(generatedCustomerId);
                    }
                }
                return true;
            }
        }catch (SQLException e){
            System.out.println("Error adding a new customer to the databse: " + e.getMessage());
        }
        return false;
    }

    //update email with help of customer id
    public boolean uppdateEmail(Customer customer) throws SQLException {

        String sql = "UPDATE customers SET email = ? WHERE customer_id=?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setString(1, customer.getEmail());
            pstms.setInt(2,customer.getCustermerId());

            int rowsAffected = pstms.executeUpdate();
            return rowsAffected>0;

        }catch (SQLException e){
            System.out.println("Error updating email." + e.getMessage());
        }
        return false;
    }

    //Check if the customer id exist in the database
    public boolean checkCustomerId(int customerId) throws SQLException{

        String sql = "SELECT COUNT (*) FROM customers WHERE customer_id = ?";

        try
            (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setInt(1,customerId);

            ResultSet rs = pstms.executeQuery();

            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch (SQLException e){
            System.out.println("Error checking customer id. "+ e.getMessage());
        }
        return false;
    }

    //Check id Name exist in database.
    public boolean checkNameDatabase(String name) throws SQLException {

        String sql = "SELECT COUNT (*) FROM customers WHERE name = ?";

        try
            (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setString(1,name);

            try(ResultSet rs = pstms.executeQuery()){
                if(rs.next()){
                    int count = rs.getInt(1);
                    return count >0;
                }
            }
        }catch (SQLException e){
            System.out.println("Error checking name in database. " + e.getMessage());
        }
        return false;
    }

    //Check if email exist in database.
    public boolean checkEmailDatabase(String email) throws SQLException {

        String sql = "SELECT COUNT (*) FROM customers WHERE email = ?";

        try
                (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setString(1,email);

            try(ResultSet rs = pstms.executeQuery()){
                if(rs.next()){
                    int count = rs.getInt(1);
                    return count >0;
                }
            }
        }catch (SQLException e){
            System.out.println("Error checking email in database. " + e.getMessage());
        }
        return false;
    }

    //Check if password exist in database.
    public boolean checkPasswordDatabase(String email, String password) throws SQLException {

        String sql = "SELECT * FROM customers WHERE email = ? AND password = ?";

        try
            (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setString(1,email);
            pstms.setString(2,password);

            try(ResultSet rs = pstms.executeQuery()){
                return rs.next();
            }
        }catch (SQLException e){
            System.out.println("Error checking email & password. " + e.getMessage());
        }
        return false;
    }

    // get customer by id
    public ArrayList<Customer> getCustomerByID(int customer_id) throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,customer_id);

            try(ResultSet rs = pstmt.executeQuery()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"),
                        rs.getString("address"), rs.getString("password"));

                customers.add(customer);
            }

        }catch (SQLException e){
            System.out.println("Error finding Customer by id. " + e.getMessage());
        }

        return customers;
    }

    //get customer by email
    public ArrayList<Customer> getCustomerByEmail(String email) throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,email);

            try(ResultSet rs = pstmt.executeQuery()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("phone"),
                        rs.getString("address"), rs.getString("password"));

                customers.add(customer);
            }

        }catch (SQLException e){
            System.out.println("Error finding Customer by email. " + e.getMessage());
        }

        return customers;
    }

    //get customer id by email
    public int getCustomerIdByEmail (String email) throws SQLException{

        String sql= "SELECT customer_id FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    return rs.getInt("customer_id");
                }else{
                    throw new SQLException("No customer found with email: " + email);
                }
            }
        }catch (SQLException e){
            System.out.println("Error finding customer id by email " + e.getMessage());
            throw e;
        }
    }



}
