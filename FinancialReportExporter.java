import java.io.*;
import java.util.*;

public class FinancialReportExporter {

    public static void exportReport(String username, List<String> assets, double zakatAmount) {
        String fileName = "zakat_report_" + username + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Zakat Financial Report for User: " + username + "\n\n");
            writer.write("Assets:\n");
            for (String asset : assets) {
                writer.write(asset + "\n");
            }
            writer.write("\nTotal Zakat Due: $" + String.format("%.2f", zakatAmount) + "\n");
            System.out.println("Report generated for user: " + username);
        } catch (IOException e) {
            System.out.println("Error writing report for " + username + ": " + e.getMessage());
        }
    }
}