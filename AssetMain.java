import java.util.Scanner;

public class AssetMain {
    private final AssetService service;

    public AssetMain(AssetService service) {
        this.service = service;
    }

    public void startAssetManagement() {
        AssetManagmentPage page = new AssetManagmentPage(service);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Asset Management System ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Edit Asset");
            System.out.println("3. Remove Asset");
            System.out.println("4. Display Assets");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    page.clickAddAssets();
                    break;
                case "2":
                    page.clickEditAssets();
                    break;
                case "3":
                    page.clickRemoveAssets();
                    break;
                case "4":
                    page.clickDisplayAssets();
                    break;
                case "5":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}