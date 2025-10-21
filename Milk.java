public class Milk extends CondimentDecorator {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Milk";
    }

    public double cost() {
        return beverage.cost() + 
        (0.5 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
