package com.example.final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class U0LogInController {

    @FXML
    private TextField enterLogInPasswordTextField;

    @FXML
    private TextField enterLogInIdTextfield;

    @FXML
    private ComboBox<String> userTypeLogInComboBox;

    private final List<user> dummyUsers = new ArrayList<>();

    @FXML
    public void initialize() {
        // Add dummy users
        dummyUsers.add(new AccountOfficer(101, "password123")); // Account Officer
        dummyUsers.add(new SalesOfficer(102, "sales2023"));     // Sales Officer

        // Populate the ComboBox with user types
        userTypeLogInComboBox.getItems().addAll("Account Officer", "Sales Officer");
    }

    @FXML
    public void logInButtonLogInPageOnAction(ActionEvent actionEvent) {
        try {
            // Get input values
            int enteredId = Integer.parseInt(enterLogInIdTextfield.getText().trim());
            String enteredPassword = enterLogInPasswordTextField.getText().trim();
            String selectedUserType = userTypeLogInComboBox.getValue();

            if (selectedUserType == null || selectedUserType.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please select a user type.");
                return;
            }

            // Verify credentials
            user matchedUser = dummyUsers.stream()
                    .filter(user -> user.verifyLogin(enteredId, enteredPassword, selectedUserType))
                    .findFirst()
                    .orElse(null);

            if (matchedUser != null) {
                String fxmlFile = "Account Officer".equals(selectedUserType)
                        ? "U1G0_AccountOfficerDashBoard.fxml"
                        : "U2G0_SalesOfficerDashBoard.fxml";

                FXMLLoader myFxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxmlFile));
                Scene viewScene = new Scene(myFxmlLoader.load());
                Stage tempStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                tempStage.setScene(viewScene);
                tempStage.show();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid credentials or user type mismatch. Please try again.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "ID must be a number.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the dashboard.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}