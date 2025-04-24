import Customer.CustomerController;
import Order.OrderController;
import Product.ProductController;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {

    CustomerController customerMenu = new CustomerController();
    ProductController productMenu = new ProductController();
    OrderController orderMenu = new OrderController();

    public void runMainMenu() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nwelcome to my java class exam exercise");
        System.out.println("You have now reached the menu and can pick an option");

        while(true) {
            System.out.println("\n\u001B[35m" + "╔════════════ Main Menu ════════════╗");
            System.out.println("║ 1. Go to the Customer menu.       ║");
            System.out.println("║ 2. Go to the Product menu.        ║");
            System.out.println("║ 3. Go to the Order menu.          ║");
            System.out.println("║ 0 Exit the program                ║");
            System.out.println("╚═══════════════════════════════════╝" + "\u001B[0m");

            System.out.print("\u001B[33mSelect an option: \u001B[0m");
            String select = scanner.nextLine();

            switch (select){
                case "1" :
                    customerMenu.runCustomerMenu();
                break;
                case "2" :
                    productMenu.runProductsMenu();
                break;
                case "3" :
                    orderMenu.runOrderMenu();
                break;
                case "0":
                    System.out.println("\n\u001B[31mProgram is getting closed down\u001B[0m");
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }
}
