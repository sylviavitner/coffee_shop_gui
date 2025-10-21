public class Decaf implements Beverage{
    private String size = "Small";

    public Decaf(String size) {
        this.size = size;
    }

    public String getCoffee() {
        return size + " Decaf";
    }

    public double cost() {
        return 2.10 * PricingCalculator.getSizeMultiplier(size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
