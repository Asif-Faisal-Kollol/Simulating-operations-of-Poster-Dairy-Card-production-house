package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class U2G8SalesInvoiceController {

    @FXML
    private TextField invoiceIDInvoiceTextField;
    @FXML
    private TextField customerNameInvoiceTextField;
    @FXML
    private TextField orderDescriptionInvoiceTextField;
    @FXML
    private TextField totalAmountInvoiceTextField;
    @FXML
    private DatePicker dateInvoiceDatePicker;

    @FXML
    private TableView<Invoice> invoiceTableView;
    @FXML
    private TableColumn<Invoice, Integer> invoiceIdInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, String> customerNameInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, String> orderDescriptionInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, Integer> totalAmountInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> dueDateInvoiceTableColumn;

    private final ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        invoiceIdInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("invoice_id"));
        customerNameInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        orderDescriptionInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        totalAmountInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dueDateInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));

        invoiceTableView.setItems(invoiceList);
        loadInvoicesFromService();
    }

    @FXML
    public void createInvoiceButtonOnAction() {
        try {
            int invoiceId = Integer.parseInt(invoiceIDInvoiceTextField.getText().trim());
            String customerName = customerNameInvoiceTextField.getText().trim();
            String orderDescription = orderDescriptionInvoiceTextField.getText().trim();
            int totalAmount = Integer.parseInt(totalAmountInvoiceTextField.getText().trim());
            LocalDate dueDate = dateInvoiceDatePicker.getValue();

            if (customerName.isEmpty() || orderDescription.isEmpty() || dueDate == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            Invoice newInvoice = new Invoice(invoiceId, customerName, orderDescription, totalAmount, dueDate);
            InvoiceService.saveInvoice(newInvoice); // Use the service to save the invoice
            invoiceList.add(newInvoice); // Add new invoice to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Invoice created successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check ID and Amount fields.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred.");
        }
    }

    @FXML
    public void sentInvoiceButtonOnAction() {
        loadInvoicesFromService();
    }

    private void loadInvoicesFromService() {
        ObservableList<Invoice> invoices = InvoiceService.loadInvoices(); // Use the service to load invoices
        if (invoices.isEmpty()) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No invoices found.");
        } else {
            invoiceList.setAll(invoices);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Invoices loaded successfully.");
        }
    }

    private void clearFields() {
        invoiceIDInvoiceTextField.clear();
        customerNameInvoiceTextField.clear();
        orderDescriptionInvoiceTextField.clear();
        totalAmountInvoiceTextField.clear();
        dateInvoiceDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
