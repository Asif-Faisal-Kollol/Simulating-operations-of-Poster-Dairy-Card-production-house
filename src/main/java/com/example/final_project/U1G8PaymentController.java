package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U1G8PaymentController {

    @FXML
    private TextField userNamePaymentTextField;
    @FXML
    private TextField userIdPaymentTextField;
    @FXML
    private ComboBox<String> selectUserTypePaymentComboBox;
    @FXML
    private TextField userAmountPaymentTextField;

    @FXML
    private TableView<Payment> duePaymentTableView;
    @FXML
    private TableColumn<Payment, Integer> userIdPaymentTableCloumn;
    @FXML
    private TableColumn<Payment, String> userNamePaymentTableCloumn;
    @FXML
    private TableColumn<Payment, String> userTypePaymentTableCloumn;
    @FXML
    private TableColumn<Payment, Float> amountPaymentTableCloumn;

    private final ObservableList<Payment> paymentList = FXCollections.observableArrayList();
    private static final String FILE_NAME = "payment.bin";

    @FXML
    public void initialize() {
        // Initialize TableView columns
        userIdPaymentTableCloumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        userNamePaymentTableCloumn.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        userTypePaymentTableCloumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        amountPaymentTableCloumn.setCellValueFactory(new PropertyValueFactory<>("amount"));

        duePaymentTableView.setItems(paymentList);

        // Initialize ComboBox options
        selectUserTypePaymentComboBox.setItems(FXCollections.observableArrayList("Admin", "Customer", "Staff"));

        // Load payments from the file
        loadPaymentsFromFile();
    }

    @FXML
    public void createPaymentButtonOnAction(ActionEvent actionEvent) {
        try {
            int userId = Integer.parseInt(userIdPaymentTextField.getText().trim());
            String userName = userNamePaymentTextField.getText().trim();
            String userType = selectUserTypePaymentComboBox.getValue();
            float amount = Float.parseFloat(userAmountPaymentTextField.getText().trim());

            if (userName.isEmpty() || userType == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields and select a user type.");
                return;
            }

            Payment newPayment = new Payment(userId, userName, userType, amount);

            savePaymentToFile(newPayment);
            paymentList.add(newPayment); // Add to TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Payment created successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check user ID or amount.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save payment data.");
        }
    }

    private void loadPaymentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No payments found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            paymentList.clear();
            while (true) {
                try {
                    Payment payment = (Payment) ois.readObject();
                    paymentList.add(payment);
                } catch (EOFException e) {
                    break;
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load payments from file.");
        }
    }

    private void savePaymentToFile(Payment payment) throws IOException {
        File file = new File(FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = file.exists() && file.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(payment);
        }
    }

    private void clearFields() {
        userIdPaymentTextField.clear();
        userNamePaymentTextField.clear();
        userAmountPaymentTextField.clear();
        selectUserTypePaymentComboBox.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
