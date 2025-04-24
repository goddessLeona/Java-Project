package Order;

import java.sql.Timestamp;

public class Order {

    private int order_id;
    private int customer_id;
    private Timestamp order_date;

    public Order() {
    }


    public Order(int customer_id, Timestamp order_date) {
        this.customer_id = customer_id;
        this.order_date = order_date;
    }

    public Order(Timestamp order_date) {
        this.order_date = order_date;
    }

    public Order(Timestamp order_date, int customer_id, int order_id) {
        this.order_date = order_date;
        this.customer_id = customer_id;
        this.order_id = order_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer_id=" + customer_id +
                ", order_date=" + order_date +
                '}';
    }
}
