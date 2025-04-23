import java.sql.*;
import java.util.ArrayList;

public class  CustomerRepository {

    public static final String URL = "jdbc:sqlite:webbutiken.db";

    public ArrayList<Customer> getAll() throws SQLException {

        ArrayList<Customer> customers = new ArrayList<>();

        System.out.println("Detta är metoden för att hämta alla customers getAll()");
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"),rs.getString("name"),
                        rs.getString("email"),rs.getString("phone"),
                        rs.getString("address"),rs.getString("password"));

                customers.add(customer);
            }
        }
        return customers;
    }
}
