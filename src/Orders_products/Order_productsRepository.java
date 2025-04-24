package Orders_products;

import Product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order_productsRepository {

    public static final String URL = "jdbc:sqlite:webbutiken.db";

    // Get all order_products
    public ArrayList<Orders_products> AllOrdersProducts() throws SQLException {

        ArrayList<Orders_products> orders_products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM orders_products")) {

            while (rs.next()) {
                Orders_products orderProduct = new Orders_products
                        (rs.getInt("order_product_id"),rs.getInt("order_id"),
                                rs.getInt("product_id"), rs.getInt("quantity"),
                                rs.getDouble("unit_price"));

                orders_products.add(orderProduct);

            }
        }
        return orders_products;
    }

    // Make an order with various products
    public boolean orderManyProducts(int order_id, List<Orders_products> productsList) throws SQLException{

        String sql = "INSERT INTO orders_products (order_id, product_id, unit_price, quantity) VALUES (?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)){

            for(Orders_products op : productsList){
                pstms.setInt(1, order_id);
                pstms.setInt(2,op.getProduct_id());
                pstms.setDouble(3,op.getUnit_price());
                pstms.setInt(4,op.getQuantity());
                pstms.addBatch();
            }

            int[]result = pstms.executeBatch();
            return result.length == productsList.size();
        }
    }
}
