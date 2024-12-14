package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;

public class U2G2InventoryManagementController {
    @FXML
    private TextField amountInventoryManagementTextfield;
    @FXML
    private TableView<Inventory> InventoryManagementTableView;
    @FXML
    private TableColumn<Inventory, Integer> availableAmountInventoryManagementTableColumn;
    @FXML
    private TableColumn<Inventory, Integer> orderedAmountInventoryManagementTableColumn;
    @FXML
    private TableColumn<Inventory, LocalDate> dateInventoryManagementTableColumn;
    @FXML
    private TableColumn<Inventory, Integer> productIDInventoryManagementTableColumn;
    @FXML
    private TableColumn<Inventory, Integer> orderIDInventoryManagementTableColumn;
    @FXML
    private TableColumn<Inventory, Integer> remainingInventoryManagementTableColumn1;
    @FXML
    private ComboBox<Integer> productIdInventoryManagementComboBox;

    private final ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
    private static final String FILE_NAME = "inventory.bin";


    @FXML
    public void initialize() {
        // Bind table columns to model properties
        productIDInventoryManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        orderIDInventoryManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        availableAmountInventoryManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("available_amount"));
        orderedAmountInventoryManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("ordered_amount"));
        dateInventoryManagementTableColumn.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        remainingInventoryManagementTableColumn1.setCellValueFactory(new PropertyValueFactory<>("deliveryAfterAmount"));
        InventoryManagementTableView.setItems(inventoryList);

        // Load data from file or create dummy data
        loadInventoryData();

        // Populate ComboBox with unique product IDs
        populateProductIDComboBox();
    }

    private void loadInventoryData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                inventoryList.clear();
                while (true) {
                    try {
                        Inventory inventory = (Inventory) ois.readObject();
                        inventoryList.add(inventory);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load inventory data.");
            }
        } else {
            // Load dummy data if no file exists
            inventoryList.addAll(
                    new Inventory(1, 101, 50, 20, LocalDate.now(), 30),
                    new Inventory(2, 102, 100, 50, LocalDate.now(), 50),
                    new Inventory(3, 103, 75, 25, LocalDate.now(), 50)
            );
        }
    }

    private void populateProductIDComboBox() {
        ObservableList<Integer> productIDs = FXCollections.observableArrayList();
        for (Inventory inventory : inventoryList) {
            if (!productIDs.contains(inventory.getProduct_id())) {
                productIDs.add(inventory.getProduct_id());
            }
        }
        productIdInventoryManagementComboBox.setItems(productIDs);
    }

    @FXML
    public void requestProductAmountInventoryManagementButtonOnAction(ActionEvent actionEvent) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Inventory inventory : inventoryList) {
                oos.writeObject(inventory);
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory data saved.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save inventory data.");
        }
    }

    @FXML
    public void confirmOrderAmountInventoryManagementButtonOnAction(ActionEvent actionEvent) {
        try {
            int productId = productIdInventoryManagementComboBox.getValue();
            int orderedAmount = Integer.parseInt(amountInventoryManagementTextfield.getText().trim());

            Inventory selectedInventory = InventoryManagementTableView.getSelectionModel().getSelectedItem();
            if (selectedInventory != null && selectedInventory.getProduct_id() == productId) {
                selectedInventory.setOrdered_amount(orderedAmount);
                selectedInventory.setDeliveryAfterAmount(selectedInventory.calculateRemainingAmount());

                InventoryManagementTableView.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Inventory updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No matching inventory found.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
