public abstract class CondimentDecorator implements Beverage{
    public Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getSize() {
        return beverage.getSize();
    }

    public void setSize(String size) {
        beverage.setSize(size);
    }

    public abstract String getCoffee();
}
