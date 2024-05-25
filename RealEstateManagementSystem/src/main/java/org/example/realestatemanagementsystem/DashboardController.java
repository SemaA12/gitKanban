package org.example.realestatemanagementsystem;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;


import javafx.scene.control.Alert;

public class DashboardController implements Initializable {
    @FXML
    private Button logout_button;
    @FXML
    private Button dashboard_button;
    @FXML
    private Button propertyManagement_button;
    // @FXML
    //  private Button tenantManagement_button;

    @FXML
    private MenuButton tenantManagement_button;
    @FXML
    private MenuItem tenants_menuItem;
    @FXML
    private MenuItem contracts_menuItem;
    @FXML
    private AnchorPane tenants_form;

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
    private AnchorPane tenantManagement_form;
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
    private Image image;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    @FXML
    private DatePicker leaseContractsManagement_startDate;
    @FXML
    private DatePicker leaseContractsManagement_endDate;

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
    private TableColumn<LeaseContractsData, Double> leaseContractsManagement_col_rentAmount;

    @FXML
    private TableColumn<LeaseContractsData, Date> leaseContractsManagement_col_startDate;

//    @FXML
//    private TextField leaseContractsManagement_amount;

    @FXML
    private DatePicker leaseContractsManagement_moveInDate;
    @FXML
    private DatePicker leaseContractsManagement_moveOutDate;

    @FXML
    private TableView<LeaseContractsData> leaseContractsManagement_tableView;

    @FXML
    private TextField leaseContractsManagement_tenantName;

    @FXML
    private TextField leaseContractsManagement_contactInfo;

    @FXML
    private ComboBox<String> propertyComboBox;

    @FXML
    private ComboBox<String> tenantComboBox;

    @FXML
    private ComboBox<String> leaseContractsManagement_status;

    @FXML
    private AnchorPane contracts_form;


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

    private ObservableList<TenantData> listTenants;


    public void createLeaseContractFile() {
        File newFile = new File("C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract.txt");
        createFile(newFile);

        LocalDate startDate = leaseContractsManagement_moveInDate.getValue();
        LocalDate endDate = leaseContractsManagement_moveOutDate.getValue();
        LocalDate createDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        String tenantName = getTenantFullName((String) tenantComboBox.getValue());
        int propertyId = getPropertyIdByAddress((String) propertyComboBox.getValue());

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
       // String fileContent = readFileContent("C:\\Users\\AliciS\\Desktop\\JAVA WIFI\\InSchulung\\RealEstateManagementSystem\\Lease_Contract.txt"); // Beispiel-Pfad
       // insertContractContentIntoDatabase(fileContent);
    }

