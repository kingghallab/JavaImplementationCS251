public class Asset {
    private String assetType;
    private String assetName;
    private int assetQuantity;
    private String purchaseDate;
    private double purchasePrice;

    public Asset(String assetType, String assetName, int assetQuantity, String purchaseDate, double purchasePrice) {
        this.assetType = assetType;
        this.assetName = assetName;
        this.assetQuantity = assetQuantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
    }

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