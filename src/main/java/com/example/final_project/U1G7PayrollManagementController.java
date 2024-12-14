package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U1G7PayrollManagementController {

    @FXML
    private TableView<Payroll> payrollManagemantTableView;
    @FXML
    private TableColumn<Payroll, Integer> empIdpayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, String> empNamepayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, String> departmentpayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, Integer> salarypayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, Integer> bonuspayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, Integer> deductionpayrollManagemantTableColumn;
    @FXML
    private TableColumn<Payroll, Integer> totalPaypayrollManagemantTableColumn;

    @FXML
    private TextField empIdPayrollTextField;
    @FXML
    private TextField empNamePayrollTextField;
    @FXML
    private TextField salaryPayrollTextField;
    @FXML
    private TextField bonusPayrollTextfield;
    @FXML
    private TextField deductionPayrollTextField;
    @FXML
    private ComboBox<String> selectDeptPayrollComboBox;
    @FXML
    private TextField searchempPayrollTextField;

    private final ObservableList<Payroll> payrollList = FXCollections.observableArrayList();
    private static final String FILE_NAME = "payroll.bin";

    @FXML
    public void initialize() {
        // Bind columns to model properties
        empIdpayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("empId"));
        empNamepayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("empName"));
        departmentpayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        salarypayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        bonuspayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        deductionpayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        totalPaypayrollManagemantTableColumn.setCellValueFactory(new PropertyValueFactory<>("totalPay"));

        payrollManagemantTableView.setItems(payrollList);

        selectDeptPayrollComboBox.setItems(FXCollections.observableArrayList("HR", "AccountOfficer", "Development", "SalesOfficer"));

        // Load data from file or create dummy data
        loadPayrollData();
    }

    private void loadPayrollData() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                payrollList.clear();
                while (true) {
                    try {
                        Payroll payroll = (Payroll) ois.readObject();
                        payrollList.add(payroll);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to load payroll data.");
            }
        } else {
            // Load dummy data
            payrollList.addAll(
                    new Payroll(1, "Alice", "HR", 5000, 500, 200),
                    new Payroll(2, "Bob", "Finance", 6000, 600, 300),
                    new Payroll(3, "Charlie", "Development", 7000, 700, 400),
                    new Payroll(4, "David", "Marketing", 5500, 550, 250),
                    new Payroll(5, "Eva", "HR", 4800, 400, 200),
                    new Payroll(6, "Charlie", "Development", 7000, 700, 400),
                    new Payroll(7, "David", "Marketing", 5500, 550, 250),
                    new Payroll(8, "Eva", "HR", 4800, 400, 200)

            );
        }
    }

    @FXML
    public void searchPayrollButtonOnAction(ActionEvent event) {
        try {
            int searchId = Integer.parseInt(searchempPayrollTextField.getText().trim());
            ObservableList<Payroll> filteredList = payrollList.filtered(p -> p.getEmpId() == searchId);
            payrollManagemantTableView.setItems(filteredList);
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid employee ID.");
        }
    }

    @FXML
    public void updatePayrollButtonOnAction(ActionEvent event) {
        try {
            int empId = Integer.parseInt(empIdPayrollTextField.getText().trim());
            String empName = empNamePayrollTextField.getText().trim();
            String department = selectDeptPayrollComboBox.getValue();
            int salary = Integer.parseInt(salaryPayrollTextField.getText().trim());
            int bonus = Integer.parseInt(bonusPayrollTextfield.getText().trim());
            int deduction = Integer.parseInt(deductionPayrollTextField.getText().trim());

            Payroll selectedPayroll = payrollManagemantTableView.getSelectionModel().getSelectedItem();
            if (selectedPayroll != null) {
                selectedPayroll.setEmpId(empId);
                selectedPayroll.setEmpName(empName);
                selectedPayroll.setDepartment(department);
                selectedPayroll.setSalary(salary);
                selectedPayroll.setBonus(bonus);
                selectedPayroll.setDeduction(deduction);
                payrollManagemantTableView.refresh();
                showAlert(Alert.AlertType.INFORMATION, "Success", "Payroll updated.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "No employee selected.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input.");
        }
    }

    @FXML
    public void proceedPayrollButtonOnAction(ActionEvent event) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (Payroll payroll : payrollList) {
                oos.writeObject(payroll);
            }
            showAlert(Alert.AlertType.INFORMATION, "Success", "Final payroll saved.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save payroll data.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
