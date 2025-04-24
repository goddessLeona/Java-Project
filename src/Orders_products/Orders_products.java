package Orders_products;

public class Orders_products {

    private int order_product_id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double unit_price;

    public Orders_products() {
    }

    public Orders_products(int quantity, double unit_price) {
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public Orders_products(int order_product_id, int order_id, int product_id, int quantity, double unit_price) {
        this.order_product_id = order_product_id;
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(int order_product_id) {
        this.order_product_id = order_product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "Orders_products{" +
                "order_product_id=" + order_product_id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                '}';
    }
}
