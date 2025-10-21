public class Caramel extends CondimentDecorator {
    public Caramel(Beverage beverage) {
        super(beverage);
    }

    public String getCoffee() {
        return beverage.getCoffee() + ", Caramel";
    }

    public double cost() {
        return beverage.cost() + 
        (0.65 * PricingCalculator.getSizeMultiplier(getSize()));
    }
}
