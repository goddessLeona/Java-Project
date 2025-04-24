package Product;

public class Product {

    private Integer pruduct_id;
    private Integer manufacturer_id;
    private String name;
    private String description;
    private Double price;
    private Integer stock_Quantety;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
    }

    public Product(Integer pruduct_id, Integer manufacturer_id, String name, String description, Double price, Integer stock_Quantety) {
        this.pruduct_id = pruduct_id;
        this.manufacturer_id = manufacturer_id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock_Quantety = stock_Quantety;
    }

    public Integer getPruduct_id() {
        return pruduct_id;
    }

    public void setPruduct_id(Integer pruductid) {
        this.pruduct_id = pruductid;
    }

    public Integer getManufacturer_id() {
        return manufacturer_id;
    }

    public void setManufacturer_id(Integer manufacturer_id) {
        this.manufacturer_id = manufacturer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock_Quantety() {
        return stock_Quantety;
    }

    public void setStock_Quantety(Integer stock_Quantety) {
        this.stock_Quantety = stock_Quantety;
    }
}
