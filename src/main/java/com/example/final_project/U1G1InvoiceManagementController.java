package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class U1G1InvoiceManagementController
{
    @FXML
    private TableColumn<Invoice, String> customerNameInvoiceTableColumn;
    @FXML
    private ComboBox<String> customerNameInvoiceComboBox;
    @FXML
    private TableColumn<Invoice, Integer> invoiceIdInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, Integer> totalAmountInvoiceTableColumn;
    @FXML
    private TextField givenInvoiceTextField;
    @FXML
    private DatePicker extendedDateInvoiceDatePicker;
    @FXML
    private TableView<Invoice> invoiceTableView;
    @FXML
    private TableColumn<Invoice, String> orderDescriptionInvoiceTableColumn;
    @FXML
    private TableColumn<Invoice, LocalDate> dueDateInvoiceTableColumn;

    private final ObservableList<Invoice> invoiceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        invoiceIdInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("invoice_id"));
        customerNameInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        orderDescriptionInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_description"));
        totalAmountInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dueDateInvoiceTableColumn.setCellValueFactory(new PropertyValueFactory<>("invoiceDate"));

        invoiceList.addAll(InvoiceService.loadInvoices());
        invoiceTableView.setItems(invoiceList);

        customerNameInvoiceComboBox.setItems(FXCollections.observableArrayList(
                invoiceList.stream()
                        .map(Invoice::getCustomer_name)
                        .distinct()
                        .collect(Collectors.toList())
        ));

        customerNameInvoiceComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                invoiceTableView.setItems(invoiceList.filtered(invoice -> invoice.getCustomer_name().equals(newVal)));
            } else {
                invoiceTableView.setItems(invoiceList);
            }
        });
    }

    @FXML
    public void extendDateRemainderInvoiceButtonOnAction(ActionEvent actionEvent) {
        Invoice selectedInvoice = invoiceTableView.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No invoice selected.");
            return;
        }

        LocalDate newDate = extendedDateInvoiceDatePicker.getValue();
        if (newDate == null || !newDate.isAfter(selectedInvoice.getInvoiceDate())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid date. Please select a future date.");
            return;
        }

        selectedInvoice.setInvoiceDate(newDate);
        InvoiceService.saveInvoice(selectedInvoice);
        invoiceTableView.refresh();
        showAlert(Alert.AlertType.INFORMATION, "Success", "Due date extended successfully.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}