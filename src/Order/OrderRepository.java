package Order;

import Customer.Customer;
import Orders_products.Orders_products;
import Product.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderRepository {

    public static final String URL = "jdbc:sqlite:webbutiken.db";

    // Hämtar alla order
    public ArrayList<Order> getAllOrder() throws SQLException {

        ArrayList<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders")) {

            while (rs.next()) {
                Order order = new Order
                        (rs.getTimestamp("order_date"),rs.getInt("customer_id"), rs.getInt("order_id"));

                orders.add(order);
            }
        }
        return orders;
    }

    // Get order history for a customer using customer id
    public ArrayList<OrderDetaljs> orderHistory(int customer_id) throws SQLException{

        ArrayList<OrderDetaljs> orderDetaljslist = new ArrayList<>();

        String sql = "SELECT customers.name AS customerName, products.name, orders_products.unit_price, orders_products.quantity, orders.order_date " +
                "FROM orders JOIN orders_products ON orders.order_id = orders_products.order_id " +
                "JOIN products ON orders_products.product_id = products.product_id " +
                "JOIN customers ON orders.customer_id = customers.customer_id " +
                "WHERE customers.customer_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setInt(1, customer_id);

            try(ResultSet rs = pstms.executeQuery()){
                while(rs.next()){

                    Product productNew = new Product(rs.getString("name"));

                    Customer customerNew = new Customer(rs.getString("customerName"));

                    Order orderNew = new Order(rs.getTimestamp("order_date"));

                    Orders_products ordersProductsNew = new Orders_products
                            (rs.getInt("quantity"),rs.getDouble("unit_price"));

                    OrderDetaljs everything = new OrderDetaljs(ordersProductsNew,customerNew,productNew,orderNew);

                    orderDetaljslist.add(everything);
                }
            }
        }
        return orderDetaljslist;
    }

    // Make an order
    public int orderNew(int customerId) throws SQLException{

        String sql = "INSERT INTO orders (customer_id, order_date) VALUES (?,?)";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){

            pstms.setInt(1,customerId);
            pstms.setTimestamp(2,currentTime);

            int rowsAffected = pstms.executeUpdate();
            if (rowsAffected == 0){
                throw new SQLException("Creating order failed");
            }

            try(ResultSet generatedKeys = pstms.getGeneratedKeys()){
                if(generatedKeys.next()){
                    //saving the auto generated  order_id save nr in variable
                    int generatedOrderId = generatedKeys.getInt(1);
                    System.out.println("New order created with Id: " + generatedOrderId);
                    System.out.println("order timestamp: " + currentTime);
                    //returning the order id to the method where you make the order
                    return generatedOrderId;
                }else{
                    throw new SQLException("Creating order failed, no Id obtained");
                }
            }
        }

    }

    // Get order history for a customer using email
    public ArrayList<OrderDetaljs> orderHistory(String email) throws SQLException{

        ArrayList<OrderDetaljs> orderDetaljslist = new ArrayList<>();

        String sql = "SELECT customers.name AS customerName, products.name, orders_products.unit_price, orders_products.quantity, orders.order_date " +
                "FROM orders JOIN orders_products ON orders.order_id = orders_products.order_id " +
                "JOIN products ON orders_products.product_id = products.product_id " +
                "JOIN customers ON orders.customer_id = customers.customer_id " +
                "WHERE customers.email = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setString(1, email);

            try(ResultSet rs = pstms.executeQuery()){
                while(rs.next()){

                    Product productNew = new Product(rs.getString("name"));
                    Customer customerNew = new Customer(rs.getString("customerName"));
                    Order orderNew = new Order(rs.getTimestamp("order_date"));

                    Orders_products ordersProductsNew = new Orders_products
                            (rs.getInt("quantity"),rs.getDouble("unit_price"));

                    OrderDetaljs everything = new OrderDetaljs(ordersProductsNew,customerNew,productNew,orderNew);
                    orderDetaljslist.add(everything);

                }
            }
        }catch (SQLException e){
            System.out.println("Error getting order history using email");
        }

        return orderDetaljslist;

    }


}
