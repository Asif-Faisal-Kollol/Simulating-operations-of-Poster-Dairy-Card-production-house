package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class U2G1OrderManagementController {

    @FXML
    private TextField orderIdOrderManagementTextField;
    @FXML
    private TextField orderNameOrderManagementTextField1;
    @FXML
    private TextField addressOrderManagementTextField2;
    @FXML
    private TextField amountOrderManagementTextField21;
    @FXML
    private DatePicker orderDateOrderManagementDatePicker;
    @FXML
    private DatePicker deliveryDateOrderManagementDatePicker;

    @FXML
    private TableView<order> OrderManagementTableView;
    @FXML
    private TableColumn<order, Integer> orderIDOrderManagementTableColumn;
    @FXML
    private TableColumn<order, String> customerNameOrderManagementTableColumn;
    @FXML
    private TableColumn<order, LocalDate> OrderDateOrderManagementTableColumn;
    @FXML
    private TableColumn<order, String> addressOrderManagementTableColumn;
    @FXML
    private TableColumn<order, LocalDate> delivaryDateOrderManagementTableColumn;
    @FXML
    private TableColumn<order, Integer> orderedAmountOrderManagementTableColumn;

    private final ObservableList<order> orderList = FXCollections.observableArrayList();

    private static final String FILE_NAME = "order.bin";

    @FXML
    public void initialize() {
        // Initialize TableView columns
        orderIDOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        customerNameOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        OrderDateOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        addressOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        delivaryDateOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("delivery_date"));
        orderedAmountOrderManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("ordered_amount"));

        OrderManagementTableView.setItems(orderList);
        loadOrdersFromFile();
    }

    @FXML
    public void generateOdermanagementButtonOnAction(ActionEvent actionEvent) {
        try {
            int orderId = Integer.parseInt(orderIdOrderManagementTextField.getText().trim());
            String customerName = orderNameOrderManagementTextField1.getText().trim();
            LocalDate orderDate = orderDateOrderManagementDatePicker.getValue();
            String address = addressOrderManagementTextField2.getText().trim();
            LocalDate deliveryDate = deliveryDateOrderManagementDatePicker.getValue();
            int orderedAmount = Integer.parseInt(amountOrderManagementTextField21.getText().trim());

            if (customerName.isEmpty() || address.isEmpty() || orderDate == null || deliveryDate == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            order newOrder = new order(orderId, customerName, orderDate, address, deliveryDate, orderedAmount);

            saveOrderToFile(newOrder);
            orderList.add(newOrder); // Add new order to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Order added successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check Order ID and Amount fields.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    private void loadOrdersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No orders found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            orderList.clear();
            while (true) {
                try {
                    order ord = (order) ois.readObject();
                    orderList.add(ord);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Orders loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void saveOrderToFile(order ord) throws IOException {
        File file = new File(FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = file.exists() && file.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(ord);
        }
    }

    private void clearFields() {
        orderIdOrderManagementTextField.clear();
        orderNameOrderManagementTextField1.clear();
        addressOrderManagementTextField2.clear();
        amountOrderManagementTextField21.clear();
        orderDateOrderManagementDatePicker.setValue(null);
        deliveryDateOrderManagementDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
