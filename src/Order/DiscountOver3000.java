package Order;

public class DiscountOver3000 implements Discount  {

    private final double percentage = 0.10;

    @Override
    public double applyDiscount(double total) {
        if (total > 3000){
            System.out.println("you earned a discount of 10%, buying over 3000 ");
            return total * (1-percentage);
        }
        return total;
    }

}
