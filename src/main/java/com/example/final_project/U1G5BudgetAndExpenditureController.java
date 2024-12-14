package com.example.final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.util.List;

public class U1G5BudgetAndExpenditureController {

    @FXML
    private TextField suggestionTextField;

    @FXML
    private TableColumn<BudgetAndExpenditure, String> descriptionBudgetandExpenditureTableColumn;

    @FXML
    private TableColumn<BudgetAndExpenditure, Float> expenseBudgetandExpenditureTableColumn;

    @FXML
    private TableColumn<BudgetAndExpenditure, Float> budgetAllocationandExpenditureTableColumn;

    @FXML
    private TableView<BudgetAndExpenditure> budgetandExpenditureTableView;

    private final ObservableList<BudgetAndExpenditure> budgetAndExpenditureList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        budgetAllocationandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("budget_allocation"));
        expenseBudgetandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("expenditure"));
        descriptionBudgetandExpenditureTableColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        budgetandExpenditureTableView.setItems(budgetAndExpenditureList);

        // Load the existing records from the file into the TableView
        loadBudgetAndExpenditureFromFile();
    }

    @FXML
    public void sentBudgetAndExpenditureButtonOnAction(ActionEvent actionEvent) {
        // Show an alert confirming that the data has been sent
        showAlert(Alert.AlertType.INFORMATION, "Sent", "Budget and expenditure data sent successfully!");
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
                    break; // End of file reached
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Records loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
