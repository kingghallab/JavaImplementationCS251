/**
 * The `Asset` class represents an individual asset owned by a user.
 * It encapsulates details such as the asset type, name, quantity, purchase date, and price.
 *
 * Features:
 * - Stores essential information about an asset.
 * - Provides getter methods to access asset details.
 */


public class Asset {
    private String assetType;
    private String assetName;
    private int assetQuantity;
    private String purchaseDate;
    private double purchasePrice;
    /**
     * Constructor that initializes an asset with the given details.
     *
     * @param assetType     The type of the asset.
     * @param assetName     The name of the asset.
     * @param assetQuantity The quantity of the asset.
     * @param purchaseDate  The purchase date of the asset.
     * @param purchasePrice The purchase price of the asset.
     */
    public Asset(String assetType, String assetName, int assetQuantity, String purchaseDate, double purchasePrice) {
        this.assetType = assetType;
        this.assetName = assetName;
        this.assetQuantity = assetQuantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }
    /**
     * getter methods for all Assets attributes
     * */
    public String getAssetType() {
        return assetType;
    }

    public String getAssetName() {
        return assetName;
    }

    public int getAssetQuantity() {
        return assetQuantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }
}