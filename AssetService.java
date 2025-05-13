import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * The `AssetService` class manages asset-related operations for a specific user.
 * It provides methods to add, update, remove, and retrieve assets.
 *
 * Features:
 * - Validates asset information before saving.
 * - Saves assets to a user-specific file.
 * - Updates or removes assets based on their ID.
 * - Retrieves all assets for display or further processing.
 */
public class AssetService {
    private String filePath;
    /**
     * Constructor that initializes the service for a specific user by creating
     * or loading their asset file.
     *
     * @param username The username of the user.
     */

    public AssetService(String username) {
        String directoryPath = "src/resources";
        ensureDirectoryExists(directoryPath);
        this.filePath = directoryPath + "/" + username + "_assets.txt";
        ensureFileExists();
    }
    private void ensureDirectoryExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }




    private void ensureFileExists() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating asset file: " + e.getMessage());
        }
    }

    /**
     * Validates the provided asset information.
     *
     * @param assetType     The type of the asset (e.g., Stocks, Real Estate).
     * @param assetName     The name of the asset.
     * @param assetQuantity The quantity of the asset.
     * @param purchaseDate  The purchase date of the asset.
     * @param purchasePrice The purchase price of the asset.
     * @return `true` if the information is valid, `false` otherwise.
     */
    public boolean validateAssetInformation(String assetType, String assetName, float assetQuantity, Date purchaseDate, float purchasePrice) {
        if (assetType == null || assetType.isBlank()) return false;
        if (assetName == null || assetName.isBlank()) return false;
        if (assetQuantity <= 0) return false;
        if (purchaseDate == null) return false;
        if (purchasePrice < 0) return false;
        return true;
    }

    public String displayError() {
        return "Error writing to the file.";
    }

    /**
     * Saves a new asset to the user's file.
     *
     * @param assetType     The type of the asset.
     * @param assetName     The name of the asset.
     * @param quantity      The quantity of the asset.
     * @param purchaseDate  The purchase date of the asset.
     * @param purchasePrice The purchase price of the asset.
     */

    public void saveAsset(String assetType, String assetName, float quantity, Date purchaseDate, float purchasePrice) {
        String line = String.join(",",
                assetType,
                assetName,
                String.valueOf(quantity),
                String.valueOf(purchaseDate.getTime()),
                String.valueOf(purchasePrice)
        );
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println(displayError());
        }
    }

    /**
     * Updates an existing asset in the user's file.
     *
     * @param assetId       The ID of the asset to update.
     * @param updatedAsset  The updated asset details as a string.
     */
    public void updateAsset(int assetId, String updatedAsset) {
        try {
            List<String> assetLines = Files.readAllLines(Paths.get(filePath));
            if (assetId < 0 || assetId >= assetLines.size()) {
                System.out.println(displayError());
                return;
            }
            assetLines.set(assetId, updatedAsset);
            Files.write(Paths.get(filePath), assetLines);
        } catch (IOException e) {
            System.out.println(displayError());
        }
    }
    /**
     * Removes an asset from the user's file.
     *
     * @param assetId The ID of the asset to remove.
     */

    public void removeAsset(int assetId) {
        try {
            List<String> assetLines = Files.readAllLines(Paths.get(filePath));
            if (assetId < 0 || assetId >= assetLines.size()) {
                System.out.println(displayError());
                return;
            }
            assetLines.remove(assetId);
            Files.write(Paths.get(filePath), assetLines);
        } catch (IOException e) {
            System.out.println(displayError());
        }
    }
    /**
     * Retrieves all assets for the user.
     *
     * @return A list of all assets as strings.
     */
    public List<String> getAllAssets() {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}