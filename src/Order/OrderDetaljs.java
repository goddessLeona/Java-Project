package Order;

import Customer.Customer;
import Orders_products.Orders_products;
import Product.Product;

public class OrderDetaljs {

    private Order order;
    private Product product;
    private Customer customer;
    private Orders_products orders_products;

    public OrderDetaljs(Orders_products orders_products, Customer customer,
                        Product product, Order order) {
        this.orders_products = orders_products;
        this.customer = customer;
        this.product = product;
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Orders_products getOrders_products() {
        return orders_products;
    }

    public void setOrders_products(Orders_products orders_products) {
        this.orders_products = orders_products;
    }

    @Override
    public String toString() {
        return "OrderDetaljs{" +
                "order=" + order +
                ", product=" + product +
                ", customer=" + customer +
                ", orders_products=" + orders_products +
                '}';
    }
}
