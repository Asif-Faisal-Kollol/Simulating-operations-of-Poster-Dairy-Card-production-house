package com.example.final_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class U2G3ProductCatalogController {

    @FXML
    private TextField productIDProductCatalogTextField;
    @FXML
    private TextField productNameProductCatalogTextField;
    @FXML
    private TextField priceProductCatalogTextField;
    @FXML
    private TextField stockProductCatalogTextField;

    @FXML
    private TableView<product> ProductCatalogTableView;
    @FXML
    private TableColumn<product, Integer> productIDProductCatalogTableColumn;
    @FXML
    private TableColumn<product, String> productNameProductCatalogTableColumn;
    @FXML
    private TableColumn<product, Integer> priceProductCatalogTableColumn;
    @FXML
    private TableColumn<product, String> stockProductCatalogTableColumn;

    private final ObservableList<product> productList = FXCollections.observableArrayList();

    private static final String FILE_NAME = "product.bin";

    @FXML
    public void initialize() {
        // Initialize TableView columns
        productIDProductCatalogTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        productNameProductCatalogTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        priceProductCatalogTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        stockProductCatalogTableColumn.setCellValueFactory(new PropertyValueFactory<>("product_stock"));

        ProductCatalogTableView.setItems(productList);
        loadProductsFromFile();
    }

    @FXML
    public void addProductProductCatalogButtonOnAction(ActionEvent actionEvent) {
        try {
            int productId = Integer.parseInt(productIDProductCatalogTextField.getText().trim());
            String productName = productNameProductCatalogTextField.getText().trim();
            int productPrice = Integer.parseInt(priceProductCatalogTextField.getText().trim());
            String productStock = stockProductCatalogTextField.getText().trim();

            if (productName.isEmpty() || productStock.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Error", "All fields must be filled.");
                return;
            }

            product newProduct = new product(productId, productName, productPrice, productStock);

            saveProductToFile(newProduct);
            productList.add(newProduct); // Add new product to the TableView

            showAlert(Alert.AlertType.INFORMATION, "Success", "Product added successfully.");
            clearFields();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid input. Check Product ID and Price fields.");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write to file.");
        }
    }

    private void loadProductsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            showAlert(Alert.AlertType.INFORMATION, "Info", "No products found.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            productList.clear();
            while (true) {
                try {
                    product prod = (product) ois.readObject();
                    productList.add(prod);
                } catch (EOFException e) {
                    break;
                }
            }

            showAlert(Alert.AlertType.INFORMATION, "Success", "Products loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to read from file.");
        }
    }

    private void saveProductToFile(product prod) throws IOException {
        File file = new File(FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file, true);
             ObjectOutputStream oos = file.exists() && file.length() > 0
                     ? new AppendableObjectOutputStream(fos)
                     : new ObjectOutputStream(fos)) {
            oos.writeObject(prod);
        }
    }

    private void clearFields() {
        productIDProductCatalogTextField.clear();
        productNameProductCatalogTextField.clear();
        priceProductCatalogTextField.clear();
        stockProductCatalogTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
