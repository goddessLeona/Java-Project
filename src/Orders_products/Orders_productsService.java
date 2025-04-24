package Orders_products;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Orders_productsService {

    Order_productsRepository orderProductsService = new Order_productsRepository();

    public ArrayList<Orders_products> getAllOrdersProducts() throws SQLException {
        return orderProductsService.AllOrdersProducts();
    }

    public boolean orderMultipleProducts(int order_id, List<Orders_products> productsList) throws SQLException{
        return orderProductsService.orderManyProducts(order_id,productsList);
    }

}
