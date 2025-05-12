import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AssetService {
    private String filePath;

    public AssetService(String username) {
        this.filePath = "src/resources/" + username + "_assets.txt";
        ensureFileExists();
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

    public List<String> getAllAssets() {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}