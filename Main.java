import java.util.Scanner;

public class Main {
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
                        assetMain.startAssetManagement();
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
