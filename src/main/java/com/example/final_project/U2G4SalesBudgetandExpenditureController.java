package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U2G4SalesBudgetandExpenditureController {

    @FXML
    private TextField budgetAllocationBudgetAndExpenditureTextField;

    @FXML
    private TextField expenseBudgetAndExpenditureTextField;

    @FXML
    private TextField descriptionBudgetAndExpenditureTextField;

    @FXML
    private TableView<BudgetAndExpenditure> budgetandExpenditureTableView;

    @FXML
    private TableColumn<BudgetAndExpenditure, Float> budgetAllocationandExpenditureTableColumn;

    @FXML
    private TableColumn<BudgetAndExpenditure, Float> expenseBudgetandExpenditureTableColumn;

    @FXML
    private TableColumn<BudgetAndExpenditure, String> descriptionBudgetandExpenditureTableColumn;

    private final ObservableList<BudgetAndExpenditure> budgetAndExpenditureList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        budgetAllocationandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("budget_allocation"));
        expenseBudgetandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
        descriptionBudgetandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        budgetandExpenditureTableView.setItems(budgetAndExpenditureList);
        loadBudgetAndExpenditureFromFile();
    }

    @FXML
    public void generateBudgetAndExpenditureButtonOnAction() {
        try {
            float budgetAllocation = Float.parseFloat(budgetAllocationBudgetAndExpenditureTextField.getText().trim());
            float expenditure = Float.parseFloat(expenseBudgetAndExpenditureTextField.getText().trim());
            String description = descriptionBudgetAndExpenditureTextField.getText().trim();

            if (description.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Description must not be empty.");
                return;
            }

            BudgetAndExpenditure newRecord = new BudgetAndExpenditure(budgetAllocation, expenditure, description);

            File file = new File("budgetandExpenditure.bin");
            try (FileOutputStream fos = new FileOutputStream(file, true);
                 ObjectOutputStream oos = file.exists() && file.length() > 0
                         ? new AppendableObjectOutputStream(fos)
                         : new ObjectOutputStream(fos)) {
                oos.writeObject(newRecord);
            }

            budgetAndExpenditureList.add(newRecord); // Add new record to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Budget and Expenditure record added successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Please ensure budget allocation and expenditure are numeric.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    @FXML
    public void loadBudgetAndExpenditureButtonOnAction() {
        loadBudgetAndExpenditureFromFile();
    }

    private void loadBudgetAndExpenditureFromFile() {
        File file = new File("budgetandExpenditure.bin");
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No records found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            budgetAndExpenditureList.clear();
            while (true) {
                try {
                    BudgetAndExpenditure record = (BudgetAndExpenditure) ois.readObject();
                    budgetAndExpenditureList.add(record);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Records loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void clearFields() {
        budgetAllocationBudgetAndExpenditureTextField.clear();
        expenseBudgetAndExpenditureTextField.clear();
        descriptionBudgetAndExpenditureTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }



}
