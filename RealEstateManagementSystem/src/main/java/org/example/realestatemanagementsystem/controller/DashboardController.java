package org.example.realestatemanagementsystem.controller;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.realestatemanagementsystem.data.*;
import org.example.realestatemanagementsystem.logic.InputChecker;
import org.example.realestatemanagementsystem.logic.SliderThread;
import org.example.realestatemanagementsystem.logic.StageHelper;
import org.example.realestatemanagementsystem.records.PropertyCosts;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class DashboardController implements Initializable {

    public Label dashboard_existingProperties;
    @FXML
    private Label dashboard_totalSoldProperties;
    @FXML
    public Label dashboard_totalIncome;
    @FXML
    public Label dashboard_availableProperties;
    @FXML
    private Button logout_button;
    @FXML
    private Button dashboard_button;
    @FXML
    private Button propertyManagement_button;
    @FXML
    private Button tenantManagement_button;
    @FXML
    private Button maintenanceManagement_button;
    @FXML
    private Button advertisementManagement_button;
    @FXML
    private Button rentalManagement_button;
    @FXML
    private Button roleManagement_button;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane propertyManagement_form;
    @FXML
    private AnchorPane tenantManager_form;
    @FXML
    private AnchorPane maintenanceManagement_form;
    @FXML
    private AnchorPane advertisementManagement_form;
    @FXML
    private AnchorPane rentalManagement_form;
    @FXML
    private AnchorPane roleManagement_form;
    @FXML
    private Label username;
    @FXML
    private AnchorPane topForm;
    @FXML
    private ImageView propertyManagement_imageView;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private TextField propertyManagement_address;

    @FXML
    private TextField propertyManagement_amenities;

    @FXML
    private TableColumn<PropertyData, String> propertyManagement_col_address;

    @FXML
    private TableColumn<PropertyData, String> propertyManagement_col_amenities;

    @FXML
    private TableColumn<PropertyData, Double> propertyManagement_col_rent;

    @FXML
    private TableColumn<PropertyData, Double> propertyManagement_col_size;

    @FXML
    private TableColumn<PropertyData, String> propertyManagement_col_status;

    @FXML
    private TableColumn<PropertyData, String> propertyManagement_col_type;

    @FXML
    private TextField propertyManagement_rent;

    @FXML
    private TextField propertyManagement_search;

    @FXML
    private TextField propertyManagement_size;

    @FXML
    private ComboBox<String> propertyManagement_status;

    @FXML
    private TableView<PropertyData> propertyManagement_tableView;

    @FXML
    private ComboBox<String> propertyManagement_type;

    @FXML
    private TableView<TenantData> tenantManagement_tableView;

    @FXML
    private TableColumn<TenantData, String> tenantManagement_col_firstName;
    @FXML
    private TableColumn<TenantData, String> tenantManagement_col_lastName;
    @FXML
    private TableColumn<TenantData, String> tenantManagement_col_email;
    @FXML
    private Button propertyManagement_addButton;
    @FXML
    private TextField tenantManagement_firstName;
    @FXML
    private TextField tenantManagement_lastName;
    @FXML
    private TextField tenantManagement_email;

    @FXML
    private TextField leaseContractsManagement_amount;

    @FXML
    private TableColumn<LeaseContractsData, Date> leaseContractsManagement_col_endDate;

    @FXML
    private TableColumn<LeaseContractsData, String> leaseContractsManagement_col_paymentStatus;

    @FXML
    private TableColumn<LeaseContractsData, String> leaseContractsManagement_col_property;

    @FXML
    private TableColumn<LeaseContractsData, String> leaseContractsManagement_col_tenant;

    @FXML
    private TableColumn<LeaseContractsData, Double> leaseContractsManagement_col_rentAmount;

    @FXML
    private TableColumn<LeaseContractsData, Date> leaseContractsManagement_col_startDate;

    @FXML
    private DatePicker leaseContractsManagement_moveInDate;
    @FXML
    private DatePicker leaseContractsManagement_moveOutDate;

    @FXML
    private TableView<LeaseContractsData> leaseContractsManagement_tableView;

    @FXML
    private ComboBox<String> propertyComboBox;

    @FXML
    private ComboBox<String> tenantComboBox;

    @FXML
    private ComboBox<String> leaseContractsManagement_status;

    @FXML
    private TextField payment_amount;

    @FXML
    private DatePicker payment_date;
    @FXML
    private ComboBox<String> payment_tenantComboBox;
    @FXML
    private ComboBox<LeaseContractsData> payment_leaseContractComboBox;

    @FXML
    private TableColumn<PaymentData, Integer> payment_col_tenant;

    @FXML
    private TableColumn<PaymentData, Integer> payment_col_leaseContracts;
    @FXML
    private TableColumn<PaymentData, Double> payment_col_amount;

    @FXML
    private TableColumn<PaymentData, Date> payment_col_paymentDate;
    //@FXML
    //private BarChart<String, Double> chart;
    @FXML
    private StackedBarChart<Double, String> chart;
    @FXML
    private TableView<PaymentData> payment_tableView;
    @FXML
    private Button propertyManagement_clearButton;

    @FXML
    private Button propertyManagement_deleteButton;

    private ObservableList<PropertyData> listAddProperties;
    @FXML
    private ComboBox<String> maintenanceManagement_status;

    @FXML
    private ComboBox<String> maintenanceManagement_priority;

    @FXML
    private ComboBox<PropertyData> maintenanceManagement_property;
    @FXML
    private TableView<MaintenanceRequestData> maintenanceManagement_tableView;
    @FXML
    private TextField maintenanceManagement_description;
    @FXML
    private TextField maintenanceManagement_costs;
    @FXML
    private TableColumn<MaintenanceRequestData, String> maintenanceManagement_col_propertyAddress;
    @FXML
    private TableColumn<MaintenanceRequestData, String> maintenanceManagement_col_status;
    @FXML
    private TableColumn<MaintenanceRequestData, String> maintenanceManagement_col_description;
    @FXML
    private TableColumn<MaintenanceRequestData, String> maintenanceManagement_col_priority;
    @FXML
    private TableColumn<MaintenanceRequestData, Integer> maintenanceManagement_col_costs;
    private ObservableList<MaintenanceRequestData> listMaintenanceData;
    private ObservableList<AdvertisementData> listAdvertisementData;
    @FXML
    private TableColumn<AdvertisementData, String> advertisementManagement_col_title;
    @FXML
    private TableColumn<AdvertisementData, String> advertisementManagement_col_property;
    @FXML
    private TableColumn<AdvertisementData, String> advertisementManagement_col_description;
    @FXML
    private TableColumn<AdvertisementData, Integer> advertisementManagement_col_costs;
    @FXML
    private TableColumn<AdvertisementData, Date> advertisementManagement_col_publicDate;
    @FXML
    private TableColumn<AdvertisementData, Date> advertisementManagement_col_expireDate;
    @FXML
    private TableView<AdvertisementData> advertisementManagement_tableView;
    @FXML
    private PieChart pieChart;
    @FXML
    private AnchorPane chartPane;
    @FXML
    private TextField advertisementManagement_title;
    @FXML
    private TextField advertisementManagement_description;
    @FXML
    private DatePicker advertisementManagement_publicDate;
    @FXML
    private DatePicker advertisementManagement_expireDate;
    @FXML
    private ComboBox<PropertyData> advertisementManagement_property;
    @FXML
    private TextField advertisementManagement_costs;

    @FXML
    private TextField maintenanceManagement_search;
    @FXML
    private TextField advertisement_search;

    public void displayUsername() {
        username.setText(RealEstateMetrics.username);
    }

    @FXML
    private ImageView advertisementManagement_imageView;

    private SliderThread sliderThread;


    public void updateSelectedAdvertisement() {
        AdvertisementData selectedAdvertisement = advertisementManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedAdvertisement == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Advertisement Selected");
            alert.setContentText("Please select an advertisement to update.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Advertisement");
        dialog.setHeaderText("Update Advertisement Details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(updateButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 200, 10, 10));

        TextField titleField = new TextField(selectedAdvertisement.getTitle());
        TextField descriptionField = new TextField(selectedAdvertisement.getDescription());
        DatePicker publicDateField = new DatePicker(selectedAdvertisement.getPublicationDate().toLocalDate());
        DatePicker expireDateField = new DatePicker(selectedAdvertisement.getExpirationDate().toLocalDate());

        TextField costsField = new TextField(String.valueOf(selectedAdvertisement.getAdvertisementCosts()));
        Label errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);

        costsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                costsField.setText(oldValue);
                errorLabel.setText("Invalid input! Please enter a valid positive number.");
            } else {
                errorLabel.setText("");
            }
        });

        Label titleLabel = new Label("Title:");
        Label descriptionLabel = new Label("Description:");
        Label publicDateLabel = new Label("Publication Date:");
        Label expireDateLabel = new Label("Expiration Date:");
        Label costsLabel = new Label("Advertisement Costs:");

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);

        grid.add(descriptionLabel, 0, 1);
        grid.add(descriptionField, 1, 1);

        grid.add(publicDateLabel, 0, 2);
        grid.add(publicDateField, 1, 2);

        grid.add(expireDateLabel, 0, 3);
        grid.add(expireDateField, 1, 3);

        grid.add(costsLabel, 0, 4);
        grid.add(costsField, 1, 4);

        grid.add(errorLabel, 1, 5);

        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(false);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                selectedAdvertisement.setTitle(titleField.getText());
                selectedAdvertisement.setDescription(descriptionField.getText());
                selectedAdvertisement.setPublicationDate(java.sql.Date.valueOf(publicDateField.getValue()));
                selectedAdvertisement.setExpirationDate(java.sql.Date.valueOf(expireDateField.getValue()));
                selectedAdvertisement.setAdvertisementCosts(Double.parseDouble(costsField.getText()));

                return updateButtonType;
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == updateButtonType) {
                try (Connection connection = DBConnection.dbConnection();
                     PreparedStatement statement = connection.prepareStatement(DBAdvertisementConstants.UPDATE_ADVERTISEMENT)) {
                    statement.setString(1, selectedAdvertisement.getTitle());
                    statement.setString(2, selectedAdvertisement.getDescription());
                    statement.setDate(3, (Date) selectedAdvertisement.getPublicationDate());
                    statement.setDate(4, (Date) selectedAdvertisement.getExpirationDate());
                    statement.setDouble(5, selectedAdvertisement.getAdvertisementCosts());
                    statement.setInt(6, selectedAdvertisement.getId());

                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Advertisement edited successfully!");
                        successAlert.showAndWait();

                        showAdvertisementList();
                        advertisementManagement_tableView.getSelectionModel().clearSelection();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to edit advertisement!");
                        errorAlert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("SQL Error");
                    errorAlert.setHeaderText("Error executing SQL query");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    public void deleteSelectedAdvertisement() {
        AdvertisementData selectedAdvertisement = advertisementManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedAdvertisement != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Advertisement");
            alert.setContentText("Are you sure you want to delete the selected advertisement?");

            ButtonType buttonTypeDelete = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeDelete) {
                try {
                    String sql = DBAdvertisementConstants.DELETE_ADVERTISEMENT;
                    connect = DBConnection.dbConnection();
                    if (connect != null) {
                        prepare = connect.prepareStatement(sql);
                    }
                    prepare.setInt(1, selectedAdvertisement.getId());
                    prepare.executeUpdate();

                    showAdvertisementList();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Advertisement deleted successfully.");
                    successAlert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while deleting advertisement.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.setTitle("Warning");
            noSelectionAlert.setHeaderText(null);
            noSelectionAlert.setContentText("Please select a advertisement to delete!");
            noSelectionAlert.showAndWait();
        }
    }

    public void insertAdvertisements() {
        String sql = DBAdvertisementConstants.INSERT_ADVERTISEMENT;
        Alert alert;

        try {
            InputChecker checker = new InputChecker();
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(sql);


            if (advertisementManagement_title.getText().isEmpty()
                    || advertisementManagement_property.getValue() == null
                    || advertisementManagement_description.getText().isEmpty()
                    || advertisementManagement_publicDate.getValue() == null
                    || advertisementManagement_expireDate.getValue() == null
                    || advertisementManagement_costs.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields!");
                alert.showAndWait();
                return;
            } else if (!checker.checkingDescription(advertisementManagement_title.getText())
                    && !checker.checkingDescription(advertisementManagement_description.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input! Please fill with letters!");
                alert.showAndWait();
                return;
            }

            String costsText = advertisementManagement_costs.getText();
            if (!costsText.matches("\\d*(\\.\\d{0,2})?") || Double.parseDouble(costsText) < 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid advertisement costs! Please enter a valid positive number.");
                alert.showAndWait();
                return;
            }
            PropertyData selectedProperty = advertisementManagement_property.getValue();
            int propertyId = selectedProperty.getId();

            LocalDate publicDate = advertisementManagement_publicDate.getValue();
            LocalDate expireDate = advertisementManagement_expireDate.getValue();
            Date sqlPublicDate = Date.valueOf(publicDate);
            Date sqlExpireDate = Date.valueOf(expireDate);

            prepare.setString(1, advertisementManagement_title.getText());
            prepare.setInt(2, propertyId);
            prepare.setString(3, advertisementManagement_description.getText());
            prepare.setDate(4, sqlPublicDate);
            prepare.setDate(5, sqlExpireDate);
            prepare.setDouble(6, Double.parseDouble(costsText));

            prepare.executeUpdate();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added!");
            alert.showAndWait();
            showAdvertisementList();

        } catch (SQLException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add advertisement!");
            alert.showAndWait();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void searchAdvertisement() {

        FilteredList<AdvertisementData> filter = new FilteredList<>(listAdvertisementData, e -> true);
        advertisement_search.textProperty().addListener((observable, oldValue, newValue) -> filter.setPredicate(predicateData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String keySearch = newValue.toLowerCase();
            if (predicateData.getTitle().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getPropertyAddress().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getDescription().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getPublicationDate().toString().contains(keySearch)) {
                return true;
            } else if (predicateData.getExpirationDate().toString().contains(keySearch)) {
                return true;
            } else {
                String advertisementCostsString = Double.toString(predicateData.getAdvertisementCosts());
                return advertisementCostsString.toLowerCase().contains(keySearch);
            }
        }));

        SortedList<AdvertisementData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(advertisementManagement_tableView.comparatorProperty());
        advertisementManagement_tableView.setItems(sortData);

    }

    public void showAdvertisementList() {

        listAdvertisementData = addAdvertisementList();
        advertisementManagement_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        advertisementManagement_col_property.setCellValueFactory(new PropertyValueFactory<>("propertyAddress"));
        advertisementManagement_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        advertisementManagement_col_publicDate.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        advertisementManagement_col_expireDate.setCellValueFactory(new PropertyValueFactory<>("expirationDate"));
        advertisementManagement_col_costs.setCellValueFactory(new PropertyValueFactory<>("advertisementCosts"));

        advertisementManagement_tableView.setItems(listAdvertisementData);
        advertisementManagement_tableView.refresh();
    }

    public ObservableList<AdvertisementData> addAdvertisementList() {
        ObservableList<AdvertisementData> listAdvertisementData = FXCollections.observableArrayList();
        String sql = DBAdvertisementConstants.SELECT_ALL_ADVERTISEMENTS;
        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
            }
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int propertyId = resultSet.getInt("property_id");
                String propertyAddress = getPropertyAddress(propertyId);
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date publicationDate = resultSet.getDate("publication_date");
                Date expirationDate = resultSet.getDate("expiration_date");

                double advertisementCosts = resultSet.getDouble("advertisement_costs");

                AdvertisementData advertisementData = new AdvertisementData(id, propertyId, propertyAddress, title, description, publicationDate, expirationDate, advertisementCosts);
                listAdvertisementData.add(advertisementData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listAdvertisementData;
    }

    public void displayPieChart() {
        pieChart();
    }

    public void pieChart() {
        String query = DBMaintenanceConstants.COUNT_OPEN_MAINTENANCE_BY_STATUS;
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        try {
            Connection connect = DBConnection.dbConnection();
            PreparedStatement prepare = connect.prepareStatement(query);
            ResultSet result = prepare.executeQuery();

            while (result.next()) {
                String status = result.getString("status");
                int count = result.getInt("count");
                pieChartData.add(new PieChart.Data(status, count));
            }

            result.close();
            prepare.close();
            connect.close();

            PieChart pieChart = new PieChart(pieChartData);
            pieChart.setTitle("Open Maintenance Work");

            pieChart.setPrefSize(500, 180);

            pieChart.setMinSize(500, 180);
            pieChart.setMaxSize(500, 180);

            chartPane.setPrefSize(537, 200);
            chartPane.setMinSize(537, 200);
            chartPane.setMaxSize(537, 200);
            chartPane.setCenterShape(true);
            chartPane.getChildren().clear();
            chartPane.getChildren().add(pieChart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteSelectedMaintenance() {
        MaintenanceRequestData selectedMaintenance = maintenanceManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedMaintenance != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Maintenance Request");
            alert.setContentText("Are you sure you want to delete the selected maintenance?");

            ButtonType buttonTypeDelete = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeDelete) {
                try {
                    String sql = DBMaintenanceConstants.DELETE_MAINTENANCE;
                    connect = DBConnection.dbConnection();
                    if (connect != null) {
                        prepare = connect.prepareStatement(sql);
                    }
                    prepare.setInt(1, selectedMaintenance.getId());
                    prepare.executeUpdate();

                    showMaintenanceList();
                    displayPieChart();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Maintenance deleted successfully.");
                    successAlert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while deleting maintenance.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.setTitle("Warning");
            noSelectionAlert.setHeaderText(null);
            noSelectionAlert.setContentText("Please select a maintenance to delete!");
            noSelectionAlert.showAndWait();
        }
    }

    public void updateSelectedMaintenance() {
        MaintenanceRequestData selectedMaintenance = maintenanceManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedMaintenance == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Maintenance Selected");
            alert.setContentText("Please select a maintenance to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Maintenance");
        dialog.setHeaderText("Edit Maintenance Details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(updateButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 200, 10, 10));

        ComboBox<String> statusField = new ComboBox<>();
        statusField.getItems().addAll("Pending",
                "In Progress",
                "Completed",
                "On Hold",
                "Cancelled");
        statusField.setValue(selectedMaintenance.getStatus());

        ComboBox<String> priorityField = new ComboBox<>();
        priorityField.getItems().addAll("Critical",
                "High",
                "Medium",
                "Low");
        priorityField.setValue(selectedMaintenance.getPriority());

        TextField descriptionField = new TextField(selectedMaintenance.getDescription());
        TextField costsField = new TextField(String.valueOf(selectedMaintenance.getMaintenanceCosts()));
        Label errorLabel = new Label();
        errorLabel.setTextFill(javafx.scene.paint.Color.RED);

        costsField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d{0,2})?")) {
                costsField.setText(oldValue);
                errorLabel.setText("Invalid input! Please enter a valid positive number.");
            } else {
                errorLabel.setText("");
            }
        });

        Label statusLabel = new Label("Status:");
        Label priorityLabel = new Label("Priority:");
        Label descriptionLabel = new Label("Description:");
        Label costsLabel = new Label("Maintenance Costs:");

        grid.add(statusLabel, 0, 0);
        grid.add(statusField, 1, 0);

        grid.add(priorityLabel, 0, 1);
        grid.add(priorityField, 1, 1);

        grid.add(descriptionLabel, 0, 2);
        grid.add(descriptionField, 1, 2);

        grid.add(costsLabel, 0, 3);
        grid.add(costsField, 1, 3);
        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(false);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                selectedMaintenance.setStatus(statusField.getValue());
                selectedMaintenance.setPriority(priorityField.getValue());
                selectedMaintenance.setDescription(descriptionField.getText());
                selectedMaintenance.setMaintenanceCosts(Double.parseDouble(costsField.getText()));

                return updateButtonType;
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == updateButtonType) {
                try (Connection connection = DBConnection.dbConnection();
                     PreparedStatement statement = connection.prepareStatement(DBMaintenanceConstants.UPDATE_MAINTENANCE)) {
                    statement.setString(1, selectedMaintenance.getStatus());
                    statement.setString(2, selectedMaintenance.getPriority());
                    statement.setString(3, selectedMaintenance.getDescription());
                    statement.setDouble(4, selectedMaintenance.getMaintenanceCosts());
                    statement.setInt(5, selectedMaintenance.getId());


                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Maintenance request edited successfully!");
                        successAlert.showAndWait();

                        showMaintenanceList();
                        maintenanceManagement_tableView.getSelectionModel().clearSelection();
                        displayPieChart();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to edit maintenance request!");
                        errorAlert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("SQL Error");
                    errorAlert.setHeaderText("Error executing SQL query");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    public void searchMaintenance() {

        FilteredList<MaintenanceRequestData> filter = new FilteredList<>(listMaintenanceData, e -> true);
        maintenanceManagement_search.textProperty().addListener((observable, oldValue, newValue) -> filter.setPredicate(predicateData -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String keySearch = newValue.toLowerCase();

            if (predicateData.getPropertyAddress().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getStatus().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getDescription().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicateData.getPriority().toLowerCase().contains(keySearch)) {
                return true;
            } else {
                String maintenanceCostsString = Double.toString(predicateData.getMaintenanceCosts());
                return maintenanceCostsString.toLowerCase().contains(keySearch);
            }
        }));

        SortedList<MaintenanceRequestData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(maintenanceManagement_tableView.comparatorProperty());
        maintenanceManagement_tableView.setItems(sortData);

    }

    public void insertMaintenanceRequests() {
        String sql = DBMaintenanceConstants.INSERT_MAINTENANCE;
        Alert alert;

        try {
            InputChecker checker = new InputChecker();
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(sql);


            if (maintenanceManagement_property.getValue() == null
                    || maintenanceManagement_status.getValue() == null
                    || maintenanceManagement_priority.getValue() == null
                    || maintenanceManagement_description.getText().isEmpty()
                    || maintenanceManagement_costs.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all required fields!");
                alert.showAndWait();
                return;
            } else if (!checker.checkingDescription(maintenanceManagement_description.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid input! Please fill with letters!");
                alert.showAndWait();
                return;
            }

            String costsText = maintenanceManagement_costs.getText();
            if (!costsText.matches("\\d*(\\.\\d{0,2})?") || Double.parseDouble(costsText) < 0) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid maintenance costs! Please enter a valid positive number.");
                alert.showAndWait();
                return;
            }
            PropertyData selectedProperty = maintenanceManagement_property.getValue();
            int propertyId = selectedProperty.getId();

            prepare.setInt(1, propertyId);
            prepare.setString(2, maintenanceManagement_status.getValue());
            prepare.setString(3, maintenanceManagement_description.getText());
            prepare.setString(4, maintenanceManagement_priority.getValue());
            prepare.setDouble(5, Double.parseDouble(maintenanceManagement_costs.getText()));

            prepare.executeUpdate();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added!");
            alert.showAndWait();
            showMaintenanceList();
            displayPieChart();

        } catch (SQLException e) {
            e.printStackTrace();
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Failed to add maintenance request!");
            alert.showAndWait();
        } finally {
            try {
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void fillPropertyComboBox() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.dbConnection();

            String sql = DBPropertyConstants.SELECT_ID_AND_ADDRESS;
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<PropertyData> properties = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");

                PropertyData property = new PropertyData(id, address);
                properties.add(property);
            }

            maintenanceManagement_property.getItems().addAll(properties);
            advertisementManagement_property.getItems().addAll(properties);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showMaintenanceList() {

        listMaintenanceData = addMaintenanceList();
        maintenanceManagement_col_propertyAddress.setCellValueFactory(new PropertyValueFactory<>("propertyAddress"));
        maintenanceManagement_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        maintenanceManagement_col_priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        maintenanceManagement_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        maintenanceManagement_col_costs.setCellValueFactory(new PropertyValueFactory<>("maintenanceCosts"));

        maintenanceManagement_tableView.setItems(listMaintenanceData);
        maintenanceManagement_tableView.refresh();
    }


    public ObservableList<MaintenanceRequestData> addMaintenanceList() {
        ObservableList<MaintenanceRequestData> listMaintenanceData = FXCollections.observableArrayList();
        String sql = DBMaintenanceConstants.SELECT_ALL_MAINTENANCE_REQUESTS;
        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
            }
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int propertyId = resultSet.getInt("property_id");
                String propertyAddress = getPropertyAddress(propertyId);
                String status = resultSet.getString("status");
                String priority = resultSet.getString("priority");
                String description = resultSet.getString("description");
                double maintenanceCosts = resultSet.getDouble("maintenance_costs");

                MaintenanceRequestData maintenanceData = new MaintenanceRequestData(id, status, propertyId, description, priority, propertyAddress, maintenanceCosts);
                listMaintenanceData.add(maintenanceData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMaintenanceData;
    }

    public void displayTotalSoldProperties() {
        dashboard_totalSoldProperties.setText(String.valueOf(RealEstateMetrics.totalSoldProperties));
    }

    public void displayTotalIncome() {
        dashboard_totalIncome.setText(String.valueOf(RealEstateMetrics.totalIncome));
    }

    public void displayNumberOfAvailableProperties() {
        dashboard_availableProperties.setText(String.valueOf(RealEstateMetrics.availableProperties));
    }


    public void displayChart() {
        List<XYChart.Series> charts = DashboardController.chart();
        chart.getData().clear();
        for (XYChart.Series chart : charts) {
            this.chart.getData().add(chart);
        }
    }


    public static List<XYChart.Series> chart() {

        String query = DBPropertyConstants.PROPERTY_COST_SUMMARIES;
        List<XYChart.Series> charts = new ArrayList<>();

        try {
            Connection connect = DBConnection.dbConnection();

            PreparedStatement rentPrepare = connect.prepareStatement(query);
            ResultSet rentResult = rentPrepare.executeQuery();
            List<PropertyCosts> propertyCostsList = new ArrayList<>();
            while (rentResult.next()) {
                double totalRent = rentResult.getDouble("total_rent");
                double totalAdvertisements = rentResult.getDouble("total_advertisements");
                double totalMaintenance = rentResult.getDouble("total_maintenance");
                String propertyName = rentResult.getString("property_address");
                propertyCostsList.add(new PropertyCosts(propertyName, totalRent, totalMaintenance, totalAdvertisements));
            }
            List<String> xAxisCategories = new ArrayList<>();
            for (PropertyCosts propertyCosts : propertyCostsList) {
                xAxisCategories.add(propertyCosts.address());
            }

            CategoryAxis yAxis = new CategoryAxis();
            Axis xAxis = new NumberAxis();
            StackedBarChart<Double, String> sbc =
                    new StackedBarChart<Double, String>(xAxis, yAxis);
            yAxis.setCategories(FXCollections.observableArrayList(
                    xAxisCategories));

            XYChart.Series<Double, String> seriesTotalRent = new StackedBarChart.Series<>();
            seriesTotalRent.setName("Total Rent");
            XYChart.Series<Double, String> seriesTotalMaintenance = new StackedBarChart.Series<>();
            seriesTotalMaintenance.setName("Total Maintenance");
            XYChart.Series<Double, String> seriesTotalAdvertisements = new StackedBarChart.Series<>();
            seriesTotalAdvertisements.setName("Total Advertisements");

            for (PropertyCosts propertyCosts : propertyCostsList) {
                seriesTotalRent.getData().add(new XYChart.Data<>(propertyCosts.totalRent(), propertyCosts.address()));
                seriesTotalMaintenance.getData().add(new XYChart.Data<>(propertyCosts.maintenanceCosts(), propertyCosts.address()));
                seriesTotalAdvertisements.getData().add(new XYChart.Data<>(propertyCosts.advertisementCosts(), propertyCosts.address()));
            }

            sbc.getData().add(seriesTotalRent);
            sbc.getData().add(seriesTotalMaintenance);
            sbc.getData().add(seriesTotalAdvertisements);
            //sbc.getData().addAll(seriesTotalRent, seriesTotalMaintenance, seriesTotalAdvertisements);
            charts.add(seriesTotalRent);
            charts.add(seriesTotalMaintenance);
            charts.add(seriesTotalAdvertisements);

            rentResult.close();
            rentPrepare.close();

            connect.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return charts;
    }


    public static void numberOfAvailableProperties() {
        int availableProperties = -1;
        PreparedStatement prepare = null;
        Connection connect = null;

        try {
            String query = DBPropertyConstants.COUNT_AVAILABLE_PROPERTIES;
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(query);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                availableProperties = resultSet.getInt("count");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RealEstateMetrics.availableProperties = availableProperties;
    }

    public static void totalIncome() {
        int totalIncome = -1;
        PreparedStatement prepare = null;
        Connection connect = null;

        try {
            String query = DBPaymentConstants.SUM_TOTAL_PAYMENTS;
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(query);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                totalIncome = resultSet.getInt("total");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RealEstateMetrics.totalIncome = totalIncome;
    }

    public static void totalSoldProperties() {
        int totalSoldProperties = -1;
        PreparedStatement prepare = null;
        Connection connect = null;

        try {
            String query = DBPropertyConstants.COUNT_SOLD_PROPERTIES;
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(query);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                totalSoldProperties = resultSet.getInt("count");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (prepare != null) {
                    prepare.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        RealEstateMetrics.totalSoldProperties = totalSoldProperties;
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }


    /*
    public void insertPayments() {
        String sql1 = DBPaymentConstants.SELECT_PAYMENTS;
        Alert alert;

        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql1);
            }

            LocalDate paymentDate = payment_date.getValue();

            if (paymentDate == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a payment date!");
                alert.showAndWait();
                return;
            }

            prepare.setDate(1, java.sql.Date.valueOf(paymentDate));
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(paymentDate + " already exists");
                alert.showAndWait();
            } else {
                if (payment_tenantComboBox.getValue() == null
                        || payment_leaseContractComboBox.getValue() == null
                        || payment_amount.getText().isEmpty()
                ) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all required fields!");
                    alert.showAndWait();
                } else {
                    double amount;
                    try {
                        amount = Double.parseDouble(payment_amount.getText());
                        if (amount < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Amount must be a positive number!");
                        alert.showAndWait();
                        return;
                    }

                    int tenantId = getTenantIdByEmail(payment_tenantComboBox.getValue());
                    int leaseContractId = payment_leaseContractComboBox.getValue();

                    String sql = DBPaymentConstants.INSERT_PAYMENT;
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, tenantId);
                    prepare.setInt(2, leaseContractId);
                    prepare.setDouble(3, amount);
                    prepare.setDate(4, java.sql.Date.valueOf(paymentDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();
                    // clearLeaseContractsList();
                    showAddPaymentsList();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     */
    public void insertPayments() {
        String sql1 = DBPaymentConstants.SELECT_PAYMENTS;
        Alert alert;

        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql1);
            }

            LocalDate paymentDate = payment_date.getValue();

            if (paymentDate == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select a payment date!");
                alert.showAndWait();
                return;
            }

            prepare.setDate(1, java.sql.Date.valueOf(paymentDate));
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(paymentDate + " already exists");
                alert.showAndWait();
            } else {
                if (payment_tenantComboBox.getValue() == null
                        || payment_leaseContractComboBox.getValue() == null
                        || payment_amount.getText().isEmpty()
                ) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all required fields!");
                    alert.showAndWait();
                } else {
                    double amount;
                    try {
                        amount = Double.parseDouble(payment_amount.getText());
                        if (amount < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Amount must be a positive number!");
                        alert.showAndWait();
                        return;
                    }

                    LeaseContractsData selectedContract = payment_leaseContractComboBox.getValue();
                    int tenantId = getTenantIdByEmail(payment_tenantComboBox.getValue());
                    int leaseContractId = selectedContract.getId(); // Hier die ID des ausgewählten Vertrags

                    String sql = DBPaymentConstants.INSERT_PAYMENT;
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, tenantId);
                    prepare.setInt(2, leaseContractId);
                    prepare.setDouble(3, amount);
                    prepare.setDate(4, java.sql.Date.valueOf(paymentDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();
                    // clearLeaseContractsList();
                    showAddPaymentsList();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void showAddPaymentsList() {
        ObservableList<PaymentData> listPayments = addPaymentsList();
        payment_col_tenant.setCellValueFactory(new PropertyValueFactory<>("tenantName"));
        payment_col_leaseContracts.setCellValueFactory(new PropertyValueFactory<>("propertyAddress"));
        payment_col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        payment_col_paymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));

        payment_tableView.setItems(listPayments);
        payment_tableView.refresh();
    }

    public ObservableList<PaymentData> addPaymentsList() {
        ObservableList<PaymentData> listData = FXCollections.observableArrayList();
        String sql = DBPaymentConstants.SELECT_ALL_PAYMENTS;
        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
            }
            ResultSet resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tenantId = resultSet.getInt("tenant_id");
                String tenantName = getTenantName(tenantId);
                int leaseContractId = resultSet.getInt("lease_contract_id");
                String propertyAddress = getPropertyAddressForPayments(leaseContractId);
                double rentAmount = resultSet.getDouble("amount");
                LocalDate paymentDate = resultSet.getDate("payment_date").toLocalDate();

                PaymentData paymentData = new PaymentData(id, tenantId, tenantName, leaseContractId, propertyAddress, rentAmount, paymentDate);
                listData.add(paymentData);
            }
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error adding payments to list", e);
            return FXCollections.observableArrayList();
        }
        return listData;
    }


    public void searchProperty() {

        FilteredList<PropertyData> filter = new FilteredList<>(listAddProperties, e -> true);
        propertyManagement_search.textProperty().addListener((observable, oldValue, newValue) -> filter.setPredicate(predicatePropertyData -> {
            if (newValue.isEmpty()) {
                return true;
            }
            String keySearch = newValue.toLowerCase();

            if (predicatePropertyData.getStatus().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicatePropertyData.getAddress().toLowerCase().contains(keySearch)) {
                return true;
            } else if (Double.toString(predicatePropertyData.getSize()).contains(keySearch)) {
                return true;
            } else if (predicatePropertyData.getAmenities().toLowerCase().contains(keySearch)) {
                return true;
            } else if (predicatePropertyData.getType().toLowerCase().contains(keySearch)) {
                return true;
            } else return Double.toString(predicatePropertyData.getRent()).contains(keySearch);
        }));
        SortedList<PropertyData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(propertyManagement_tableView.comparatorProperty());
        propertyManagement_tableView.setItems(sortData);

    }


    public void createLeaseContractPdf(LocalDate startDate, LocalDate endDate, String tenantName, String propertyAddress, String propertyType, String propertyAmenities, String filePath) {
        Document document = new Document(PageSize.A4);
        PdfWriter writer = null;

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            LocalDate createDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, new Color(0, 102, 204));
            Paragraph titleParagraph = new Paragraph("LEASE AGREEMENT", titleFont);
            titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            titleParagraph.setSpacingAfter(20);
            document.add(titleParagraph);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            addTableCell(infoTable, "Date:", true);
            addTableCell(infoTable, createDate.format(formatter), false);
            addTableCell(infoTable, "Landlord:", true);
            addTableCell(infoTable, "Wiener Wohnen", false);
            addTableCell(infoTable, "Tenant:", true);
            addTableCell(infoTable, tenantName, false);
            document.add(infoTable);

            document.add(new Paragraph("\n"));

            PdfPTable detailsTable = new PdfPTable(2);
            detailsTable.setWidthPercentage(100);

            PdfPCell headerCell1 = new PdfPCell(new Phrase("Description", new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE)));
            headerCell1.setBackgroundColor(new Color(0, 102, 204));
            headerCell1.setPadding(10f);
            detailsTable.addCell(headerCell1);

            PdfPCell headerCell2 = new PdfPCell(new Phrase("Details", new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE)));
            headerCell2.setBackgroundColor(new Color(0, 102, 204));
            headerCell2.setPadding(10f);
            detailsTable.addCell(headerCell2);

            addTableCell(detailsTable, "Premises:", true);
            addTableCell(detailsTable, propertyType + " located at " + propertyAddress, false);
            addTableCell(detailsTable, "Amenities:", true);
            addTableCell(detailsTable, propertyAmenities, false);
            addTableCell(detailsTable, "Move-in Date:", true);
            addTableCell(detailsTable, startDate.format(formatter), false);
            addTableCell(detailsTable, "Move-out Date:", true);
            addTableCell(detailsTable, endDate.format(formatter), false);

            document.add(detailsTable);

            Font sectionFont = new Font(Font.HELVETICA, 12, Font.BOLD, new Color(0, 102, 204));
            Paragraph termsParagraph = new Paragraph("\nAgreement to Lease\n", sectionFont);
            document.add(termsParagraph);
            document.add(new Paragraph("Landlord agrees to lease to Tenant and Tenant agrees to lease from Landlord, " +
                    "according to the terms and conditions set forth herein, the Premises."));

            document.add(new Paragraph("\n"));

            Paragraph termParagraph = new Paragraph("Term", sectionFont);
            document.add(termParagraph);
            document.add(new Paragraph("This Agreement will be for a term beginning on " + startDate.format(formatter) +
                    " and ending on " + endDate.format(formatter) + "."));

            Paragraph footer = new Paragraph("At the end of the Term, a month-to-month holdover tenancy will be created if applicable. If Landlord accepts a " +
                    "rent payment from Tenant, other than past due rent or additional rent, after the Term expires, " +
                    "both parties understand that a month-to-month holdover tenancy will be created at the agreed upon " +
                    "monthly rent, unless proper notice has been served as required by applicable laws. If either Tenant" +
                    "or Landlord wishes to end the month-to-month tenancy, such Party must provide at least " +
                    "thirty (30) days' written notice before the desired termination date.");
            document.add(footer);

            File pdfFile = new File(filePath);
            if (pdfFile.exists()) {
                System.out.println("PDF successfully created under: " + filePath);
            } else {
                System.out.println("Error when creating the PDF under: " + filePath);
            }

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
            if (writer != null) {
                writer.close();
            }
        }

    }

    private void addTableCell(PdfPTable table, String text, boolean isHeader) {
        Font font = new Font(Font.HELVETICA, 12, isHeader ? Font.BOLD : Font.NORMAL);
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setPadding(10f);
        if (isHeader) {
            cell.setBackgroundColor(new Color(0, 102, 204));
            cell.setPhrase(new Phrase(text, new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE)));
        }
        table.addCell(cell);
    }

    private String generateUniqueIdentifier() {
        return UUID.randomUUID().toString();
    }


    public void insertContracts() {
        String sql1 = DBLeaseContractConstants.SELECT_LEASE_CONTRACTS;
        Alert alert;
        String uniqueIdentifier = generateUniqueIdentifier();
        String filePath = "C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract_" + uniqueIdentifier + ".pdf";

        //String filePath = "C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract.txt";

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql1)) {

            if (connect != null) {
                prepare.setString(1, leaseContractsManagement_status.getValue());
                try (ResultSet result = prepare.executeQuery()) {

                    if (result.next()) {
                        showAlert(Alert.AlertType.ERROR, "Error Message", leaseContractsManagement_status.getValue() + " already exists");
                    } else {
                        if (isInputValid()) {
                            LocalDate moveInDate = leaseContractsManagement_moveInDate.getValue();
                            LocalDate moveOutDate = leaseContractsManagement_moveOutDate.getValue();
                            double amount = Double.parseDouble(leaseContractsManagement_amount.getText());

                            if (!isDateAndAmountValid(moveInDate, moveOutDate, amount)) {
                                return;
                            }

                            if (moveOutDate.isBefore(moveInDate.plusYears(3))) {
                                alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Move-out date must be at least 3 years after move-in date! Do you want to continue?");

                                ButtonType continueButton = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
                                ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                                alert.getButtonTypes().setAll(continueButton, cancelButton);

                                Optional<ButtonType> resultChoice = alert.showAndWait();
                                if (resultChoice.isPresent() && resultChoice.get() == continueButton) {
                                    insertLeaseContract(moveInDate, moveOutDate, amount, filePath);
                                }
                            } else {
                                insertLeaseContract(moveInDate, moveOutDate, amount, filePath);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        if (leaseContractsManagement_status.getValue() == null
                || leaseContractsManagement_moveInDate.getValue() == null
                || leaseContractsManagement_moveOutDate.getValue() == null
                || leaseContractsManagement_amount.getText().isEmpty()
                || tenantComboBox.getValue() == null
                || propertyComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error Message", "Please fill all required fields!");
            return false;
        }
        return true;
    }

    private boolean isDateAndAmountValid(LocalDate moveInDate, LocalDate moveOutDate, double amount) {
        LocalDate currentDate = LocalDate.now();

        if (amount < 0) {
            showAlert(Alert.AlertType.ERROR, "Error Message", "Amount must be a positive number!");
            return false;
        }

        if (moveInDate.isBefore(currentDate)) {
            showAlert(Alert.AlertType.ERROR, "Error Message", "Move-in date cannot be in the past!");
            return false;
        }

        if (moveOutDate.isBefore(moveInDate) || moveOutDate.isEqual(moveInDate)) {
            showAlert(Alert.AlertType.ERROR, "Error Message", "Move-out date cannot be at the same time or before move-in date!");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void insertLeaseContract(LocalDate moveInDate, LocalDate moveOutDate, double amount, String filePath) {
        String sql = DBLeaseContractConstants.INSERT_LEASE_CONTRACT;

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            int tenantId = getTenantIdByEmail(tenantComboBox.getValue());
            int propertyId = getPropertyIdByAddress(propertyComboBox.getValue());

            prepare.setInt(1, tenantId);
            prepare.setInt(2, propertyId);
            prepare.setDate(3, java.sql.Date.valueOf(moveInDate));
            prepare.setDate(4, java.sql.Date.valueOf(moveOutDate));
            prepare.setDouble(5, amount);
            prepare.setString(6, leaseContractsManagement_status.getValue());
            prepare.setString(7, filePath);

            prepare.executeUpdate();

            createLeaseContractPdf(moveInDate, moveOutDate, getTenantFullName(tenantComboBox.getValue()),
                    propertyComboBox.getValue(), getPropertyType(propertyId), getPropertyAmenities(propertyId), filePath);

            showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully added!");
//            createLeaseContractPdf(moveInDate,moveOutDate,getTenantName(tenantId),getPropertyAddressById(propertyId),getPropertyType(propertyId),filePath);
            showLeaseContractsList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openPdf(String filePath) {
        if (Desktop.isDesktopSupported()) {
            try {
                File pdfFile = new File(filePath);
                if (pdfFile.exists()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("File not found: " + filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void downloadLeaseContract() {
        if (leaseContractsManagement_tableView.getSelectionModel().getSelectedItem() != null) {
            LeaseContractsData selectedContract = leaseContractsManagement_tableView.getSelectionModel().getSelectedItem();

            String sql = DBLeaseContractConstants.SELECT_CONTRACT;
            String filePath = selectedContract.getContract();

            try (Connection connect = DBConnection.dbConnection();
                 PreparedStatement prepare = connect.prepareStatement(sql)) {

                prepare.setInt(1, selectedContract.getId());

                try (ResultSet result = prepare.executeQuery()) {
                    if (result.next()) {
                        filePath = result.getString("contract");
                    }
                }
            } catch (SQLException e) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        "Error downloading lease contract", e);
                showAlert(Alert.AlertType.ERROR,
                        "Error Message", "Failed to download lease contract.");
                return;
            }

            if (filePath != null && !filePath.isEmpty()) {
                File pdfFile = new File(filePath);
                if (pdfFile.exists()) {
                    openPdf(filePath);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error Message", "File not found: " + filePath);
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error Message", "Contract path not found in the database.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error Message", "No rental agreement selected.");
        }
    }


    public void showLeaseContractsList() {
        ObservableList<LeaseContractsData> listContracts = addContractsList();
        leaseContractsManagement_col_tenant.setCellValueFactory(new PropertyValueFactory<>("tenantName"));
        leaseContractsManagement_col_property.setCellValueFactory(new PropertyValueFactory<>("propertyAddress"));
        leaseContractsManagement_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        leaseContractsManagement_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        leaseContractsManagement_col_paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        leaseContractsManagement_col_rentAmount.setCellValueFactory(new PropertyValueFactory<>("rentAmount"));

        leaseContractsManagement_tableView.setItems(listContracts);
        leaseContractsManagement_tableView.refresh();
    }

    public ObservableList<LeaseContractsData> addContractsList() {
        ObservableList<LeaseContractsData> list = FXCollections.observableArrayList();
        String query = DBLeaseContractConstants.SELECT_ALL_LEASE_CONTRACTS;

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(query);
             ResultSet resultSet = prepare.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int tenantId = resultSet.getInt("tenant_id");
                String tenantName = getTenantName(tenantId);
                int propertyId = resultSet.getInt("property_id");
                String propertyAddress = getPropertyWithContract(propertyId);
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                double rentAmount = resultSet.getDouble("rent_amount");
                String paymentStatus = resultSet.getString("payment_status");
                String contract = resultSet.getString("contract");

                LeaseContractsData leaseContract = new LeaseContractsData(id, tenantId, tenantName, propertyId, propertyAddress, startDate, endDate, rentAmount, paymentStatus, contract);
                list.add(leaseContract);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    /*
    public ObservableList<LeaseContractsData> addContractsList() {
        ObservableList<LeaseContractsData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM lease_contracts";
        connect = DBConnection.dbConnection();

        try {
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                LeaseContractsData leaseD;
                while (result.next()) {
                    leaseD = new LeaseContractsData(
                            result.getInt("id"),
                            result.getInt("tenant_id"),
                            result.getInt("property_id"),
                            result.getDate("start_date"),
                            result.getDate("end_date"),
                            result.getDouble("rent_amount"),
                            result.getString("payment_status"),
                            result.getString("contract")
                    );
                    listData.add(leaseD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


  */

    private String getTenantFullName(String email) {
        String tenantFullName = "";
        String query = DBTenantConstants.SELECT_TENANT_NAME;
        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setString(1, email);
            try (ResultSet resultSet = prepare.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    tenantFullName = firstName + " " + lastName;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching tenant full name", e);
        }
        return tenantFullName;
    }

    private String getTenantName(int id) {
        String tenantFullName = "";
        String query = DBTenantConstants.SELECT_TENANT_NAME_BY_LEASE_CONTRACT;

        try (PreparedStatement prepare = connect.prepareStatement(query)) {
            prepare.setInt(1, id);
            try (ResultSet resultSet = prepare.executeQuery()) {
                if (resultSet.next()) {
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    tenantFullName = firstName + " " + lastName;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching tenant first and last name", e);        }

        return tenantFullName.trim();
    }


    private String getPropertyType(int propertyId) {
        String propertyType = "";
        try {
            String query = DBPropertyConstants.SELECT_TYPE;
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        propertyType = resultSet.getString("type");
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching property type", e);
        }
        return propertyType;
    }

    private String getPropertyAmenities(int propertyId) {
        String propertyAmenities = "";
        try {
            String query = DBPropertyConstants.SELECT_AMENITIES;
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        propertyAmenities = resultSet.getString("amenities");
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching property amenities", e);
        }
        return propertyAmenities;
    }

    private String getPropertyWithContract(int propertyId) {
        String property = "";
        try {
            String query = DBPropertyConstants.SELECT_ADDRESS_BY_LEASE_CONTRACTS;
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        property = resultSet.getString("address");
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching property with lease contract", e);        }
        return property;
    }

    public String getPropertyAddress(int propertyId) {
        String sql = "SELECT address FROM properties WHERE id = ?";
        String propertyAddress = null;

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setInt(1, propertyId);

            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                propertyAddress = resultSet.getString("address");
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching property address", e);        }

        return propertyAddress;
    }

    /*
    private String getPropertyAddress(int propertyId) {
        String property = "";
        try {
            String query = DBPropertyConstants.SELECT_ALL_PROPERTIES;
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        property = resultSet.getString("address");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return property;
    }

     */

    private String getPropertyAddressForPayments(int leaseContractId) {
        String property = "";
        try {
            String query = DBPropertyConstants.SELECT_ADDRESS_BY_PAYMENT_AND_CONTRACT;
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, leaseContractId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        property = resultSet.getString("address");
                    }
                }
            }
        } catch (SQLException e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                "Error fetching property address for payments", e);
        }
        return property;
    }

    public int getContractIdByTenantId(int tenant_id) {
        int lease_contract_id = -1;
        try {
            String query = DBLeaseContractConstants.SELECT_LEASE_CONTRACT_ID_BY_TENANT;
            prepare = connect.prepareStatement(query);
            prepare.setInt(1, tenant_id);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                lease_contract_id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,"Error fetching contract id by tenant id");        }
        return lease_contract_id;
    }

    private int getTenantIdByEmail(String email) {
        int tenantId = -1;
        try {
            String query = DBTenantConstants.SELECT_TENANT_ID;
            prepare = connect.prepareStatement(query);
            prepare.setString(1, email);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                tenantId = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching tenant id by email");
        }
        return tenantId;
    }

    private int getPropertyIdByAddress(String address) {
        int propertyId = -1;
        try {
            String query = DBPropertyConstants.SELECT_PROPERTY_ID;
            prepare = connect.prepareStatement(query);
            prepare.setString(1, address);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                propertyId = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error fetching property id by address");
        }
        return propertyId;
    }


    public ObservableList<String> getTenantsWithoutLeaseContract() {
        ObservableList<String> tenantsList = FXCollections.observableArrayList();
        String query = DBTenantConstants.SELECT_TENANTS_WITHOUT_LEASE_CONTRACTS;

        try (Statement statement = connect.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                tenantsList.add(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error retrieving tenants without lease contracts from database", e);
            return FXCollections.observableArrayList();
        }
        return tenantsList;
    }


    public ObservableList<String> getTenantsWithLeaseContract() {
        ObservableList<String> tenantsList = FXCollections.observableArrayList();
        String query = DBTenantConstants.SELECT_TENANTS_WITH_LEASE_CONTRACTS;

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                tenantsList.add(result.getString("email"));
            }

            //result.close();
            //statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return FXCollections.observableArrayList();
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tenantsList;
    }

    /*
    public ObservableList<Integer> getLeaseContracts() {
        ObservableList<Integer> contractsList = FXCollections.observableArrayList();
        try {
            String query = DBLeaseContractConstants.SELECT_LEASE_CONTRACT_ID;
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                contractsList.add(resultSet.getInt("id"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractsList;
    }
*/
    public ObservableList<LeaseContractsData> getLeaseContracts() {
        ObservableList<LeaseContractsData> contractsList = FXCollections.observableArrayList();
        try {
            String query = "SELECT lc.id, lc.tenant_id, lc.property_id, lc.rent_amount, lc.payment_status, lc.contract, p.address " +
                    "FROM lease_contracts lc " +
                    "JOIN properties p ON lc.property_id = p.id";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                LeaseContractsData contract = new LeaseContractsData();
                contract.setId(resultSet.getInt("id"));
                contract.setTenantId(resultSet.getInt("tenant_id"));
                contract.setPropertyId(resultSet.getInt("property_id"));
                contract.setRentAmount(resultSet.getDouble("rent_amount"));
                contract.setPaymentStatus(resultSet.getString("payment_status"));
                contract.setContract(resultSet.getString("contract"));
                contract.setPropertyAddress(resultSet.getString("address"));

                contractsList.add(contract);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contractsList;
    }

    public void selectTenantsList() {
        if (tenantManagement_tableView.getSelectionModel().getSelectedItem() != null) {
            TenantData selectedTenant = tenantManagement_tableView.getSelectionModel().getSelectedItem();

            tenantManagement_firstName.setText(selectedTenant.getFirstName());
            tenantManagement_lastName.setText(selectedTenant.getLastName());
            tenantManagement_email.setText(selectedTenant.getEmail());
        }
    }

    public ObservableList<TenantData> addTenantsList() {
        ObservableList<TenantData> listData = FXCollections.observableArrayList();
        String sql = DBTenantConstants.SELECT_ALL_TENANTS;
        connect = DBConnection.dbConnection();
        try {
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                TenantData tenantD;
                while (result.next()) {
                    tenantD = new TenantData(
                            result.getInt("id"),
                            result.getString("first_name"),
                            result.getString("last_name"),
                            result.getTimestamp("create_date").toLocalDateTime(),
                            result.getString("email"),
                            result.getString("phone"),
                            result.getString("address"),
                            result.getDate("move_in_date"));
                    listData.add(tenantD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public void updateSelectedTenant() {
        TenantData selectedTenant = tenantManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedTenant == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Tenant Selected");
            alert.setContentText("Please select a tenant to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Tenant");
        dialog.setHeaderText("Edit Tenant Details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(updateButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 200, 10, 10));

        TextField firstNameField = new TextField(selectedTenant.getFirstName());
        TextField lastNameField = new TextField(selectedTenant.getLastName());
        TextField emailField = new TextField(selectedTenant.getEmail());

        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setMinWidth(100);
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setMinWidth(100);
        Label emailLabel = new Label("E-Mail:");
        emailLabel.setMinWidth(100);

        Label firstNameErrorLabel = new Label();
        firstNameErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
        Label lastNameErrorLabel = new Label();
        lastNameErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
        Label emailErrorLabel = new Label();
        emailErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        VBox firstNameBox = new VBox(firstNameField, firstNameErrorLabel);
        VBox lastNameBox = new VBox(lastNameField, lastNameErrorLabel);
        VBox emailBox = new VBox(emailField, emailErrorLabel);

        grid.add(firstNameLabel, 0, 0);
        grid.add(firstNameBox, 1, 0);

        grid.add(lastNameLabel, 0, 1);
        grid.add(lastNameBox, 1, 1);

        grid.add(emailLabel, 0, 2);
        grid.add(emailBox, 1, 2);

        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        Pattern firstNamePattern = Pattern.compile("^[a-zA-Z]+$");
        Pattern lastNamePattern = Pattern.compile("^[a-zA-Z]+$");
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

        ChangeListener<String> fieldListener = (observable, oldValue, newValue) -> {
            boolean firstNameValid = firstNamePattern.matcher(firstNameField.getText().trim()).matches();
            boolean lastNameValid = lastNamePattern.matcher(lastNameField.getText().trim()).matches();
            boolean emailValid = emailPattern.matcher(emailField.getText().trim()).matches();

            boolean allFieldsValid = firstNameValid && lastNameValid && emailValid;

            updateButton.setDisable(!allFieldsValid);

            if (!firstNameValid) {
                firstNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                firstNameErrorLabel.setText("Should contain only letters!");
            } else {
                firstNameField.setStyle(null);
                firstNameErrorLabel.setText("");
            }

            if (!lastNameValid) {
                lastNameField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                lastNameErrorLabel.setText("Should contain only letters!");
            } else {
                lastNameField.setStyle(null);
                lastNameErrorLabel.setText("");
            }

            if (!emailValid) {
                emailField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                emailErrorLabel.setText("Invalid email format!");
            } else {
                emailField.setStyle(null);
                emailErrorLabel.setText("");
            }
        };

        firstNameField.textProperty().addListener(fieldListener);
        lastNameField.textProperty().addListener(fieldListener);
        emailField.textProperty().addListener(fieldListener);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                selectedTenant.setFirstName(firstNameField.getText());
                selectedTenant.setLastName(lastNameField.getText());
                selectedTenant.setEmail(emailField.getText());
                return updateButtonType;
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == updateButtonType) {
                try (Connection connection = DBConnection.dbConnection();
                     PreparedStatement statement = connection.prepareStatement(DBTenantConstants.UPDATE_TENANT_DETAILS)) {
                    statement.setString(1, selectedTenant.getFirstName());
                    statement.setString(2, selectedTenant.getLastName());
                    statement.setString(3, selectedTenant.getEmail());
                    statement.setInt(4, selectedTenant.getId());

                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tenant edited successfully!");
                        successAlert.showAndWait();

                        showTenantsList();
                        tenantManagement_tableView.getSelectionModel().clearSelection();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to edit tenant!");
                        errorAlert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("SQL Error");
                    errorAlert.setHeaderText("Error executing SQL query");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    public void showTenantsList() {
        ObservableList<TenantData> listTenants = addTenantsList();
        tenantManagement_col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tenantManagement_col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tenantManagement_col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tenantManagement_tableView.setItems(listTenants);

        tenantManagement_tableView.refresh();
    }


    public void insertTenants() {
        String email = tenantManagement_email.getText().trim();
        String firstName = tenantManagement_firstName.getText().trim();
        String lastName = tenantManagement_lastName.getText().trim();

        Pattern namePattern = Pattern.compile("^[a-zA-Z]+$");
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

        Alert alert;

        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields!");
            alert.showAndWait();
            return;
        }

        if (!namePattern.matcher(firstName).matches() || !namePattern.matcher(lastName).matches()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("First name and last name should only contain letters!");
            alert.showAndWait();
            return;
        }

        if (!emailPattern.matcher(email).matches()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email format!");
            alert.showAndWait();
            return;
        }

        String sqlCheck = DBTenantConstants.SELECT_TENANTS;
        String sqlInsert = DBTenantConstants.INSERT_TENANT;

        if (connect != null) {
            try (Connection connect = DBConnection.dbConnection();
                 PreparedStatement checkStmt = connect.prepareStatement(sqlCheck);
                 PreparedStatement insertStmt = connect.prepareStatement(sqlInsert)) {

                checkStmt.setString(1, email);
                try (ResultSet result = checkStmt.executeQuery()) {
                    if (result.next()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText(email + " already exists");
                        alert.showAndWait();
                        return;
                    }
                }

                insertStmt.setString(1, firstName);
                insertStmt.setString(2, lastName);
                insertStmt.setString(3, email);
                insertStmt.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully added!");
                alert.showAndWait();

                clearTenantsList();
                showTenantsList();

            } catch (SQLException e) {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Error");
                alert.setHeaderText("Error executing SQL query");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }


    public void clearTenantsList() {
        tenantManagement_firstName.setText("");
        tenantManagement_lastName.setText("");
        tenantManagement_email.setText("");
    }

    public void updateSelectedProperty() {
        PropertyData selectedProperty = propertyManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedProperty == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Property Selected");
            alert.setContentText("Please select a property to edit.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Edit Property");
        dialog.setHeaderText("Edit Property Details");

        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().setAll(updateButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField addressField = new TextField(selectedProperty.getAddress());
        TextField amenitiesField = new TextField(selectedProperty.getAmenities());
        TextField rentField = new TextField(String.valueOf(selectedProperty.getRent()));
        TextField sizeField = new TextField(String.valueOf(selectedProperty.getSize()));
        ComboBox<String> statusComboBox = new ComboBox<>();
        statusComboBox.setItems(FXCollections.observableArrayList(
                "Available", "Rented", "Under Maintenance", "Pending",
                "Sold", "Off Market", "Reserved", "Occupied"));
        statusComboBox.setValue(selectedProperty.getStatus());
        ComboBox<String> typeComboBox = new ComboBox<>();
        typeComboBox.setItems(FXCollections.observableArrayList(
                "House", "Apartment", "Office Building", "Commercial Property",
                "Warehouse", "Land / Lot", "Vacation Home / Holiday Rental",
                "Loft", "Duplex / Maisonette", "Townhouse"));
        typeComboBox.setValue(selectedProperty.getType());

        Button chooseImageButton = new Button("Choose Image");
        chooseImageButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                selectedProperty.setImage(selectedFile.getAbsolutePath());
                System.out.println("Selected image path: " + selectedFile.getAbsolutePath());
            }
        });

        grid.add(new Label("Address:"), 0, 0);
        grid.add(addressField, 1, 0);
        grid.add(new Label("Amenities:"), 0, 1);
        grid.add(amenitiesField, 1, 1);
        grid.add(new Label("Rent:"), 0, 2);
        grid.add(rentField, 1, 2);
        grid.add(new Label("Size:"), 0, 3);
        grid.add(sizeField, 1, 3);
        grid.add(new Label("Status:"), 0, 4);
        grid.add(statusComboBox, 1, 4);
        grid.add(new Label("Type:"), 0, 5);
        grid.add(typeComboBox, 1, 5);
        grid.add(chooseImageButton, 0, 6);

        Node updateButton = dialog.getDialogPane().lookupButton(updateButtonType);
        updateButton.setDisable(true);

        ChangeListener<String> fieldListener = (observable, oldValue, newValue) -> {
            boolean allFieldsFilled = !addressField.getText().trim().isEmpty() &&
                    !amenitiesField.getText().trim().isEmpty() &&
                    !rentField.getText().trim().isEmpty() &&
                    !sizeField.getText().trim().isEmpty();
            updateButton.setDisable(!allFieldsFilled);
        };

        addressField.textProperty().addListener(fieldListener);
        amenitiesField.textProperty().addListener(fieldListener);
        rentField.textProperty().addListener(fieldListener);
        sizeField.textProperty().addListener(fieldListener);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {
                selectedProperty.setAddress(addressField.getText());
                selectedProperty.setAmenities(amenitiesField.getText());
                selectedProperty.setRent(Double.parseDouble(rentField.getText()));
                selectedProperty.setSize(Double.parseDouble(sizeField.getText()));
                selectedProperty.setStatus(statusComboBox.getValue());
                selectedProperty.setType(typeComboBox.getValue());
                return updateButtonType;
            }
            return null;
        });

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> {
            if (buttonType == updateButtonType) {
                try (Connection connection = DBConnection.dbConnection();
                     PreparedStatement statement = connection.prepareStatement(DBPropertyConstants.UPDATE_PROPERTY_DETAILS)) {
                    statement.setString(1, selectedProperty.getStatus());
                    statement.setString(2, selectedProperty.getAddress());
                    statement.setDouble(3, selectedProperty.getSize());
                    statement.setString(4, selectedProperty.getAmenities());
                    statement.setString(5, selectedProperty.getType());
                    statement.setDouble(6, selectedProperty.getRent());
                    statement.setString(7, selectedProperty.getImage());
                    statement.setInt(8, selectedProperty.getId());

                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Property edited successfully!");
                        successAlert.showAndWait();

                        showAddPropertiesList();
                        propertyManagement_tableView.getSelectionModel().clearSelection();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to edit property!");
                        errorAlert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("SQL Error");
                    errorAlert.setHeaderText("Error executing SQL query");
                    errorAlert.setContentText(e.getMessage());
                    errorAlert.showAndWait();
                }
            }
        });
    }

    public void deleteSelectedProperty() {
        PropertyData selectedProperty = propertyManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Delete Property");
            alert.setContentText("Are you sure you want to delete the selected property?");

            ButtonType buttonTypeDelete = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeDelete, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeDelete) {
                try {
                    String sql = DBPropertyConstants.DELETE_PROPERTY_BY_ID;
                    connect = DBConnection.dbConnection();
                    if (connect != null) {
                        prepare = connect.prepareStatement(sql);
                    }
                    prepare.setInt(1, selectedProperty.getId());
                    prepare.executeUpdate();

                    showAddPropertiesList();

                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.setTitle("Information");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Property deleted successfully.");
                    successAlert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Error occurred while deleting property.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert noSelectionAlert = new Alert(Alert.AlertType.WARNING);
            noSelectionAlert.setTitle("Warning");
            noSelectionAlert.setHeaderText(null);
            noSelectionAlert.setContentText("Please select a property to delete!");
            noSelectionAlert.showAndWait();
        }
    }

    private void initializeComboBoxes() {
        ObservableList<String> statusOptions = FXCollections.observableArrayList(
                "Available",
                "Rented",
                "Under Maintenance",
                "Pending",
                "Sold",
                "Off Market",
                "Reserved"
        );
        propertyManagement_status.setItems(statusOptions);

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "House",
                "Apartment",
                "Office Building",
                "Commercial Property",
                "Warehouse",
                "Land / Lot",
                "Vacation Home / Holiday Rental",
                "Loft",
                "Duplex / Maisonette",
                "Townhouse"

        );
        propertyManagement_type.setItems(typeOptions);

        ObservableList<String> statusContracts = FXCollections.observableArrayList(
                "Active",
                "Inactive",
                "Pending",
                "Renewed",
                "Terminated",
                "Cancelled",
                "Overdue",
                "Rejected"
        );
        leaseContractsManagement_status.setItems(statusContracts);

        ObservableList<String> tenantsWithoutContract = FXCollections.observableArrayList(getTenantsWithoutLeaseContract());
        tenantComboBox.setItems(tenantsWithoutContract);

        ObservableList<String> tenantsWithContract = FXCollections.observableArrayList(getTenantsWithLeaseContract());
        payment_tenantComboBox.setItems(tenantsWithContract);

      //  ObservableList<Integer> contracts = FXCollections.observableArrayList(getLeaseContracts());
    //    payment_leaseContractComboBox.setItems(contracts);

        ObservableList<LeaseContractsData> contracts = FXCollections.observableArrayList(getLeaseContracts());
        payment_leaseContractComboBox.setItems(contracts);

        //ObservableList<String> propertiesWithoutContract = FXCollections.observableArrayList(getPropertiesWithoutLeaseContract());
        //propertyComboBox.setItems(propertiesWithoutContract);


        updatePropertyComboBox(leaseContractsManagement_moveInDate.getValue(), leaseContractsManagement_moveOutDate.getValue());
        ObservableList<String> statusMaintenance = FXCollections.observableArrayList(
                "Pending",
                "In Progress",
                "Completed",
                "On Hold",
                "Cancelled"
        );
        maintenanceManagement_status.setItems(statusMaintenance);

        ObservableList<String> priorityMaintenance = FXCollections.observableArrayList(
                "Low",
                "Medium",
                "High",
                "Critical"
        );
        maintenanceManagement_priority.setItems(priorityMaintenance);

        fillPropertyComboBox();

/*
        List<String> excludedStatuses = Arrays.asList("Under Maintenance", "Pending");
        List<PropertyData> propertiesNotUnderMaintenance = PropertyService.getNotUnderMaintenanceProperties(excludedStatuses);

        ObservableList<PropertyData> observableProperties = FXCollections.observableArrayList(propertiesNotUnderMaintenance);
        maintenanceManagement_property.setItems(observableProperties);

 */

    }


    public void updatePropertyComboBox(LocalDate startDate, LocalDate endDate) {
        ObservableList<String> availableProperties = FXCollections.observableArrayList(PropertyService.getAvailableProperties(startDate, endDate));
        propertyComboBox.setItems(availableProperties);
    }

    public void onDateSelected() {
        LocalDate startDate = leaseContractsManagement_moveInDate.getValue();
        LocalDate endDate = leaseContractsManagement_moveOutDate.getValue();

        if (startDate != null || endDate != null) {
            updatePropertyComboBox(startDate, endDate);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select both start and end dates!");
            alert.showAndWait();
        }
    }

    public void selectAddPropertiesList() {
        PropertyData selectedProperty = propertyManagement_tableView.getSelectionModel().getSelectedItem();

        if (selectedProperty != null) {
            propertyManagement_addButton.setVisible(false);

            propertyManagement_address.setText(selectedProperty.getAddress());
            propertyManagement_amenities.setText(selectedProperty.getAmenities());
            propertyManagement_rent.setText(String.valueOf(selectedProperty.getRent()));
            propertyManagement_size.setText(String.valueOf(selectedProperty.getSize()));
            propertyManagement_status.getSelectionModel().select(selectedProperty.getStatus());
            propertyManagement_type.getSelectionModel().select(selectedProperty.getType());

            String uri = "file:" + selectedProperty.getImage();
            Image image = new Image(uri, 105, 145, false, true);
            propertyManagement_imageView.setImage(image);
            propertyManagement_clearButton.setVisible(true);
            propertyManagement_deleteButton.setDisable(false);

        }
    }

    public void insertAddProperties() {
        String address = propertyManagement_address.getText();
        Connection connect = DBConnection.dbConnection();
        Alert alert;

        try {
            if (connect != null) {
                PreparedStatement checkStmt = connect.prepareStatement(DBPropertyConstants.SELECT_PROPERTY_BY_ADDRESS);
                checkStmt.setString(1, address);
                ResultSet result = checkStmt.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(address + " already exists");
                    alert.showAndWait();
                } else {
                    if (address.isEmpty() || propertyManagement_amenities.getText().isEmpty()
                            || propertyManagement_rent.getText().isEmpty()
                            || propertyManagement_size.getText().isEmpty()
                            || propertyManagement_status.getValue() == null
                            || propertyManagement_type.getValue() == null) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Please fill all blank fields!");
                        alert.showAndWait();
                    } else {
                        PreparedStatement insertStmt = connect.prepareStatement(DBPropertyConstants.INSERT_PROPERTY);
                        insertStmt.setString(1, propertyManagement_status.getValue());
                        insertStmt.setString(2, address);
                        insertStmt.setDouble(3, Double.parseDouble(propertyManagement_size.getText()));
                        insertStmt.setString(4, propertyManagement_amenities.getText());
                        insertStmt.setString(5, propertyManagement_type.getValue());
                        insertStmt.setDouble(6, Double.parseDouble(propertyManagement_rent.getText()));
                        insertStmt.setString(7, RealEstateMetrics.path);

                        insertStmt.execute();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully added!");
                        alert.showAndWait();

                        clearAddPropertiesList();
                        showAddPropertiesList();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connect != null) {
                try {
                    connect.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ObservableList<PropertyData> addPropertiesList() {
        ObservableList<PropertyData> listData = FXCollections.observableArrayList();
        String sql = DBPropertyConstants.SELECT_ALL_PROPERTIES;
        connect = DBConnection.dbConnection();

        try {
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                PropertyData propertyD;
                while (result.next()) {
                    propertyD = new PropertyData(
                            result.getInt("id"),
                            result.getString("status"),
                            result.getTimestamp("create_date").toLocalDateTime(),
                            result.getString("address"),
                            result.getDouble("size"),
                            result.getString("amenities"),
                            result.getString("type"),
                            result.getDouble("rent"),
                            result.getString("image")
                    );
                    listData.add(propertyD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public void showAddPropertiesList() {
        listAddProperties = addPropertiesList();

        propertyManagement_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        propertyManagement_col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        propertyManagement_col_size.setCellValueFactory(new PropertyValueFactory<>("size"));
        propertyManagement_col_amenities.setCellValueFactory(new PropertyValueFactory<>("amenities"));
        propertyManagement_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        propertyManagement_col_rent.setCellValueFactory(new PropertyValueFactory<>("rent"));

        propertyManagement_tableView.setItems(listAddProperties);
        propertyManagement_tableView.refresh();
    }


    public void clearAddPropertiesList() {
        propertyManagement_address.setText("");
        propertyManagement_amenities.setText("");
        propertyManagement_rent.setText("");
        propertyManagement_size.setText("");
        propertyManagement_imageView.setImage(null);
        propertyManagement_status.setValue(null);
        propertyManagement_type.setValue(null);

        propertyManagement_addButton.setVisible(true);
        propertyManagement_clearButton.setVisible(false);
        propertyManagement_deleteButton.setDisable(true);
    }

    public void importImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) propertyManagement_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            Image image = new Image(file.toURI().toString(), 105, 145, false, true);
            propertyManagement_imageView.setImage(image);
            RealEstateMetrics.path = file.getAbsolutePath();
        }
    }

    public void switchForm(ActionEvent event) {
        Pane[] forms = {
                dashboard_form, propertyManagement_form, tenantManager_form, maintenanceManagement_form,
                advertisementManagement_form, rentalManagement_form, roleManagement_form
        };

        Button[] buttons = {
                dashboard_button, propertyManagement_button, tenantManagement_button,
                maintenanceManagement_button, advertisementManagement_button, rentalManagement_button, roleManagement_button
        };


        for (Pane form : forms) {
            form.setVisible(false);
        }

        for (Button button : buttons) {
            button.setStyle("-fx-background-color: transparent");
        }

        if (event.getSource() == tenantManagement_button) {
            tenantManager_form.setVisible(true);
            tenantManagement_button.setStyle("-fx-background-color: #0000ff");
        } else if (event.getSource() == dashboard_button) {
            showSelectedForm(dashboard_form, dashboard_button);
        } else if (event.getSource() == propertyManagement_button) {
            showSelectedForm(propertyManagement_form, propertyManagement_button);
        } else if (event.getSource() == maintenanceManagement_button) {
            showSelectedForm(maintenanceManagement_form, maintenanceManagement_button);
        } else if (event.getSource() == advertisementManagement_button) {
            showSelectedForm(advertisementManagement_form, advertisementManagement_button);
        } else if (event.getSource() == rentalManagement_button) {
            showSelectedForm(rentalManagement_form, rentalManagement_button);
           // rentalManagement_button.setDisable(true);
        } else if (event.getSource() == roleManagement_button) {
            showSelectedForm(roleManagement_form, roleManagement_button);
        }
    }


    private void showSelectedForm(Pane form, Button button) {
        form.setVisible(true);
        button.setStyle("-fx-background-color: #0000ff");
    }


    public void logout() {
        logout_button.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/realestatemanagementsystem/sign-in-up.fxml")));
            Stage stage = new Stage();
            StageHelper.configureStage(stage, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayUsername();
        showAddPropertiesList();
        initializeComboBoxes();
        showTenantsList();
        showLeaseContractsList();
        showAddPaymentsList();
        displayTotalSoldProperties();
        displayTotalIncome();
        displayNumberOfAvailableProperties();
        displayChart();
        showMaintenanceList();
        displayPieChart();
        showAdvertisementList();
        File imageDirectory = new File("src/main/java/org/example/image");
        sliderThread = new SliderThread(advertisementManagement_imageView, imageDirectory);
        sliderThread.start();
    }

    public void stopSlider() {
        if (sliderThread != null) {
            sliderThread.stopSlider();
        }
    }
}
