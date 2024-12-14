package com.example.final_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class U2G0SalesOfficerDashBoardController
{
    @javafx.fxml.FXML
    private AnchorPane salesDashBoardAnchorPane;
    @javafx.fxml.FXML
    private BorderPane salesOfficerDashboardborderpane;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void salesInventoryManagementDashBoardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G2_Inventory Management.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesOrderManagementDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G1_Order Management.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesProductCatalogDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G3_Product Catalog.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesCustomerComplaintsDashBoardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G6_Customer Complains.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesReportsDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G5_Sales Report.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesPromotionsDiscountsDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G7_Promotions & Discounts.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void salesBudgetandExpenditureDashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G4_Sales Budget and Expenditure.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}
    }

    @javafx.fxml.FXML
    public void signOutButtonOnAction(ActionEvent actionEvent) {
        try{
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
    public void SalesInvoicesDashBoardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader myFxmlLoader = new FXMLLoader(
                    MainApplication.class.getResource("U2G8_Sales_invoice.fxml")
            );
            salesOfficerDashboardborderpane.setCenter(myFxmlLoader.load());
        }
        catch(Exception e){}

    }
}