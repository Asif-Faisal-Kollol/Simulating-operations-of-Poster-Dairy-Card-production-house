package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U2G6CustomerComplainsController {

    @FXML
    private TextField requestIDReturnRequestTextFied;
    @FXML
    private TextField productNameReturnRequestTextField;
    @FXML
    private TextField amountReturnRequestTextField;
    @FXML
    private TextField descriptionReturnRequestTextField;

    @FXML
    private TableView<Refund> returnRequestTableView;
    @FXML
    private TableColumn<Refund, Integer> requestIDReturnRequestTableColumn;
    @FXML
    private TableColumn<Refund, String> productNameReturnRequestTableColumn;
    @FXML
    private TableColumn<Refund, Integer> amountReturnRequestTableColumn;
    @FXML
    private TableColumn<Refund, String> complainsDescriptionReturnRequestTableColumn;

    private final ObservableList<Refund> refundList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        requestIDReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("Request_ID"));
        productNameReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        amountReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        complainsDescriptionReturnRequestTableColumn.setCellValueFactory(new PropertyValueFactory<>("problem_details"));

        returnRequestTableView.setItems(refundList);
        loadComplainsFromFile();
    }

    @FXML
    public void generateReturnRequestButtonOnAction() {
        try {
            int requestId = Integer.parseInt(requestIDReturnRequestTextFied.getText().trim());
            String productName = productNameReturnRequestTextField.getText().trim();
            int amount = Integer.parseInt(amountReturnRequestTextField.getText().trim());
            String problemDetails = descriptionReturnRequestTextField.getText().trim();

            if (productName.isEmpty() || problemDetails.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            Refund newRefund = new Refund(requestId, productName, amount, problemDetails);

            File file = new File("customerCustomer.bin");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            try {
                fos = new FileOutputStream(file, true);
                oos = file.exists() && file.length() > 0
                        ? new AppendableObjectOutputStream(fos)
                        : new ObjectOutputStream(fos);
                oos.writeObject(newRefund);
            } finally {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            }

            refundList.add(newRefund); // Add new complaint to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Complaint generated successfully.");
            clearFields();

            // Reload to ensure synchronization with file (optional redundancy)
            loadComplainsFromFile();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check ID and Amount fields.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    private void loadComplainsFromFile() {
        refundList.clear();
        File file = new File("customerCustomer.bin");

        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No complaints found.");
            return;
        }

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            while (true) {
                try {
                    Refund refund = (Refund) ois.readObject();
                    refundList.add(refund);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to close file stream.");
            }
        }
    }

    private void clearFields() {
        requestIDReturnRequestTextFied.clear();
        productNameReturnRequestTextField.clear();
        amountReturnRequestTextField.clear();
        descriptionReturnRequestTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Inner class to handle appending to ObjectOutputStream
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
