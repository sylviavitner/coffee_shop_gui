public class Soy extends CondimentDecorator {
    public Soy(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Soy";
    }

    public double cost() {
        return beverage.cost() + 
        (0.6 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
