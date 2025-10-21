public class Mocha extends CondimentDecorator {
    public Mocha(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Mocha";
    }

    public double cost() {
        return beverage.cost() + 
        (0.75 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
