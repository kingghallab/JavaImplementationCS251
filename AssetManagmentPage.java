import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AssetManagmentPage {
    private final AssetService service;
    private final String[] assetTypes = {"Stocks", "Real Estate", "Crypto", "Bonds", "Commodities"};

    public AssetManagmentPage(AssetService service) {
        this.service = service;
    }

    public void clickAddAssets() {
        Asset newAsset = displayInputFields();
        service.saveAsset(
                newAsset.getAssetType(),
                newAsset.getAssetName(),
                newAsset.getAssetQuantity(),
                new Date(),
                (float) newAsset.getPurchasePrice()
        );
        System.out.println("Asset added successfully!");
    }

    public void clickEditAssets() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Asset ID to edit: ");
        int assetId;
        while (true) {
            try {
                assetId = Integer.parseInt(scanner.nextLine());
                if (assetId >= 0) break;
                System.out.println("Asset ID must be a non-negative integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        List<String> assets = service.getAllAssets();
        if (assetId < 0 || assetId >= assets.size()) {
            System.out.println("Invalid Asset ID. Please try again.");
            return;
        }

        System.out.println("Editing Asset: " + assets.get(assetId));
        String[] assetFields = assets.get(assetId).split(",");

        while (true) {
            System.out.println("\n--- Edit Asset Menu ---");
            System.out.println("1. Edit Asset Type");
            System.out.println("2. Edit Asset Name");
            System.out.println("3. Edit Asset Quantity");
            System.out.println("4. Edit Asset Purchase Date");
            System.out.println("5. Edit Asset Price");
            System.out.println("6. Save and Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    while (true) {
                        System.out.println("Choose Asset Type:");
                        for (int i = 0; i < assetTypes.length; i++) {
                            System.out.println((i + 1) + ". " + assetTypes[i]);
                        }
                        System.out.print("Enter choice (1-" + assetTypes.length + "): ");
                        try {
                            int typeChoice = Integer.parseInt(scanner.nextLine());
                            if (typeChoice >= 1 && typeChoice <= assetTypes.length) {
                                assetFields[0] = assetTypes[typeChoice - 1];
                                break;
                            }
                            System.out.println("Invalid choice. Please select a valid option.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }
                    break;
                case "2":
                    while (true) {
                        System.out.print("Enter new Asset Name (non-empty): ");
                        String assetName = scanner.nextLine().trim();
                        if (!assetName.isEmpty()) {
                            assetFields[1] = assetName;
                            break;
                        }
                        System.out.println("Asset Name cannot be empty.");
                    }
                    break;
                case "3":
                    while (true) {
                        System.out.print("Enter new Asset Quantity (positive integer): ");
                        try {
                            int assetQuantity = Integer.parseInt(scanner.nextLine());
                            if (assetQuantity > 0) {
                                assetFields[2] = String.valueOf(assetQuantity);
                                break;
                            }
                            System.out.println("Asset Quantity must be a positive integer.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                    }
                    break;
                case "4":
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    while (true) {
                        System.out.print("Enter new Purchase Date (yyyy-MM-dd): ");
                        try {
                            String dateInput = scanner.nextLine();
                            dateFormat.parse(dateInput);
                            assetFields[3] = dateInput;
                            break;
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                        }
                    }
                    break;
                case "5":
                    while (true) {
                        System.out.print("Enter new Asset Price (non-negative number): ");
                        try {
                            double assetPrice = Double.parseDouble(scanner.nextLine());
                            if (assetPrice >= 0) {
                                assetFields[4] = String.valueOf(assetPrice);
                                break;
                            }
                            System.out.println("Asset Price must be a non-negative number.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    }
                    break;
                case "6":
                    String updatedAsset = String.join(",", assetFields);
                    service.updateAsset(assetId, updatedAsset);
                    System.out.println("Asset updated successfully!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void clickRemoveAssets() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Asset ID to remove: ");
        int assetId = Integer.parseInt(scanner.nextLine());
        service.removeAsset(assetId);
    }

    public void clickDisplayAssets() {
        List<String> assets = service.getAllAssets();
        if (assets.isEmpty()) {
            System.out.println("No assets found.");
        } else {
            System.out.println("\n--- Current Assets ---");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (int i = 0; i < assets.size(); i++) {
                String[] assetFields = assets.get(i).split(",");
                String formattedDate = dateFormat.format(new Date(Long.parseLong(assetFields[3])));
                System.out.println(i + ": " + assetFields[0] + ", " + assetFields[1] + ", " + assetFields[2] + ", " + formattedDate + ", " + assetFields[4]);
            }
        }
    }

    private Asset displayInputFields() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);


        String assetType;
        while (true) {
            System.out.println("Choose Asset Type:");
            for (int i = 0; i < assetTypes.length; i++) {
                System.out.println((i + 1) + ". " + assetTypes[i]);
            }
            System.out.print("Enter choice (1-" + assetTypes.length + "): ");
            try {
                int typeChoice = Integer.parseInt(scanner.nextLine());
                if (typeChoice >= 1 && typeChoice <= assetTypes.length) {
                    assetType = assetTypes[typeChoice - 1];
                    break;
                }
                System.out.println("Invalid choice. Please select a valid option.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }


        String assetName;
        while (true) {
            System.out.print("Input Asset Name (non-empty): ");
            assetName = scanner.nextLine().trim();
            if (!assetName.isEmpty()) break;
            System.out.println("Asset Name cannot be empty.");
        }


        int assetQuantity;
        while (true) {
            System.out.print("Input Asset Quantity (positive integer): ");
            try {
                assetQuantity = Integer.parseInt(scanner.nextLine());
                if (assetQuantity > 0) break;
                System.out.println("Asset Quantity must be a positive integer.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }


        Date purchaseDate;
        while (true) {
            System.out.print("Input Asset Purchase Date (yyyy-MM-dd): ");
            try {
                String dateInput = scanner.nextLine();
                purchaseDate = dateFormat.parse(dateInput);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
            }
        }


        double assetPrice;
        while (true) {
            System.out.print("Input Asset Price (non-negative number): ");
            try {
                assetPrice = Double.parseDouble(scanner.nextLine());
                if (assetPrice >= 0) break;
                System.out.println("Asset Price must be a non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return new Asset(assetType, assetName, assetQuantity, dateFormat.format(purchaseDate), assetPrice);
    }
}