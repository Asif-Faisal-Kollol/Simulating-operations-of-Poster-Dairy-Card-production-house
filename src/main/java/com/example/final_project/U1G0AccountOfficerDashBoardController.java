package com.example.final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class U1G0AccountOfficerDashBoardController
{
    @javafx.fxml.FXML
    private AnchorPane accountDashBoardAnchorPane;
    @javafx.fxml.FXML
    private BorderPane accountOfficerDashboardborderpane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void accCreatePaymentDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G8_Payment.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accFinancialReportingDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G4_Financial_Reporting.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accPayrollManagementDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G7_Payroll Management.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accMakePaymentProcessingDashBoardButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G3_Payment_Processing.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accRefundReturnsDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G6_Refund and Returns.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accProductionCostProfitDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G2_Production Cost & Profit .fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void accInvoiceManagementDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G1_Invoice_Management.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void signOutButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U0_LogIn.fxml")
            );
            Scene viewScene = new Scene(myFxmlLoader.load());
            //Stage viewStage = new Stage();
            //viewStage.setTitle("Object Viewing Scene");
            Stage tempStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            tempStage.setScene(viewScene);
            tempStage.show();
        } catch(
                IOException e){};
    }

    @javafx.fxml.FXML
    public void accBudgetandExpenditureDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U1G5_Budget and Expenditure.fxml")
            );
            accountOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }
}