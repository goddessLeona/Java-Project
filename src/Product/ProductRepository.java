package Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductRepository {

    public static final String URL = "jdbc:sqlite:webbutiken.db";


    // Hämtar alla producter
    public ArrayList<Product> getAllProducts() throws SQLException {

        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Detta är metoden för att hämta alla products getAllProucts()");

        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                Product product = new Product
                        (rs.getInt("product_id"),rs.getInt("manufacturer_id"),
                        rs.getString("name"),rs.getString("description"),
                        rs.getDouble("price"),rs.getInt("stock_quantity"));

                products.add(product);
            }
        }
        return products;
    }

    // Sök efter producter efter deras namn
    public ArrayList<Product> searchProductName(String name) throws SQLException {

        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setString(1, "%" + name + "%");

            try(ResultSet rs = pstms.executeQuery()){
                while(rs.next()){
                    Product product = new Product
                            (rs.getInt("product_id"),rs.getInt("manufacturer_id"),
                                    rs.getString("name"),rs.getString("description"),
                                    rs.getDouble("price"),rs.getInt("stock_quantity"));
                    products.add(product);
                }
            }

        }catch (SQLException e){
            System.out.println("Error finding prodoct using name: " + e.getMessage());
        }
        return products;
    }

    // Search a product by catogory
    public ArrayList<Product> searchByCatogory(String name) throws SQLException{

        ArrayList<Product> products = new ArrayList<>();

        String sql = "SELECT " +
                "products.product_id, " +
                "products.manufacturer_id, " +
                "products.name, " +
                "products.description, " +
                "products.price, " +
                "products.stock_quantity " +
                "FROM products " +
                "JOIN products_categories ON products.product_id = products_categories.product_id " +
                "JOIN categories ON products_categories.category_id = categories.category_id " +
                "WHERE categories.name LIKE ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setString(1, "%" + name + "%");

            try(ResultSet rs = pstms.executeQuery()){
                while(rs.next()){
                    Product product = new Product
                            (rs.getInt("product_id"),rs.getInt("manufacturer_id"),
                                    rs.getString("name"),rs.getString("description"),
                                    rs.getDouble("price"),rs.getInt("stock_quantity"));
                    products.add(product);
                }
            }
            return products;
        }
    }

    // Uppdate price
    public boolean uppdatePrice(Product product) throws SQLException {

        String sql = "UPDATE products SET price = ? WHERE product_id=?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setDouble(1, product.getPrice());
            pstms.setInt(2,product.getPruduct_id());

            int rowsAffected = pstms.executeUpdate();

            return rowsAffected>0;

        }

    }

    // Uppdate stock quantity
    public boolean uppdateStocks(Product product) throws SQLException {

        String sql = "UPDATE products SET stock_quantity = ? WHERE product_id=?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setInt(1,product.getStock_Quantety());
            pstms.setInt(2,product.getPruduct_id());

            int rowsAffected = pstms.executeUpdate();

            return rowsAffected>0;

        }

    }

    // Add a new product
    public boolean addProductNew(Product product)
            throws SQLException {
        String sql = "INSERT into products(manufacturer_id,name,description,price,stock_quantity)VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,product.getManufacturer_id());
            pstmt.setString(2,product.getName());
            pstmt.setString(3,product.getDescription());
            pstmt.setDouble(4,product.getPrice());
            pstmt.setInt(5,product.getStock_Quantety());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        }
    }

    // check if a catogory exist in the database
    public boolean catogoryExist(String name) throws SQLException{

        String sql = "SELECT 1 FROM categories WHERE LOWER (name) = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, name);

            try(ResultSet rs = stmt.executeQuery()){
                return rs.next();
            }
        }

    }

    // get product by id
    public ArrayList<Product> getProductById(int id)throws SQLException{

        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstms = connection.prepareStatement(sql)) {

            pstms.setInt(1, id);

            try(ResultSet rs = pstms.executeQuery()){
                while(rs.next()){
                    Product product = new Product
                            (rs.getInt("product_id"),rs.getInt("manufacturer_id"),
                                    rs.getString("name"),rs.getString("description"),
                                    rs.getDouble("price"),rs.getInt("stock_quantity"));
                    products.add(product);
                }
            }
            return products;
        }
    }

    //Check if the product id exist in the database
    public boolean checkProductId(int productId) throws SQLException{

        String sql = "SELECT COUNT (*) FROM products WHERE product_id = ?";

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setInt(1,productId);

            ResultSet rs = pstms.executeQuery();

            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }
        return false;
    }

    //Check if the manufacture id exist in the database
    public boolean checkManufacturyId(int manufacturer_id) throws SQLException{

        String sql = "SELECT COUNT (*) FROM products WHERE manufacturer_id = ?";

        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement pstms = connection.prepareStatement(sql)){

            pstms.setInt(1,manufacturer_id);

            ResultSet rs = pstms.executeQuery();

            if(rs.next()){
                return rs.getInt(1)>0;
            }
        }catch (SQLException e){
            System.out.println("Error finding manifacture id exist in database: " + e.getMessage());
        }
        return false;
    }

    //Get the price of a product using id
    public double getProductPriceById(int product_id)throws SQLException{

        String sql = "SELECT price FROM products WHERE product_id=?";

        try(Connection conn =DriverManager.getConnection(URL);
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,product_id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getDouble("price");
                }else{
                    throw new SQLException("No product found with ID: " + product_id);
                }
            }
        }
    }

    //Get the price of a product using id
    public int getProductStocksById(int product_id)throws SQLException{

        String sql = "SELECT stock_quantity FROM products WHERE product_id=?";

        try(Connection conn =DriverManager.getConnection(URL);
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,product_id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return rs.getInt("stock_quantity");
                }else{
                    throw new SQLException("No product found with that product ID: " + product_id);
                }
            }
        }
    }



}