    public static void createFile(File newFile) {
        try {
            if (newFile.createNewFile()) {
                System.out.println("Die Datei wurde erstellt, unter dem Namen: " + newFile.getName());
            } else {
                System.out.println("Diese Datei besteht bereits.");
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
            System.out.println("Erfolgreich!");
        } catch (Exception e) {
            System.out.println("Erfolglos!");
            e.printStackTrace();
        }
    }

    private static String repeatTabs(int count) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < count; i++) {
            tabs.append("\t");
        }
        return tabs.toString();
    }

    private static double readDouble(String betrag) {
        try {
            // Ersetze das Komma durch einen Punkt, falls vorhanden
            betrag = betrag.replace(',', '.');
        } catch (NumberFormatException e) {
            System.out.println("Ungültige Eingabe! Geben Sie bitte eine gültige Dezimalzahl ein:");
        }
        return Double.parseDouble(betrag);
    }

    public void showLeaseContractsList() {
        listContracts = addContractsList();
        leaseContractsManagement_col_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        leaseContractsManagement_col_endDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        leaseContractsManagement_col_paymentStatus.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        leaseContractsManagement_col_rentAmount.setCellValueFactory(new PropertyValueFactory<>("rentAmount"));

        leaseContractsManagement_tableView.setItems(listContracts);
        leaseContractsManagement_tableView.refresh();
    }

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
        String sql1 = "SELECT * FROM lease_contracts WHERE payment_status = ?";
        Alert alert;

        try {
            connect = DBConnection.dbConnection();
            prepare = connect.prepareStatement(sql1);
            prepare.setString(1, (String) leaseContractsManagement_status.getValue());
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

                    if (moveOutDate.isBefore(moveInDate.plusYears(3))) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Move-out date must be at least 3 years after move-in date!");
                        alert.showAndWait();
                        return;
                    }

                    int tenantId = getTenantIdByEmail((String) tenantComboBox.getValue());
                    int propertyId = getPropertyIdByAddress((String) propertyComboBox.getValue());

                    String sql = "INSERT INTO lease_contracts (tenant_id, property_id, start_date, end_date, rent_amount, payment_status) VALUES(?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, tenantId);
                    prepare.setInt(2, propertyId);
                    prepare.setDate(3, java.sql.Date.valueOf(moveInDate));
                    prepare.setDate(4, java.sql.Date.valueOf(moveOutDate));
                    prepare.setDouble(5, amount);
                    prepare.setString(6, (String) leaseContractsManagement_status.getValue());

                    prepare.executeUpdate();


                    createLeaseContractFile();


                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();
                    // clearLeaseContractsList();
                    showLeaseContractsList();
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
        listTenants = addTenantsList();
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
        ObservableList<String> tenantsWithoutContract = FXCollections.observableArrayList(getTenantsWithoutLeaseContract());
        tenantComboBox.setItems(tenantsWithoutContract);
        ObservableList<String> propertiesWithoutContract = FXCollections.observableArrayList(getPropertiesWithoutLeaseContract());
        propertyComboBox.setItems(propertiesWithoutContract);

    }


    public void selectAddPropertiesList() {
        if (propertyManagement_tableView.getSelectionModel().getSelectedItem() != null) {
            PropertyData selectedProperty = propertyManagement_tableView.getSelectionModel().getSelectedItem();

            //PropertyData propertyD = (PropertyData) propertyManagement_tableView.getSelectionModel().getSelectedItem();

            propertyManagement_address.setText(selectedProperty.getAddress());
            propertyManagement_amenities.setText(selectedProperty.getAmenities());
            propertyManagement_rent.setText(String.valueOf(selectedProperty.getRent()));
            propertyManagement_size.setText(String.valueOf(selectedProperty.getSize()));
            propertyManagement_status.getSelectionModel().select(selectedProperty.getStatus());
            propertyManagement_type.getSelectionModel().select(selectedProperty.getType());

            String uri = "file:" + selectedProperty.getImage();
            Image image = new Image(uri, 105, 145, false, true);
            propertyManagement_imageView.setImage(image);
        }
    }

    public void insertAddProperties() {
        String sql1 = "SELECT * FROM properties WHERE address = '"
                + propertyManagement_address.getText() + "'";
        connect = DBConnection.dbConnection();

        Alert alert;
        try {
            statement = connect.createStatement();
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
                    prepare.setString(1, (String) propertyManagement_status.getValue());
                    prepare.setString(2, propertyManagement_address.getText());
                    prepare.setDouble(3, Double.parseDouble(propertyManagement_size.getText()));
                    prepare.setString(4, propertyManagement_amenities.getText());
                    prepare.setString(5, (String) propertyManagement_type.getValue());
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

    private ObservableList<PropertyData> listAddProperties;
    private ObservableList<LeaseContractsData> listContracts;

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
    }

    public void importImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage) propertyManagement_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if (file != null) {
            image = new Image(file.toURI().toString(), 105, 145, false, true);
            propertyManagement_imageView.setImage(image);
            GetData.path = file.getAbsolutePath();
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == dashboard_button) {
            dashboard_form.setVisible(true);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);
            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            dashboard_button.setStyle("-fx-background-color: #0000ff");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == propertyManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(true);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);

            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: #0000ff");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == tenantManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(true);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);


            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: #0000ff");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");

            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == maintenanceManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);

            maintenanceManagement_form.setVisible(true);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");

            maintenanceManagement_button.setStyle("-fx-background-color: #0000ff");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == advertisementManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);

            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(true);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");

            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: #0000ff");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == rentalManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);

            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(true);
            roleManagement_form.setVisible(false);


            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: #0000ff");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == roleManagement_button) {
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            tenantManagement_form.setVisible(false);
            tenants_form.setVisible(false);
            contracts_form.setVisible(false);

            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(true);

            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: #0000ff");
        } else if (event.getSource() == tenants_menuItem) {
            tenants_form.setVisible(true);
            contracts_form.setVisible(false);

            tenantManagement_form.setVisible(false);
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            // tenantManagement_form.setVisible(false);
            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            tenants_menuItem.setStyle("-fx-background-color: #0000ff");
            contracts_menuItem.setStyle("-fx-background-color: transparent");
            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == contracts_menuItem) {

            tenants_form.setVisible(false);
            contracts_form.setVisible(true);

            tenantManagement_form.setVisible(false);
            dashboard_form.setVisible(false);
            propertyManagement_form.setVisible(false);
            //tenantManagement_form.setVisible(false);
            maintenanceManagement_form.setVisible(false);
            advertisementManagement_form.setVisible(false);
            rentalManagement_form.setVisible(false);
            roleManagement_form.setVisible(false);

            tenants_menuItem.setStyle("-fx-background-color: transparent");
            contracts_menuItem.setStyle("-fx-background-color: #0000ff");
            dashboard_button.setStyle("-fx-background-color: transparent");
            propertyManagement_button.setStyle("-fx-background-color: transparent");
            tenantManagement_button.setStyle("-fx-background-color: transparent");
            maintenanceManagement_button.setStyle("-fx-background-color: transparent");
            advertisementManagement_button.setStyle("-fx-background-color: transparent");
            rentalManagement_button.setStyle("-fx-background-color: transparent");
            roleManagement_button.setStyle("-fx-background-color: transparent");
        }

    }

    public void logout() {
        logout_button.getScene().getWindow().hide();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
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

    }
}
