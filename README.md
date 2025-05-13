# Asset Management & Zakat Calculation System

## Overview
This Java project is a console-based application for managing user assets, generating financial reports, and calculating Zakat (an Islamic financial obligation). It supports user authentication, asset management, financial reporting, and Zakat report generation.

## Features
- **User Authentication**: Sign up and login functionality for multiple users.
- **Asset Management**: Add, edit, remove, and display various asset types (Stocks, Real Estate, Crypto, Bonds, Commodities).
- **Financial Reports**: Generate income and profit reports based on user assets.
- **Zakat Calculation**: Calculate and export Zakat reports based on user assets.
- **Persistent Storage**: User and asset data are stored in files for persistence between sessions.

## Directory Structure
- `JavaImplementationCS251/` - Main source code and generated documentation (Javadoc HTML files).
- `resources/` - Stores user asset files and Zakat reports (e.g., `joe_assets.txt`, `joe_zakat_report.txt`).
- `users.ser` - Serialized user data.

## How to Run
1. **Compile the project** (if not already compiled):
   - Use your IDE or run `javac *.java` in the `JavaImplementationCS251` directory.
2. **Run the application**:
   - `java Main`
3. **Follow the prompts** to sign up, log in, manage assets, generate reports, and calculate Zakat.

## Main Classes
- `Main.java`: Entry point, handles user interaction and menu navigation.
- `AuthenticationService.java`: Manages user sign up and login.
- `AssetService.java`: Handles asset CRUD operations for users.
- `FinancialReportExporter.java`: Generates financial and Zakat reports.
- `ZakatCalculator.java`: Calculates Zakat based on asset data.
- `User.java`: Represents a user.
- `Asset.java`: Represents an asset.

## Notes
- Asset and Zakat report files are stored in the `resources/` directory.
- Make sure the `resources/` directory exists before running the application.
- The application is designed for educational purposes and may require further enhancements for production use.

