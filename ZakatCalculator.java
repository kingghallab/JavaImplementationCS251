import java.util.*;
/**
 * The `ZakatCalculator` class is responsible for calculating Zakat based on a user's assets.
 * It determines whether assets are zakatable and calculates the Zakat due.
 */

public class ZakatCalculator {
    /**
     * Calculates the Zakat due based on the provided asset details.
     *
     * @param assetLines A list of asset details, where each line represents an asset in the format:
     *                   `<AssetType>,<Name>,<Quantity>,<ID>,<Price>`.
     * @return The calculated Zakat amount due. Returns 0.0 if the total zakatable assets are below the Nisab threshold.
     */

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

    /**
     * Determines if a given asset type is zakatable.
     *
     * @param type The type of the asset (e.g., "Stocks", "Crypto", "Cash", "Gold").
     * @return `true` if the asset type is zakatable, otherwise `false`.
     */

    private static boolean isZakatableAsset(String type) {
        return type.equalsIgnoreCase("Stocks") ||
                type.equalsIgnoreCase("Crypto") ||
                type.equalsIgnoreCase("Cash") ||
                type.equalsIgnoreCase("Gold");
    }
}