import java.io.*;
import java.nio.file.Files;
import java.util.*;
/**
 * The `FinancialReportExporter` class handles the generation and export of financial reports.
 * It provides methods to calculate income and export Zakat reports to a file.
 */
public class FinancialReportExporter {
    /**
     * Generates a financial report for a given asset file and report period.
     *
     * @param assetFilePath The path to the asset file containing asset details.
     * @param reportPeriod  The time period for which the financial report is generated (e.g., "Q1 2025").
     */

    public static void generateFinancialReport(String assetFilePath, String reportPeriod) {
        try {
            List<String> assetLines = Files.readAllLines(new File(assetFilePath).toPath());
            double income = 0;

            for (String line : assetLines) {
                String[] parts = line.split(",");
                if (parts.length != 5) continue;

                String assetType = parts[0];
                double quantity = Double.parseDouble(parts[2]);
                double price = Double.parseDouble(parts[4]);
                double totalValue = quantity * price;

                if (assetType.equalsIgnoreCase("Stocks") || assetType.equalsIgnoreCase("Crypto")) {
                    income += totalValue; // Example: Treat stocks and crypto as income
                }
            }

            System.out.println();
            System.out.println("Financial Report for " + reportPeriod + ":");
            System.out.println("Income: $" + String.format("%.2f", income));
            System.out.println("Net Profit: $" + String.format("%.2f", income) + " (Profit)");
        } catch (IOException e) {
            System.err.println("Error reading asset file: " + e.getMessage());
        }
    }
    /**
     * Exports a Zakat report to a file for the specified user.
     *
     * @param username    The username of the user for whom the Zakat report is generated.
     * @param assetLines  A list of asset details used to calculate Zakat.
     * @param zakatDue    The calculated Zakat amount due.
     */
    public static void exportZakatReport(String username, List<String> assetLines, double zakatDue) {
        String reportFilePath = "resources/" + username + "_zakat_report.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFilePath))) {
            writer.write("Zakat Report for User: " + username + "\n");
            writer.write("=====================================\n");
            writer.write("Assets:\n");

            for (String line : assetLines) {
                writer.write(line + "\n");
            }

            writer.write("\nZakat Due: $" + String.format("%.2f", zakatDue) + "\n");
            writer.write("=====================================\n");
            System.out.println("Zakat report generated successfully at: " + reportFilePath);
        } catch (IOException e) {
            System.err.println("Error writing Zakat report: " + e.getMessage());
        }
    }
}