package org.example.realestatemanagementsystem.controller;

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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;


import javafx.scene.control.Alert;
import org.example.realestatemanagementsystem.data.*;
import org.example.realestatemanagementsystem.logic.StageHelper;

public class DashboardController implements Initializable {
    public Label dashboard_totalSoldTickets;
    public Label dashboard_totalIncome;
    public Label dashboard_availableMovies;
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
    private ComboBox<Integer> payment_leaseContractComboBox;

    @FXML
    private TableColumn<PaymentData, Integer> payment_col_tenant;

    @FXML
    private TableColumn<PaymentData,Integer> payment_col_leaseContracts;
    @FXML
    private TableColumn<PaymentData, Double> payment_col_amount;

    @FXML
    private TableColumn<PaymentData, Date> payment_col_paymentDate;


    @FXML
    private TableView<PaymentData> payment_tableView;
    @FXML
    private Button propertyManagement_clearButton;

    @FXML
    private Button propertyManagement_deleteButton;

    private ObservableList<PropertyData> listAddProperties;


    public void displayUsername() {
        username.setText(GetData.username);
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    public void insertPayments() {
        String sql1 = "SELECT * FROM payments WHERE id = ?";
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

                    String sql = "INSERT INTO payments (tenant_id, lease_contract_id, amount, payment_date) VALUES(?,?,?,?)";
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

    public void showAddPaymentsList(){
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
        String sql = "SELECT * FROM payments";
        try {
            connect = DBConnection.dbConnection();
            PreparedStatement prepare = connect.prepareStatement(sql);
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
            e.printStackTrace();
        }
        return listData;
    }


    /*
    public ObservableList<PaymentData> addPaymentsList() {
        ObservableList<PaymentData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM payments";
        connect = DBConnection.dbConnection();

        try {
            if (connect != null) {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                PaymentData paymentD;
                while (result.next()) {
                    paymentD = new PaymentData(
                            result.getInt("id"),
                            result.getInt("tenant_id"),
                            result.getInt("lease_contract_id"),
                            result.getDouble("amount"),
                            result.getDate("payment_date").toLocalDate());
                    listData.add(paymentD);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

     */


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

    public void createLeaseContractFile() {
        File newFile = new File("C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract.txt");
        createFile(newFile);

        LocalDate startDate = leaseContractsManagement_moveInDate.getValue();
        LocalDate endDate = leaseContractsManagement_moveOutDate.getValue();
        LocalDate createDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String tenantName = getTenantFullName(tenantComboBox.getValue());
        int propertyId = getPropertyIdByAddress(propertyComboBox.getValue());

        String type = getPropertyType(propertyId);
        String amenities = getPropertyAmenities(propertyId);
        String address = getPropertyAddressById(propertyId);


        StringBuilder sb = new StringBuilder();
        sb.append("\t\t\t\t\t\t\tLEASE AGREEMENT\n");
        sb.append("----------------------------------------------------------------------------------\n\n");
        sb.append("This Lease Agreement is made on ").append(createDate.format(formatter)).append(" and between:\n\n");
        sb.append("Landlord: Wiener Wohnen and\n\n");
        sb.append("Tenant: ").append(tenantName).append("\t\t\t\t\t\t\t\t\t.\n\n");
        sb.append("1. Premises: The premises leased is a/an ").append(type).append(" with:\n\n");
        sb.append(amenities).append("\n\n");
        sb.append("located at ").append(address).append("\t\t\t\t\t\t\t\t\t.\n\n");
        sb.append("2. Agreement to Lease. Landlord agrees to lease to Tenant and Tenant agrees to lease from Landlord,\n");
        sb.append("according to the terms and conditions set forth herein, the Premises.\n\n");
        sb.append("3. Term. This Agreement shall be considered a:\n\n");
        sb.append("Fixed Lease. This Agreement will be for a term beginning on ").append(startDate.format(formatter)).append("\n\n");
        sb.append("and ending on ").append(endDate.format(formatter)).append("\t\t\t\t\t\t\t\t\t.\n\n");
        sb.append("----------------------------------------------------------------------------------\n\n");

        sb.append("At the end of the Term: [ A month-to-month holdover tenancy will be created. If Landlord accepts a\n\n");
        sb.append("rent payment from Tenant, other than past due rent or additional rent, after the Term expires,\n\n");
        sb.append("both parties understand that a month-to-month holdover tenancy will be created at the agreed upon\n\n");
        sb.append("monthly rent, unless proper notice has been served as required by applicable laws. If either Tenant\n\n");
        sb.append("or Landlord wishes to end the month-to-month tenancy, such Party must provide at least\n\n");
        sb.append("thirty (30) days' written notice before the desired termination date.\n");
        sb.append("----------------------------------------------------------------------------------\n\n");

        writeInFile(newFile, sb.toString());
        // String fileContent = readFileContent("C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract.txt");
        // insertContractContentIntoDatabase(fileContent);
    }

    public static void createFile(File newFile) {
        try {
            if (newFile.createNewFile()) {
                System.out.println("The file was created under the name: " + newFile.getName());
            } else {
                System.out.println("This file already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeInFile(File fileToRead, String text) {
        try {
            FileWriter fileWriter = new FileWriter(fileToRead);
            fileWriter.write(text);
            fileWriter.close();
            System.out.println("Successful!");
        } catch (Exception e) {
            System.out.println("Unsuccessful!");
            e.printStackTrace();
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
        String query = "SELECT * FROM lease_contracts";

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

                LeaseContractsData leaseContract = new LeaseContractsData(id, tenantId,tenantName,propertyId, propertyAddress, startDate, endDate, rentAmount,paymentStatus,contract);
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
    private String readFileContent(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void insertContracts() {
        String sql1 = "SELECT * FROM lease_contracts WHERE id = ?";
        Alert alert;

        try {
            connect = DBConnection.dbConnection();
            if (connect != null) {
                prepare = connect.prepareStatement(sql1);
            }
            prepare.setString(1, leaseContractsManagement_status.getValue());
            result = prepare.executeQuery();

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(leaseContractsManagement_status.getValue() + " already exists");
                alert.showAndWait();
            } else {
                if (leaseContractsManagement_status.getValue() == null
                        || leaseContractsManagement_moveInDate.getValue() == null
                        || leaseContractsManagement_moveOutDate.getValue() == null
                        || leaseContractsManagement_amount.getText().isEmpty()
                        || tenantComboBox.getValue() == null
                        || propertyComboBox.getValue() == null) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all required fields!");
                    alert.showAndWait();
                } else {
                    LocalDate moveInDate = leaseContractsManagement_moveInDate.getValue();
                    LocalDate moveOutDate = leaseContractsManagement_moveOutDate.getValue();
                    LocalDate currentDate = LocalDate.now();
                    double amount;
                    try {
                        amount = Double.parseDouble(leaseContractsManagement_amount.getText());
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
                    if (moveInDate.isBefore(currentDate)) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Move-in date cannot be in the past!");
                        alert.showAndWait();
                        return;
                    }
                    if (moveOutDate.isBefore(moveInDate)) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Move-out date cannot be at the same time or before move-in date!");
                        alert.showAndWait();
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

                        Optional<ButtonType> result = alert.showAndWait();

                        if (result.isPresent() && result.get() == continueButton) {
                            insertLeaseContract(moveInDate, moveOutDate, amount);
                        }
                    } else {
                        insertLeaseContract(moveInDate, moveOutDate, amount);
                    }
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

    private void insertLeaseContract(LocalDate moveInDate, LocalDate moveOutDate, double amount) {
        try {

            int tenantId = getTenantIdByEmail(tenantComboBox.getValue());
            int propertyId = getPropertyIdByAddress(propertyComboBox.getValue());

            String sql = "INSERT INTO lease_contracts (tenant_id, property_id, start_date, end_date, rent_amount, payment_status) VALUES(?,?,?,?,?,?)";
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, tenantId);
            prepare.setInt(2, propertyId);
            prepare.setDate(3, java.sql.Date.valueOf(moveInDate));
            prepare.setDate(4, java.sql.Date.valueOf(moveOutDate));
            prepare.setDouble(5, amount);
            prepare.setString(6,  leaseContractsManagement_status.getValue());

            prepare.executeUpdate();

            createLeaseContractFile();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully added!");
            alert.showAndWait();
            // clearLeaseContractsList();
            showLeaseContractsList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private String getPropertyAddressById(int propertyId) {
        String propertyAddress = "";
        try {
            String query = "SELECT address FROM properties WHERE id = ?";
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        propertyAddress = resultSet.getString("address");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyAddress;
    }

    private String getTenantFullName(String email) {
        String tenantFullName = "";
        String query = "SELECT first_name, last_name FROM tenants WHERE email = ?";

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
            e.printStackTrace();
        }

        return tenantFullName;
    }
    private String getTenantName(int id) {
        String tenantFullName = "";
        String query = "SELECT first_name, last_name FROM tenants WHERE id IN (SELECT tenant_id FROM lease_contracts WHERE tenant_id = ?)";

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
            e.printStackTrace();
        }

        return tenantFullName.trim();
    }



    private String getPropertyType(int propertyId) {
        String propertyType = "";
        try {
            String query = "SELECT type FROM properties WHERE id = ?";
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        propertyType = resultSet.getString("type");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyType;
    }

    private String getPropertyAmenities(int propertyId) {
        String propertyAmenities = "";
        try {
            String query = "SELECT amenities FROM properties WHERE id = ?";
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, propertyId);
                try (ResultSet resultSet = prepare.executeQuery()) {
                    if (resultSet.next()) {
                        propertyAmenities = resultSet.getString("amenities");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyAmenities;
    }

    private String getPropertyWithContract(int propertyId) {
        String property = "";
        try {
            String query = "SELECT address FROM properties WHERE id IN (SELECT property_id FROM lease_contracts WHERE property_id = ?)";
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

    private String getPropertyAddressForPayments(int leaseContractId) {
        String property = "";
        try {
            String query = "SELECT address \n" +
                    "FROM properties \n" +
                    "WHERE id IN (SELECT property_id FROM lease_contracts WHERE id IN (SELECT lease_contract_id FROM payments WHERE lease_contract_id = ?))\n";
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setInt(1, leaseContractId);
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
    public int getContractIdByTenantId(int tenant_id){
        int lease_contract_id = -1;
        try {
            String query = "SELECT id FROM lease_contracts WHERE tenant_id = ?";
            prepare = connect.prepareStatement(query);
            prepare.setInt(1,tenant_id);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                lease_contract_id = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lease_contract_id;
    }

    private int getTenantIdByEmail(String email) {
        int tenantId = -1;
        try {
            String query = "SELECT id FROM tenants WHERE email = ?";
            prepare = connect.prepareStatement(query);
            prepare.setString(1, email);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                tenantId = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenantId;
    }

    private int getPropertyIdByAddress(String address) {
        int propertyId = -1;
        try {
            String query = "SELECT id FROM properties WHERE address = ?";
            prepare = connect.prepareStatement(query);
            prepare.setString(1, address);
            ResultSet resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                propertyId = resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertyId;
    }
    public ObservableList<String> getAvailableProperties(LocalDate startDate, LocalDate endDate) {
        ObservableList<String> availablePropertiesList = FXCollections.observableArrayList();
        if (startDate == null || endDate == null) {
            return availablePropertiesList;
        }
        String sql = "SELECT address FROM properties p " +
                "WHERE p.id NOT IN (" +
                "SELECT lc.property_id FROM lease_contracts lc " +
                "WHERE (lc.start_date < ? AND lc.end_date > ?))";

        try (Connection connect = DBConnection.dbConnection();
             PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setDate(1, java.sql.Date.valueOf(endDate));
            prepare.setDate(2, java.sql.Date.valueOf(startDate));
            ResultSet resultSet = prepare.executeQuery();

            while (resultSet.next()) {
                availablePropertiesList.add(resultSet.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availablePropertiesList;
    }

    public ObservableList<String> getTenantsWithoutLeaseContract() {
        ObservableList<String> tenantsList = FXCollections.observableArrayList();
        try {
            String query = "SELECT email FROM tenants WHERE id NOT IN (SELECT tenant_id FROM lease_contracts)";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tenantsList.add(resultSet.getString("email"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenantsList;
    }
    public ObservableList<String> getTenantsWithLeaseContract() {
        ObservableList<String> tenantsList = FXCollections.observableArrayList();
        try {
            String query = "SELECT email FROM tenants WHERE id IN (SELECT tenant_id FROM lease_contracts)";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tenantsList.add(resultSet.getString("email"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenantsList;
    }
    public ObservableList<Integer> getLeaseContracts() {
        ObservableList<Integer> contractsList = FXCollections.observableArrayList();
        try {
            String query = "SELECT id FROM lease_contracts";
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
    public ObservableList<String> getPropertiesWithoutLeaseContract() {
        ObservableList<String> propertiesList = FXCollections.observableArrayList();
        try {
            String query = "SELECT address FROM properties WHERE id NOT IN (SELECT property_id FROM lease_contracts)";
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                propertiesList.add(resultSet.getString("address"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return propertiesList;
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
        String sql = "SELECT * FROM tenants";
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

    private List<String> getPropertiesInPeriod(LocalDate moveInDate, LocalDate moveOutDate) {
        List<String> properties = new ArrayList<>();
        try {
            String query = "SELECT address " +
                    "FROM properties " +
                    "WHERE id IN (SELECT property_id " +
                    "FROM lease_contracts " +
                    "WHERE start_date <= ? AND end_date >= ?)";
            try (PreparedStatement prepare = connect.prepareStatement(query)) {
                prepare.setDate(1, Date.valueOf(moveOutDate));
                prepare.setDate(2, Date.valueOf(moveInDate));
                try (ResultSet resultSet = prepare.executeQuery()) {
                    while (resultSet.next()) {
                        properties.add(resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public void updateSelectedTenant() {
        TenantData selectedTenant = tenantManagement_tableView.getSelectionModel().getSelectedItem();
        if (selectedTenant == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Tenant Selected");
            alert.setContentText("Please select a tenant to update.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Tenant");
        dialog.setHeaderText("Update Tenant Details");

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
        firstNameErrorLabel.setTextFill(Color.RED);
        Label lastNameErrorLabel = new Label();
        lastNameErrorLabel.setTextFill(Color.RED);
        Label emailErrorLabel = new Label();
        emailErrorLabel.setTextFill(Color.RED);

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
                     PreparedStatement statement = connection.prepareStatement("UPDATE tenants SET first_name=?, last_name=?, email=? WHERE id=?")) {
                    statement.setString(1, selectedTenant.getFirstName());
                    statement.setString(2, selectedTenant.getLastName());
                    statement.setString(3, selectedTenant.getEmail());
                    statement.setInt(4, selectedTenant.getId());

                    int affectedRows = statement.executeUpdate();
                    if (affectedRows > 0) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Tenant updated successfully!");
                        successAlert.showAndWait();

                        showTenantsList();
                        tenantManagement_tableView.getSelectionModel().clearSelection();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to update tenant!");
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

        String sqlCheck = "SELECT * FROM tenants WHERE email = ?";
        String sqlInsert = "INSERT INTO tenants (first_name, last_name, email) VALUES(?,?,?)";

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
            alert.setContentText("Please select a property to update.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Update Property");
        dialog.setHeaderText("Update Property Details");

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
                     PreparedStatement statement = connection.prepareStatement("UPDATE properties SET status=?, address=?, size=?, amenities=?, type=?, rent=?, image=? WHERE id=?")) {
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
                        successAlert.setContentText("Property updated successfully!");
                        successAlert.showAndWait();

                        showAddPropertiesList();
                        propertyManagement_tableView.getSelectionModel().clearSelection();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to update property!");
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
                    String sql = "DELETE FROM properties WHERE id = ?";
                    connect = DBConnection.dbConnection();
                    prepare = connect.prepareStatement(sql);
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
                "Reserved",
                "Occupied"
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

        //ObservableList<String> tenantsWithoutContract = FXCollections.observableArrayList(getTenantsWithoutLeaseContract());
        //tenantComboBox.setItems(tenantsWithoutContract);
        upTenantComboBox();

        ObservableList<String> tenantsWithContract = FXCollections.observableArrayList(getTenantsWithLeaseContract());
        payment_tenantComboBox.setItems(tenantsWithContract);

       ObservableList<Integer> contracts = FXCollections.observableArrayList(getLeaseContracts());
       payment_leaseContractComboBox.setItems(contracts);



        //ObservableList<String> propertiesWithoutContract = FXCollections.observableArrayList(getPropertiesWithoutLeaseContract());
        //propertyComboBox.setItems(propertiesWithoutContract);


        updatePropertyComboBox(leaseContractsManagement_moveInDate.getValue(),leaseContractsManagement_moveOutDate.getValue());
    }

    public void upTenantComboBox() {
        ObservableList<String> tenantsWithoutContract = FXCollections.observableArrayList(getTenantsWithoutLeaseContract());
        tenantComboBox.setItems(tenantsWithoutContract);
    }
    public void updatePropertyComboBox(LocalDate startDate, LocalDate endDate) {
        ObservableList<String> availableProperties = FXCollections.observableArrayList(getAvailableProperties(startDate, endDate));
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
        PropertyData selectedProperty= propertyManagement_tableView.getSelectionModel().getSelectedItem();

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
        String sql1 = "SELECT * FROM properties WHERE address = '"
                + propertyManagement_address.getText() + "'";
        connect = DBConnection.dbConnection();

        Alert alert;
        try {
            if (connect != null) {
                statement = connect.createStatement();
            }
            result = statement.executeQuery(sql1);
            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(propertyManagement_address.getText() + " already exists");
                alert.showAndWait();
            } else {
                if (propertyManagement_address.getText().isEmpty()
                        || propertyManagement_amenities.getText().isEmpty()
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
                    String sql = "INSERT INTO properties (status, address, size, amenities, type, rent, image) VALUES(?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, propertyManagement_status.getValue());
                    prepare.setString(2, propertyManagement_address.getText());
                    prepare.setDouble(3, Double.parseDouble(propertyManagement_size.getText()));
                    prepare.setString(4, propertyManagement_amenities.getText());
                    prepare.setString(5, propertyManagement_type.getValue());
                    prepare.setDouble(6, Double.parseDouble(propertyManagement_rent.getText()));
                    prepare.setString(7, GetData.path);

                    prepare.execute();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    clearAddPropertiesList();
                    showAddPropertiesList();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<PropertyData> addPropertiesList() {
        ObservableList<PropertyData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM properties";
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
            GetData.path = file.getAbsolutePath();
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
        } else if (event.getSource() == roleManagement_button) {
            showSelectedForm(roleManagement_form, roleManagement_button);
        }
    }

    private void showSelectedForm(Button selectedButton) {
        if (selectedButton == dashboard_button) {
            showSelectedForm(dashboard_form, dashboard_button);
        } else if (selectedButton == propertyManagement_button) {
            showSelectedForm(propertyManagement_form, propertyManagement_button);
        } else if (selectedButton == tenantManagement_button) {
            showSelectedForm(tenantManager_form, tenantManagement_button);
        } else if (selectedButton == maintenanceManagement_button) {
            showSelectedForm(maintenanceManagement_form, maintenanceManagement_button);
        } else if (selectedButton == advertisementManagement_button) {
            showSelectedForm(advertisementManagement_form, advertisementManagement_button);
        } else if (selectedButton == rentalManagement_button) {
            showSelectedForm(rentalManagement_form, rentalManagement_button);
        } else if (selectedButton == roleManagement_button) {
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
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/realestatemanagementsystem/hello-view.fxml")));
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
    }
}
