package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class U2G7PromotionsDiscountsController {

    @FXML
    private TextField campaignNamePromotionTextField;
    @FXML
    private TextField productPromotionTextField;
    @FXML
    private TextField discountPromotionTextField;
    @FXML
    private DatePicker startDatePromotionDatepicker;
    @FXML
    private DatePicker endDatePromotionDatePicker;
    @FXML
    private TableView<Promotion> promotionTableView;
    @FXML
    private TableColumn<Promotion, String> campaignNamePromotionTableColumn;
    @FXML
    private TableColumn<Promotion, ArrayList<String>> productListPromotionTableColumn;
    @FXML
    private TableColumn<Promotion, Float> discountPercentagePromotionTableColumn;
    @FXML
    private TableColumn<Promotion, LocalDate> startDatePromotionTableColumn;
    @FXML
    private TableColumn<Promotion, LocalDate> endDatePromotionTableColumn;

    private final ObservableList<Promotion> promotionList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize TableView columns
        campaignNamePromotionTableColumn.setCellValueFactory(new PropertyValueFactory<>("campaign_name"));
        productListPromotionTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_list"));
        discountPercentagePromotionTableColumn.setCellValueFactory(new PropertyValueFactory<>("discount_percentage"));
        startDatePromotionTableColumn.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        endDatePromotionTableColumn.setCellValueFactory(new PropertyValueFactory<>("end_date"));

        promotionTableView.setItems(promotionList);
        loadPromotionsFromFile();
    }

    @FXML
    public void createPromotionButtonOnAction(ActionEvent actionEvent) {
        try {
            String campaignName = campaignNamePromotionTextField.getText().trim();
            String productText = productPromotionTextField.getText().trim();
            float discountPercentage = Float.parseFloat(discountPromotionTextField.getText().trim());
            LocalDate startDate = startDatePromotionDatepicker.getValue();
            LocalDate endDate = endDatePromotionDatePicker.getValue();

            if (campaignName.isEmpty() || productText.isEmpty() || startDate == null || endDate == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            ArrayList<String> productList = new ArrayList<String>(List.of(productText.split(",")));
            Promotion newPromotion = new Promotion(campaignName, productList, discountPercentage, startDate, endDate);

            File file = new File("promotionDiscount.bin");
            try (FileOutputStream fos = new FileOutputStream(file, true);
                 ObjectOutputStream oos = file.exists() && file.length() > 0
                         ? new AppendableObjectOutputStream(fos)
                         : new ObjectOutputStream(fos)) {
                oos.writeObject(newPromotion);
            }

            promotionList.add(newPromotion); // Add new promotion to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Promotion created successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check Discount Percentage field.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    @FXML
    public void updatePromotionButtonOnAction(ActionEvent actionEvent) {
        loadPromotionsFromFile();
    }

    private void loadPromotionsFromFile() {
        File file = new File("promotionDiscount.bin");
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No promotions found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            promotionList.clear();
            while (true) {
                try {
                    Promotion promotion = (Promotion) ois.readObject();
                    promotionList.add(promotion);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Promotions loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void clearFields() {
        campaignNamePromotionTextField.clear();
        productPromotionTextField.clear();
        discountPromotionTextField.clear();
        startDatePromotionDatepicker.setValue(null);
        endDatePromotionDatePicker.setValue(null);
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}


