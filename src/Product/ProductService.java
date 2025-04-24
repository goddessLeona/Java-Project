package Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {

    ProductRepository productRepository = new ProductRepository();

    //get all products
    public ArrayList<Product> getAllP() throws SQLException {
        return productRepository.getAllProducts();
    }

    //search product using name
    public ArrayList<Product> searchProduct(String name) throws SQLException {
        name = name.toLowerCase();
        return productRepository.searchProductName(name);
    }

    //search product by category name
    public ArrayList<Product> productsSearchByCatogoryName(String name) throws SQLException {
        name = name.toLowerCase();
        return productRepository.searchByCatogory(name);
    }

    //update price on a product
    public boolean updatePriceProduct(Product product) throws SQLException {
        return productRepository.uppdatePrice(product);
    }

    //update stock quantity
    public boolean updateStocksProduct(Product product) throws SQLException {
        return productRepository.uppdateStocks(product);
    }

    //Add a new product
    public boolean newProduct(Product product)throws SQLException{
        return productRepository.addProductNew(product);
    }

    //check if the name of the category exist in the database
    public boolean categoryExistCheck(String name) throws SQLException {
        name = name.toLowerCase();
        return productRepository.catogoryExist(name);
    }

    //get product by id
    public ArrayList<Product> productByid(int id) throws SQLException {
        return productRepository.getProductById(id);
    }

    //Check if product id exist
    public boolean CheckProductIdExist(int product_id) throws SQLException {
        return productRepository.checkProductId(product_id);
    }

    //Check if the manufactury_id exist in database
    public boolean checkManufacturyIdExist(int manufacturer_id) throws SQLException{
        return productRepository.checkManufacturyId(manufacturer_id);
    }

    // get the price on a product by product_id
    public double getPriceProduct(int product_id) throws SQLException{
        return productRepository.getProductPriceById(product_id);
    }

    // get the stock_Quantety using product id
    public int getQuantetyProductByID(int product_id)throws SQLException{
        return productRepository.getProductStocksById(product_id);
    }

}
