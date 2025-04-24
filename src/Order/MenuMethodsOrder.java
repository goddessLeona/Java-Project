package Order;

import Customer.CustomerService;
import Customer.MenuMethodCustomers;
import Orders_products.Orders_products;
import Orders_products.Orders_productsService;
import Product.MenuMethodProduct;
import Product.ProductService;
import Validation.Validation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuMethodsOrder {

    OrderService orderService = new OrderService();
    CustomerService customerService = new CustomerService();
    MenuMethodCustomers menuMethodCustomers = new MenuMethodCustomers();
    MenuMethodProduct menuMethodProduct = new MenuMethodProduct();
    Orders_productsService orders_productsService = new Orders_productsService();
    Validation validation = new Validation();
    ProductService productService = new ProductService();
    Discount discount = new DiscountOver3000();


    Scanner scanner = new Scanner(System.in);

    public void getAllOrder() throws SQLException {
        System.out.println("this is all the Orders made:");
        ArrayList<Order> orders = orderService.ordersGetAll();

        for(Order order : orders){
            System.out.println("Order id: " + order.getOrder_id());
            System.out.println("Customer id: " + order.getCustomer_id());
            System.out.println("Order date: " + order.getOrder_date());
            System.out.println();
        }
    }

    public void getAllOrder_products() throws SQLException {
        System.out.println("this is all Orders_products information:\n");
        ArrayList<Orders_products> orderInfo = orders_productsService.getAllOrdersProducts();

        for(Orders_products op : orderInfo){
            System.out.println("Order_product_id: " + op.getOrder_product_id());
            System.out.println("Order_id: " + op.getOrder_id());
            System.out.println("Product_id: " + op.getProduct_id());
            System.out.println("price: " + op.getUnit_price());
            System.out.println("Quantity of products: " + op.getQuantity());
            System.out.println();

        }
    }

    public void getOrderHistoryByCustomerId() throws SQLException {

        try {

            int customer_id;

            while(true){
                System.out.println("Write a customer id to get order history");
                String input = scanner.nextLine();
                if (validation.numberValidation(input)){
                    customer_id = Integer.parseInt(input);
                    if(customerService.validateIfCustomerExist(customer_id)){
                        break;
                    }else{
                        System.out.println("There are no customer with that id");
                    }
                }else{
                    System.out.println("You have to and a customer_id number");
                }
            }

            ArrayList<OrderDetaljs> customerOrderHistory = orderService.orderHistoryCustomer(customer_id);

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

        } catch (Exception e) {
            System.out.println("A database error occurred");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void MakeOrder() throws SQLException {

        try {
            System.out.println("To make an order follow instructions.");
            int customer_id;

            while (true) {
                System.out.println("Write in your customer id to make an order");
                String input = scanner.nextLine();
                if (validation.numberValidation(input)) {
                    customer_id = Integer.parseInt(input);
                    if (customerService.validateIfCustomerExist(customer_id)) {
                        break;
                    } else {
                        System.out.println("There are no customer with that id");
                    }
                } else {
                    System.out.println("You have to write in your customer_id number");
                }
            }

            System.out.println("Welcome customer : ");
            menuMethodCustomers.getCustomerByIDMethod(customer_id);

            //get the generated order id
            int generatedOrderId = orderService.makeOrder(customer_id);

            System.out.println("Order added successfully!");
            makeOrderPart2(generatedOrderId);


        } catch (Exception e) {
            System.out.println("Database problems");
            throw new RuntimeException(e);
        }
    }

    public void makeOrderPart2(int order_id) throws  SQLException{

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
                        }else{
                            System.out.println("There are no product with that id number");
                        }
                    }else{
                        System.out.println("You have to write in a product id number");
                    }
                }

                menuMethodProduct.getProductsUsingId(product_id);

                double unit_price = productService.getPriceProduct(product_id);
                System.out.println("The products price :" + unit_price);

                int stock_Quantety = productService.getQuantetyProductByID(product_id);

                int quantity =0;
                while(true){

                    System.out.println("Add the quantity you like to order");
                    String input = scanner.nextLine();

                    if (validation.numberValidation(input)) {
                        quantity = Integer.parseInt(input);

                        if(stock_Quantety >0 && quantity <= stock_Quantety){
                            break;
                        }else{
                            System.out.println("There are currently only " + stock_Quantety + " products in stock\n");
                        }
                    } else {
                         System.out.println("You have to add a number");
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


}
