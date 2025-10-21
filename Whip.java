public class Whip extends CondimentDecorator{
    public Whip(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Whip";
    }

    public double cost() {
        return beverage.cost() + 
        (0.45 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
