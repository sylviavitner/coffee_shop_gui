public class HouseBlend  implements Beverage{
    private String size = "Small";

    public HouseBlend(String size) {
        this.size = size;
    }

    public String getCoffee() {
        return size + " House Blend";
    }

    public double cost() {
        return 1.80 * PricingCalculator.getSizeMultiplier(size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
