package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U1G6RefundAndReturnsController {

    @FXML
    private TableView<Refund> returnRequestTableView;
    @FXML
    private TableColumn<Refund, Integer> requestIDReturnRequestTableColumn;
    @FXML
    private TableColumn<Refund, String> ProductNamerefundRequestTablecolumn;
    @FXML
    private TableColumn<Refund, Integer> amountReturnRequestTableColumn;
    @FXML
    private TableColumn<Refund, String> complainsDescriptionReturnRequestTableColumn;
    @FXML
    private ComboBox<Integer> selectRequestIdRefundComboBox;

    private final ObservableList<Refund> refundList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up TableView columns
        requestIDReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("Request_ID"));
        ProductNamerefundRequestTablecolumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        amountReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        complainsDescriptionReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("problem_details"));

        // Load refunds from file
        loadRefundsFromFile();

        // Set data into TableView
        returnRequestTableView.setItems(refundList);

        // Populate ComboBox with Request IDs
        for (Refund refund : refundList) {
            selectRequestIdRefundComboBox.getItems().add(refund.getRequest_ID());
        }
    }

    private void loadRefundsFromFile() {
        refundList.clear();
        File file = new File("customerCustomer.bin");

        if (!file.exists() || file.length() == 0) {
            System.out.println("No refunds found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            while (true) {
                try {
                    Refund refund = (Refund) ois.readObject();
                    refundList.add(refund);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    @FXML
    public void confirmRefundButtonOnAction(ActionEvent actionEvent) {
        Integer selectedRequestId = selectRequestIdRefundComboBox.getValue();

        if (selectedRequestId == null) {
            System.out.println("No Request ID selected.");
            return;
        }

        Refund selectedRefund = null;

        for (Refund refund : refundList) {
            if (refund.getRequest_ID() == selectedRequestId) {
                selectedRefund = refund;
                break;
            }
        }

        if (selectedRefund != null) {
            refundList.remove(selectedRefund);
            updateRefundFile(refundList);
            System.out.println("Refund confirmed for Request ID: " + selectedRequestId);

            // Update ComboBox
            selectRequestIdRefundComboBox.getItems().remove(selectedRequestId);
        } else {
            System.out.println("Refund not found.");
        }
    }

    private void updateRefundFile(ObservableList<Refund> refundList) {
        File file = new File("customerCustomer.bin");

        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Refund refund : refundList) {
                oos.writeObject(refund);
            }
        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }
}
