package Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {

    OrderRepository orderService = new OrderRepository();

    // get all order
    public ArrayList<Order> ordersGetAll() throws SQLException {
        return orderService.getAllOrder();
    }

    // order history for a customer
    public ArrayList<OrderDetaljs> orderHistoryCustomer(int customer_id) throws SQLException {
        return orderService.orderHistory(customer_id);
    }

    // order history for a customer
    public ArrayList<OrderDetaljs> orderHistoryCustomer(String email) throws SQLException {
        return orderService.orderHistory(email);
    }

    // make order
    public int makeOrder(int customerId) throws SQLException {
        return orderService.orderNew(customerId);
    }

}
