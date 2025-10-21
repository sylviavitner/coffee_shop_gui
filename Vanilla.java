public class Vanilla extends CondimentDecorator {
    public Vanilla(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Vanilla";
    }

    public double cost() {
        return beverage.cost() + 
        (0.55 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
