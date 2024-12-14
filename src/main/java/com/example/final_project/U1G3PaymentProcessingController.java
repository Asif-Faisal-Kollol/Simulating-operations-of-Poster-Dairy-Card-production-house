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
import java.util.ArrayList;
import java.util.List;

public class U1G3PaymentProcessingController {

    @FXML
    private TableColumn<Payment, Integer> userIdPaymentTableCloumn;
    @FXML
    private TableColumn<Payment, String> userNamePaymentTableCloumn;
    @FXML
    private TableColumn<Payment, String> userTypePaymentTableCloumn;
    @FXML
    private TableColumn<Payment, Float> amountPaymentTableCloumn;

    @FXML
    private TableView<Payment> duePaymentTableView;

    @FXML
    private ComboBox<Integer> userIdPaymentComboBox;

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

        // Load payments from the file
        loadPaymentsFromFile();

        // Populate ComboBox with user IDs
        refreshComboBox();
    }

    private void loadPaymentsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No payments found.");
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
                    break; // End of file reached
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void makePaymentButtonOnAction(ActionEvent actionEvent) {
        Integer selectedUserId = userIdPaymentComboBox.getValue();

        if (selectedUserId != null) {
            Payment selectedPayment = null;

            // Find the payment for the selected user
            for (Payment payment : paymentList) {
                if (payment.getUser_id() == selectedUserId) {
                    selectedPayment = payment;
                    break;
                }
            }

            if (selectedPayment != null) {
                // Remove the payment from the list
                paymentList.remove(selectedPayment);

                // Update the file
                savePaymentsToFile();

                // Refresh the ComboBox
                refreshComboBox();

                System.out.println("Payment processed and removed for user: " + selectedPayment.getUser_name());
            } else {
                System.out.println("Payment not found for the selected user ID.");
            }
        } else {
            System.out.println("No user ID selected.");
        }
    }

    private void savePaymentsToFile() {
        File file = new File(FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            for (Payment payment : paymentList) {
                oos.writeObject(payment);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void refreshComboBox() {
        List<Integer> userIds = new ArrayList<>();
        for (Payment payment : paymentList) {
            userIds.add(payment.getUser_id());
        }
        userIdPaymentComboBox.setItems(FXCollections.observableArrayList(userIds));
    }
}
