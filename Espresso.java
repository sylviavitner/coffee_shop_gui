public class Espresso implements Beverage {
    private String size = "Small";

    public Espresso(String size) {
        this.size = size;
    }

    public String getCoffee() {
        return size + " Espresso";
    }

    public double cost() {
        return 2.00 * PricingCalculator.getSizeMultiplier(size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}