package Customer;

import java.sql.SQLException;
import java.util.Scanner;

public class CustomerController {

    MenuMethodCustomers customerMenu = new MenuMethodCustomers();
    CustomerLoggedIn loggInCustomers = new CustomerLoggedIn();


    // Customer menu
    public void runCustomerMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("\n\u001B[35m" + "╔════════════ Customer Menu ════════════╗");
            System.out.println("║ 1. Get all customers                  ║");
            System.out.println("║ 2. Add customer                       ║");
            System.out.println("║ 3. Update a customers email           ║");
            System.out.println("║ 4. Customer LOG IN                    ║");
            System.out.println("║ 0. Exit and get to main Menu          ║");
            System.out.println("╚═══════════════════════════════════════╝" + "\u001B[0m");
            System.out.print("\u001B[33mSelect an option: \u001B[0m");
            String select = scanner.nextLine();

            switch (select){
                case "1":
                    customerMenu.getAllCustomerMethod();
                break;
                case "2":
                    customerMenu.addNewCustomerMethod();
                break;
                case "3":
                    customerMenu.updateEmailMethod();
                break;
                case "4":
                    loggInCustomers.logIn();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }

}
