package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U1G2ProductionCostProfitController {

    @FXML
    private TableView<Production_and_Profit> productionCostAndProfitTableView;
    @FXML
    private TableColumn<Production_and_Profit, Integer> productIDProductionTableColumn;
    @FXML
    private TableColumn<Production_and_Profit, Float> productionCostProductionTableColumn;
    @FXML
    private TableColumn<Production_and_Profit, Float> revenueProductionTableColumn;
    @FXML
    private TableColumn<Production_and_Profit, Float> profitProductionTableColumn;
    @FXML
    private TableColumn<Production_and_Profit, Float> budgetProductionTableColumn;
    @FXML
    private ComboBox<Integer> selectProductIDProductionComboBox;
    @FXML
    private TextField budgetProductionTextField;

    private final ObservableList<Production_and_Profit> productionList = FXCollections.observableArrayList();
    private static final String FILE_NAME = "productionCostAndProfit.bin";

    @FXML
    public void initialize() {
        // Bind table columns to model properties
        productIDProductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productionCostProductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("productionCost"));
        revenueProductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        profitProductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));
        budgetProductionTableColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));

        productionCostAndProfitTableView.setItems(productionList);

        // Load data from file or create dummy data
        loadProductionData();

        // Populate ComboBox with unique product IDs
        populateProductIDComboBox();
    }

    private void loadProductionData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                productionList.clear();
                while (true) {
                    try {
                        Production_and_Profit production = (Production_and_Profit) ois.readObject();
                        productionList.add(production);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load production data.");
            }
        } else {
            // Load dummy data if no file exists
            productionList.addAll(
                    new Production_and_Profit(1, 1000.0f, 1500.0f, 500.0f, 1200.0f),
                    new Production_and_Profit(2, 2000.0f, 3000.0f, 1000.0f, 2500.0f),
                    new Production_and_Profit(3, 1000.0f, 1500.0f, 500.0f, 1200.0f),
                    new Production_and_Profit(4, 2000.0f, 3000.0f, 1000.0f, 2500.0f),
                    new Production_and_Profit(5, 1000.0f, 1500.0f, 500.0f, 1200.0f),
                    new Production_and_Profit(6, 2000.0f, 3000.0f, 1000.0f, 2500.0f)
            );
        }
    }

    private void populateProductIDComboBox() {
        ObservableList<Integer> productIDs = FXCollections.observableArrayList();
        for (Production_and_Profit production : productionList) {
            if (!productIDs.contains(production.getProductID())) {
                productIDs.add(production.getProductID());
            }
        }
        selectProductIDProductionComboBox.setItems(productIDs);
    }

    @FXML
    public void setNewBudgetProductionButtonOnAction(ActionEvent event) {
        try {
            int productID = selectProductIDProductionComboBox.getValue();
            float newBudget = Float.parseFloat(budgetProductionTextField.getText().trim());

            Production_and_Profit selectedProduction = productionCostAndProfitTableView.getSelectionModel().getSelectedItem();
            if (selectedProduction != null && selectedProduction.getProductID() == productID) {
                selectedProduction.setBudget(newBudget);
                productionCostAndProfitTableView.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Budget updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Selected product does not match any item in the table.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input.");
        }
    }

    @FXML
    public void saveProductionDataButtonOnAction(ActionEvent event) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Production_and_Profit production : productionList) {
                oos.writeObject(production);
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Production data saved.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save production data.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


}
