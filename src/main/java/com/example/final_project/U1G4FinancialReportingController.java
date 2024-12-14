package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class U1G4FinancialReportingController {

    @FXML
    private TextField accountNumFinancialReportTextField;
    @FXML
    private TextField transactionAmountFinancialReportTextField;
    @FXML
    private DatePicker dateFinancialReportDatePicker;

    @FXML
    private TableView<Financial_Report> financialReportTableView;
    @FXML
    private TableColumn<Financial_Report, Integer> accountfinancialReportTableColumn;
    @FXML
    private TableColumn<Financial_Report, LocalDate> datefinancialReportTableColumn;
    @FXML
    private TableColumn<Financial_Report, Float> amountfinancialReportTableColumn;

    private final ObservableList<Financial_Report> financialReports = FXCollections.observableArrayList();

    private static final String FILE_NAME = "financialReport.bin";

    @FXML
    public void initialize() {
        // Initialize TableView columns
        accountfinancialReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("account_Number"));
        datefinancialReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("transaction_Date"));
        amountfinancialReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("transaction_Amount"));

        financialReportTableView.setItems(financialReports);
        loadFinancialReportsFromFile();
    }

    @FXML
    public void generateandSendfinancialReportButtonOnAction(ActionEvent actionEvent) {
        try {
            int accountNumber = Integer.parseInt(accountNumFinancialReportTextField.getText().trim());
            float transactionAmount = Float.parseFloat(transactionAmountFinancialReportTextField.getText().trim());
            LocalDate transactionDate = dateFinancialReportDatePicker.getValue();

            if (transactionDate == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Transaction date must be selected.");
                return;
            }

            Financial_Report newReport = new Financial_Report(accountNumber, transactionAmount, transactionDate);

            saveFinancialReportToFile(newReport);
            financialReports.add(newReport); // Add new report to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Financial report added successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check account number and transaction amount.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    private void loadFinancialReportsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No financial reports found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            financialReports.clear();
            while (true) {
                try {
                    Financial_Report report = (Financial_Report) ois.readObject();
                    financialReports.add(report);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Financial reports loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void saveFinancialReportToFile(Financial_Report report) throws IOException {
        File file = new File(FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = file.exists() && file.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(report);
        }
    }

    private void clearFields() {
        accountNumFinancialReportTextField.clear();
        transactionAmountFinancialReportTextField.clear();
        dateFinancialReportDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
