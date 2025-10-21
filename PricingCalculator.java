public class PricingCalculator {
    //beverage prices
    private static final double ESPRESSO_PRICE = 2.00;
    private static final double HOUSE_BLEND_PRICE = 1.80;
    private static final double DARK_ROAST_PRICE = 2.20;
    private static final double DECAF_PRICE = 2.10;

    //add on prices
    private static final double MILK_PRICE = 0.50;
    private static final double MOCHA_PRICE = 0.75;
    private static final double SOY_PRICE = 0.60;
    private static final double WHIP_PRICE = 0.45;
    private static final double CARAMEL_PRICE = 0.65;
    private static final double VANILLA_PRICE = 0.55;

    //size multiplier
    private static final double SMALL_MULTIPLIER = 1.00;
    private static final double MEDIUM_MULTIPLIER = 1.20;
    private static final double LARGE_MULTIPLIER = 1.40;

    public static double getBase(String base) {
        switch (base) {
            case "Espresso":
                return ESPRESSO_PRICE;
            case "House Blend":
                return HOUSE_BLEND_PRICE;
            case "Dark Roast":
                return DARK_ROAST_PRICE;
            case "Decaf":
                return DECAF_PRICE;
            default:
                return 0.0;
        }
    }

    public static double getCondiment(String condiment) {
        switch (condiment) {
            case "Milk":
                return MILK_PRICE;
            case "Mocha": 
                return MOCHA_PRICE;
            case "Soy":
                return SOY_PRICE;
            case "Whip":
                return WHIP_PRICE;
            case "Carmel":
                return CARAMEL_PRICE;
            case "Vanilla":
                return VANILLA_PRICE;
            default:
                return 0.0;
        }
    }

    public static double getSize(String size) {
        switch (size) {
            case "Small": 
                return SMALL_MULTIPLIER;
            case "Medium":
                return MEDIUM_MULTIPLIER;
            case "Large":
                return LARGE_MULTIPLIER;
            default:
                return 1.0;
        }
    }
    
    public static double baseTotal(String base, String size) {
        double basePrice = getBase(base);
        double sizeMultiplier = getSize(size);
        return basePrice * sizeMultiplier;
    }
    
    public static double condimentTotal(String condiemnt, String size) {
        double condimentTotal = getCondiment(condiemnt);
        double sizeMultiplier = getSize(size);
        return condimentTotal * sizeMultiplier;
    }
}
