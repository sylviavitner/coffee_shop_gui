public class DarkRoast implements Beverage {
    private String size = "Small";

    public DarkRoast(String size) {
        this.size = size;
    }

    public String getCoffee() {
        return size + " Dark Roast";
    }

    public double cost() {
        return 2.20 * PricingCalculator.getSizeMultiplier(size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
