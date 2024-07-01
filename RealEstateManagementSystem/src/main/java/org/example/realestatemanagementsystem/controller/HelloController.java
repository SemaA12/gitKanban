package org.example.realestatemanagementsystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.realestatemanagementsystem.data.DBConnection;
import org.example.realestatemanagementsystem.data.RealEstateMetrics;
import org.example.realestatemanagementsystem.logic.HashingPassword;
import org.example.realestatemanagementsystem.logic.InputChecker;
import org.example.realestatemanagementsystem.logic.StageHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {
    @FXML
    private Button login_close;
    @FXML
    private Button login_minimize;

    @FXML
    private AnchorPane login_form1;
    @FXML
    private AnchorPane login_form2;
    @FXML
    private Button signup_close;
    @FXML
    private Button signup_minimize;
    @FXML
    private Hyperlink login_createAccount;
    @FXML
    private Hyperlink signup_accountExists;
    @FXML
    private AnchorPane signup_form1;
    @FXML
    private AnchorPane signup_form2;

    @FXML
    private Button signup;

    @FXML
    private TextField signup_email;
    @FXML
    private PasswordField signup_password;

    @FXML
    private TextField signup_username;


    @FXML
    private TextField login_username;
    @FXML
    private PasswordField login_password;

    @FXML
    private Button login;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public void login_close() {
        System.exit(0);
    }

    public void login_minimize() {
        Stage stage = (Stage) login_form1.getScene().getWindow();
        stage.setIconified(true);
    }

    public void signup_close() {
        System.exit(0);
    }

    public void signup_minimize() {
        Stage stage = (Stage) signup_form1.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    protected void onClickSignUp() {
        String insertQuery = "INSERT INTO users(email, user_name, password) VALUES (?, ?, ?)";
        String checkEmailQuery = "SELECT * FROM users WHERE email = ?";

        connection = DBConnection.dbConnection();
        InputChecker checker = new InputChecker();

        try {
            preparedStatement = connection.prepareStatement(checkEmailQuery);
            preparedStatement.setString(1, signup_email.getText());
            resultSet = preparedStatement.executeQuery();

            Alert alert;

            if (signup_email.getText().isEmpty() || signup_username.getText().isEmpty()
                    || signup_password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields!");
                alert.showAndWait();
            } else if (signup_username.getText().length() < 3 || signup_username.getText().length() > 255 ||
                    !checker.checkingUserName(signup_username.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                if (signup_username.getText().length() < 3 || signup_username.getText().length() > 255) {
                    alert.setContentText("Invalid username length!");
                } else {
                    alert.setContentText("Invalid username format!");
                }
                alert.showAndWait();
            } else if (signup_password.getText().length() < 8 || signup_password.getText().length() > 32 ||
                    !checker.checkingPassWord(signup_password.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);

                if (signup_password.getText().length() < 8 || signup_password.getText().length() > 30) {
                    alert.setContentText("Invalid password length!");
                } else {
                    alert.setContentText("Invalid password format!");
                }
                alert.showAndWait();
            } else if (resultSet.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("The provided email is already in use!");
                alert.showAndWait();
            } else if (!checker.checkingEmail(signup_email.getText())) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message!");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email format!");
                alert.showAndWait();
            } else {
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, signup_email.getText());
                preparedStatement.setString(2, signup_username.getText());
                preparedStatement.setString(3, signup_password.getText());
                preparedStatement.execute();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message!");
                alert.setHeaderText(null);
                alert.setContentText("Your account has been created successfully!");
                alert.showAndWait();

                signup_email.setText("");
                signup_username.setText("");
                signup_password.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClickLogin() {

        String query = "SELECT * FROM users WHERE user_name = ?";
        connection = DBConnection.dbConnection();


        try {
            if (connection != null) {

                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, login_username.getText());
                resultSet = preparedStatement.executeQuery();

                Alert alert;
                if (login_username.getText().isEmpty() || login_password.getText().isEmpty()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message!");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all fields!");
                    alert.showAndWait();
                } else {
                    if (resultSet.next()) {

                        RealEstateMetrics.username = login_username.getText();

                        String hashedPasswordFromDB = resultSet.getString("password");
                        String inputPassword = login_password.getText();
                        if (verifyPassword(inputPassword, hashedPasswordFromDB)) {

                            DashboardController.totalSoldProperties();
                            DashboardController.totalIncome();
                            DashboardController.numberOfAvailableProperties();
                            DashboardController.chart();

                            login.getScene().getWindow().hide();

                            Parent root = FXMLLoader.load(getClass().getResource("/org/example/realestatemanagementsystem/dashboard.fxml"));
                            Stage stage = new Stage();
                            StageHelper.configureStage(stage, root);
                        } else {
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message!");
                            alert.setHeaderText(null);
                            alert.setContentText("The provided username or password is invalid!");
                            alert.showAndWait();
                        }
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message!");
                        alert.setHeaderText(null);
                        alert.setContentText("The provided username does not exist!");
                        alert.showAndWait();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean verifyPassword(String inputPassword, String hashedPasswordFromDB) {
        HashingPassword hashingPassword = new HashingPassword();
        String hashedInputPassword = hashingPassword.doHashing(inputPassword);

        return hashedInputPassword.equals(hashedPasswordFromDB);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == login_createAccount) {
            login_form1.setVisible(false);
            login_form2.setVisible(false);
            signup_form1.setVisible(true);
            signup_form2.setVisible(true);

        } else if (event.getSource() == signup_accountExists) {
            login_form1.setVisible(true);
            login_form2.setVisible(true);
            signup_form1.setVisible(false);
            signup_form2.setVisible(false);
        }
    }
}