package Customer;

import Order.Discount;
import Order.DiscountOver3000;
import Order.OrderDetaljs;
import Order.OrderService;
import Orders_products.Orders_products;
import Orders_products.Orders_productsService;
import Product.MenuMethodProduct;
import Product.ProductService;
import Validation.Validation;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerLoggedIn {

    private String loggedInEmail;
    private Instant logIntime;

     CustomerService loggIn = new CustomerService();
     Validation validation = new Validation();
     MenuMethodCustomers methodCustomers = new MenuMethodCustomers();
     OrderService orderService = new OrderService();
     ProductService productService = new ProductService();
     MenuMethodProduct menuMethodProduct = new MenuMethodProduct();
     Orders_productsService orders_productsService = new Orders_productsService();
     Discount discount = new DiscountOver3000();

    public void logIn() throws SQLException {

        System.out.println(" Log in using your email and password");

        loggedInEmail = logInEmail();

        if (loggedInEmail != null) {
            logInPassword();
        }

    }

    public String logInEmail() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.print("\u001B[33mWrite in your email: \u001B[0m");
                String email = scanner.nextLine();

                if (loggIn.checkEmailExist(email)) {
                    System.out.println("Email exist");
                    return email;
                } else {
                    System.out.println("Email does not exist");
                    System.out.println(" Do you want to try again ?");
                    System.out.println("yes or no");

                    String response = scanner.nextLine();
                    if (validation.yesNoValidation(response)) {
                        if (response.equalsIgnoreCase("no")) {
                            return null;
                        }
                    } else {
                        System.out.println("Please answer YES or NO ");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Database problems");
            throw new RuntimeException(e);
        }
    }

    public void logInPassword() throws SQLException {

        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                System.out.print("\u001B[33mWrite in your password: \u001B[0m");
                String password = scanner.nextLine();

                if (loggIn.checkPasswordExist(loggedInEmail, password)) {
                    menuCustomersLoggedIn();
                    return;
                } else {
                    System.out.println("Incorrect password.");
                    System.out.println("You want to try again ?");
                    System.out.println("yes or no");

                    String response = scanner.nextLine();
                    if (validation.yesNoValidation(response)) {
                        if (response.equalsIgnoreCase("no")) {
                            return;
                        }
                    } else {
                        System.out.println("Please answer YES or NO ");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Database problem");
            throw new RuntimeException(e);
        }
    }

    public void menuCustomersLoggedIn() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nYou are now logged in");
        System.out.println("Welcome: ");
        methodCustomers.getCustomerByEmailMethod(loggedInEmail);

        logIntime = Instant.now();
        System.out.println("You logged in: " + logIntime);

        while (true) {
            System.out.println("\n\u001B[35m" + "╔════════════ Your Menu ════════════╗");
            System.out.println("║ 1. Get your order history         ║");
            System.out.println("║ 2. Make an order                  ║");
            System.out.println("║ 3. Customer LOG out               ║");
            System.out.println("╚═══════════════════════════════════╝" + "\u001B[0m");
            System.out.print("\u001B[33mSelect an option: \u001B[0m");
            String select = scanner.nextLine().trim();

            switch (select) {
                case "1":
                    getOrderHistoryByEmail();
                    break;
                case "2":
                    makeOrderCustomer();
                    break;
                case "3":
                    loggOut();
                    return;
                default:
                    System.out.println("\u001B[31mInvalid choice. Please try again.\u001B[0m");
            }
        }
    }

    public void getOrderHistoryByEmail() throws SQLException {
        ArrayList<OrderDetaljs> customerOrderHistory = orderService.orderHistoryCustomer(loggedInEmail);

        if (customerOrderHistory.isEmpty()) {
            System.out.println("The customer has no order history.");
        } else {
            for (OrderDetaljs orderDetaljs : customerOrderHistory) {
                System.out.println("Customer name: " + orderDetaljs.getCustomer().getName());
                System.out.println("Product name: " + orderDetaljs.getProduct().getName());
                System.out.println("Product price: " + orderDetaljs.getOrders_products().getUnit_price());
                System.out.println("Product quantity: " + orderDetaljs.getOrders_products().getQuantity());
                System.out.println();
            }
        }
    }

    public void makeOrderCustomer() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("To make an order follow instructions.");
            int customerId = loggIn.customerIdByEmail(loggedInEmail);

            //get the generated order id
            int generatedOrderId = orderService.makeOrder(customerId);

            System.out.println("Order added successfully!");
            makeOrderPart2Customer(generatedOrderId);

        } catch (Exception e) {
            System.out.println("Database problems");
            throw new RuntimeException(e);
        }
    }

    public void makeOrderPart2Customer(int order_id) throws  SQLException{

        Scanner scanner = new Scanner(System.in);
        List<Orders_products> ordersProductsList = new ArrayList<>();


        try{
            System.out.println("\nNow you can add your products to your order");
            System.out.println("If you buy over 3000 you will get a 10% discount\n");

            double totalpriceOrder = 0;
            boolean addMore= true;

            while (addMore){
                Orders_products orderP = new Orders_products();

                int product_id;

                while(true){
                    System.out.println("Add the product ID of the product you want to order");
                    String input = scanner.nextLine();
                    if(validation.numberValidation(input)){
                        product_id = Integer.parseInt(input);
                        if(productService.CheckProductIdExist(product_id)){
                            break;
                        }
                    }
                }

                menuMethodProduct.getProductsUsingId(product_id);

                double unit_price = productService.getPriceProduct(product_id);
                System.out.println("The products price :" + unit_price);

                int stock_Quantety = productService.getQuantetyProductByID(product_id);

                int quantity;
                while (true){
                    System.out.println("Add the quantity you like to order");
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if(stock_Quantety>quantity){
                        break;
                    }else{
                        System.out.println("There are currently only " + stock_Quantety + " products in stock\n");
                    }
                }

                double totalPriceQ = quantity * unit_price;
                totalpriceOrder = totalpriceOrder + totalPriceQ;

                orderP.setOrder_id(order_id);
                orderP.setProduct_id(product_id);
                orderP.setUnit_price(unit_price);
                orderP.setQuantity(quantity);

                ordersProductsList.add(orderP);

                System.out.println("You want to add one more product to the order?");
                System.out.println("yes or no");
                String response = scanner.nextLine();

                if (validation.yesNoValidation(response)){
                    addMore = response.equalsIgnoreCase("yes");
                }else{
                    System.out.println("Please answer YES or NO ");
                }

            }

            boolean success = orders_productsService.orderMultipleProducts(order_id,ordersProductsList);

            if (success){
                System.out.println("You have now made a order with many products");
                System.out.println("The total price on your order is : " + totalpriceOrder);
                double discountedTotal = discount.applyDiscount(totalpriceOrder);
                System.out.println("Price after discount: " + discountedTotal);
            }else{
                System.out.println("Ohh no something went wrong when you made the order");
            }

        } catch (Exception e) {
            System.out.println("Data base error");
            e.printStackTrace();
        }

    }

    public void loggOut(){
        System.out.println("You are now about to get logged out");
        Instant logoutTime = Instant.now();
        System.out.println("You logged out: " + logoutTime);
        Duration session = Duration.between(logIntime,logoutTime);

        long minutes = session.toMinutes();
        long seconds = session.getSeconds()% 60;
        System.out.println("You have been logged in for : " + minutes + " minutes and " + seconds + " seconds.");
    }

}

