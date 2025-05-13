import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ZakatReportMain {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.ser"))) {
            List<User> users = (List<User>) ois.readObject();

            for (User user : users) {
                String username = user.getUsername();
                String assetFile = "src/resources/" + username + "_assets.txt";

                File file = new File(assetFile);
                if (!file.exists()) {
                    System.out.println("No asset file for user: " + username);
                    continue;
                }

                List<String> assetLines = Files.readAllLines(file.toPath());
                double zakatDue = ZakatCalculator.calculateZakat(assetLines);
                FinancialReportExporter.exportReport(username, assetLines, zakatDue);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error processing users: " + e.getMessage());
        }
    }
}