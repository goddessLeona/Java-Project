package Order;

import java.sql.SQLException;
import java.util.Scanner;

public class OrderController {

    MenuMethodsOrder orderMethods = new MenuMethodsOrder();

    public void runOrderMenu() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\u001B[35m" + "╔════════════ Order Menu ══════════════╗");
            System.out.println("║ 1. Get all order                      ║");
            System.out.println("║ 2. Get order history by customer id   ║");
            System.out.println("║ 3. Make a order                       ║");
            System.out.println("║ 4. Get all orders_products            ║");
            System.out.println("║ 0. Exit and get to main Menu          ║");
            System.out.println("╚═══════════════════════════════════════╝" + "\u001B[0m");
            System.out.print("\u001B[33mSelect an option: \u001B[0m");
            String select = scanner.nextLine();

            switch (select) {
                case "1":
                    orderMethods.getAllOrder();
                    break;
                case "2":
                    orderMethods.getOrderHistoryByCustomerId();
                    break;
                case "3":
                    orderMethods.MakeOrder();
                    break;
                case "4":
                    orderMethods.getAllOrder_products();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }

    }
}
