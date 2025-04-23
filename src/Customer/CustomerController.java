import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {

    CustomerService customerService = new CustomerService();

    public void runmenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Hämta all kunder");
        String select = scanner.nextLine();

        switch (select){
            case "1":
                ArrayList<Customer> customers = customerService.getAllCustomer();
                for(Customer customer: customers){
                    System.out.println("kundId " + customer.getCustermerId() );
                    System.out.println("Custumer name: " + customer.getName());
                }
        }
    }

}
