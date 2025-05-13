import java.io.*;
import java.nio.file.*;
import java.util.*;
/**
 * The `ZakatReportMain` class is responsible for generating Zakat reports for users.
 * It reads the user's asset file, calculates the Zakat due, and exports the report.
 */

public class ZakatReportMain {
    /**
     * Generates a Zakat report for the specified user.
     *
     * @param username The username of the user for whom the Zakat report is generated.
     *                 The method looks for a file named username_assets.txt in the resources directory.
     */
    public static void generateZakatReport(String username) {
        String assetFile = "src/resources/" + username + "_assets.txt";

        File file = new File(assetFile);
        if (!file.exists()) {
            System.out.println("No asset file for user: " + username);
            return;
        }

        try {
            List<String> assetLines = Files.readAllLines(file.toPath());
            double zakatDue = ZakatCalculator.calculateZakat(assetLines);
            FinancialReportExporter.exportZakatReport(username, assetLines, zakatDue);
        } catch (IOException e) {
            System.out.println("Error processing Zakat report: " + e.getMessage());
        }
    }
}