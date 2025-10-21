public class PricingCalculator {
    private static final double SMALL_MULTIPLIER = 1.00;
    private static final double MEDIUM_MULTIPLIER = 1.20;
    private static final double LARGE_MULTIPLIER = 1.40;

    public static double getSizeMultiplier(String size) {
        switch (size) {
            case "Small":
                return SMALL_MULTIPLIER;
            case "Medium":
                return MEDIUM_MULTIPLIER;
            case "Large":
                return LARGE_MULTIPLIER;
            default:    
                return 1.00;
        }
    }
}
