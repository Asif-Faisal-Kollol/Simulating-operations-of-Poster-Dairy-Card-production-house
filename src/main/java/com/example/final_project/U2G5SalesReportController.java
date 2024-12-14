package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U2G5SalesReportController {

    @FXML
    private TableView<Sales_Report> salesReportTableView;
    @FXML
    private TableColumn<Sales_Report, Integer> productIDsalesReportTableColumn;
    @FXML
    private TableColumn<Sales_Report, String> productNameSalesReportTableColumn;
    @FXML
    private TableColumn<Sales_Report, Integer> quantitySoldSalesReportTableColumn;
    @FXML
    private TableColumn<Sales_Report, String> categorySalesReportTableColumn;
    @FXML
    private TableColumn<Sales_Report, Float> revenueSalesReportTableColumn;

    @FXML
    private TextField productIDSalesReportTextField;
    @FXML
    private TextField productNameSalesReportTextField;
    @FXML
    private TextField quantitySoldSalesReportTextField;
    @FXML
    private TextField revenueSalesReportTextField;
    @FXML
    private ComboBox<String> selectCategorySalesReportComboBox;

    private final ObservableList<Sales_Report> salesReportList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        productIDsalesReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        productNameSalesReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        quantitySoldSalesReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("quantity_sold"));
        categorySalesReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        revenueSalesReportTableColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));

        // Populate the ComboBox with categories (can be updated as needed)
        selectCategorySalesReportComboBox.setItems(FXCollections.observableArrayList("Electronics", "Clothing", "Food", "Books", "Other"));

        salesReportTableView.setItems(salesReportList);
        loadSalesReportsFromFile();
    }

    @FXML
    public void generateSalesReportButtonOnAction(ActionEvent actionEvent) {
        try {
            int productId = Integer.parseInt(productIDSalesReportTextField.getText().trim());
            String productName = productNameSalesReportTextField.getText().trim();
            int quantitySold = Integer.parseInt(quantitySoldSalesReportTextField.getText().trim());
            String category = selectCategorySalesReportComboBox.getValue();
            float revenue = Float.parseFloat(revenueSalesReportTextField.getText().trim());

            if (productName.isEmpty() || category == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            Sales_Report newReport = new Sales_Report(productId, productName, quantitySold, category, revenue);

            File file = new File("salesReport.bin");
            try (FileOutputStream fos = new FileOutputStream(file, true);
                 ObjectOutputStream oos = file.exists() && file.length() > 0
                         ? new AppendableObjectOutputStream(fos)
                         : new ObjectOutputStream(fos)) {
                oos.writeObject(newReport);
            }

            salesReportList.add(newReport); // Add new sales report to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Sales report generated successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check Product ID, Quantity, and Revenue fields.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    private void loadSalesReportsFromFile() {
        File file = new File("salesReport.bin");
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No sales reports found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            salesReportList.clear();
            while (true) {
                try {
                    Sales_Report report = (Sales_Report) ois.readObject();
                    salesReportList.add(report);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Sales reports loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void clearFields() {
        productIDSalesReportTextField.clear();
        productNameSalesReportTextField.clear();
        quantitySoldSalesReportTextField.clear();
        revenueSalesReportTextField.clear();
        selectCategorySalesReportComboBox.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}