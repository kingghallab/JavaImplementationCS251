import java.util.*;

public class ZakatCalculator {

    private static final double NISAB_THRESHOLD = 4000; // USD equivalent

    public static double calculateZakat(List<String> assetLines) {
        double totalZakatable = 0;

        for (String line : assetLines) {
            String[] parts = line.split(",");
            if (parts.length != 5) continue;

            String assetType = parts[0];
            double quantity = Double.parseDouble(parts[2]);
            double price = Double.parseDouble(parts[4]);
            double totalValue = quantity * price;

            if (isZakatableAsset(assetType)) {
                totalZakatable += totalValue;
            }
        }

        if (totalZakatable >= NISAB_THRESHOLD) {
            return totalZakatable * 0.025; // 2.5%
        }
        return 0.0;
    }

    private static boolean isZakatableAsset(String type) {
        return type.equalsIgnoreCase("Stocks") ||
               type.equalsIgnoreCase("Crypto") ||
               type.equalsIgnoreCase("Cash") ||
               type.equalsIgnoreCase("Gold");
    }
}