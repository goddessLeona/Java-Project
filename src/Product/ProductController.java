package Product;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {

    MenuMethodProduct methodsProduct = new MenuMethodProduct();

    //Product menu
    public void runProductsMenu() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        while(true) {

            System.out.println("\n\u001B[35m" + "╔════════════ Product Menu ═══════════════╗");
            System.out.println("║ 1. Get all products                     ║");
            System.out.println("║ 2. Search product using name            ║");
            System.out.println("║ 3. Search product by category           ║");
            System.out.println("║ 4. Update Price                         ║");
            System.out.println("║ 5. Update the stock quantity            ║");
            System.out.println("║ 6. Add a new product                    ║");
            System.out.println("║ 0. Exit and go back to main Menu        ║");
            System.out.println("╚═════════════════════════════════════════╝" + "\u001B[0m");
            System.out.print("\u001B[33mSelect an option: \u001B[0m");
            String select1 = scanner.nextLine();

        switch (select1){
            case "1":
                methodsProduct.getAllProducts();
                break;
            case "2":
                methodsProduct.searchByName();
                break;
            case "3":
                methodsProduct.searchByCategory();
                break;

            case "4":
                methodsProduct.updateProductPrice();
                break;
            case "5":
               methodsProduct.updateStock_quantity();
                break;
            case "6":
                methodsProduct.addNewProduct();
                break;
            case "0":
                return;
            default:
                System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
        }
        }
    }
}
