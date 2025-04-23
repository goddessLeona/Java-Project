package Order;

public class DiscountPercentage implements Discount3000  {

    private final double percentage;

    public DiscountPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double discount3000(double totalAmount) {
        return totalAmount * (1 - percentage/100);
    }
}
