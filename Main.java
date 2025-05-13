import java.util.Scanner;
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

public class Main {
    /**
     * The main method initializes the application and handles user interaction.
     * It provides a menu-driven interface for user authentication and navigation
     * to asset management features , zakat calculation and financial reports .
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (authService.signup(name, email, username, password)) {
                        System.out.println("Signup successful! Please login.");
                    } else {
                        System.out.println("Signup failed. Username may already exist or information is invalid.");
                    }
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    if (authService.login(loginUser, loginPass)) {
                        System.out.println("Login successful! Welcome, " + loginUser + ".");
                        AssetService assetService = new AssetService(loginUser);
                        AssetMain assetMain = new AssetMain(assetService);

                        Scanner scanner2 = new Scanner(System.in);
                        while (true) {
                            System.out.println("\nChoose an option:");
                            System.out.println("1. Manage Assets");
                            System.out.println("2. Generate Financial Reports");
                            System.out.println("3. Calculate Zakat");
                            System.out.println("4. Logout");
                            System.out.print("Enter your choice: ");

                            String userChoice = scanner2.nextLine();
                            switch (userChoice) {
                                case "1":
                                    assetMain.startAssetManagement();
                                    break;
                                case "2":
                                    FinancialReportExporter.generateFinancialReport("src/resources/karen_assets.txt", "Q1 2025");
                                    break;
                                case "3":
                                    ZakatReportMain.generateZakatReport(loginUser);
                                    break;
                                case "4":
                                    System.out.println("Logging out. Goodbye!");
                                    return;
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
