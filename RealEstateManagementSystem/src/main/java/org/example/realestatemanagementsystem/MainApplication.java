package org.example.realestatemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.example.realestatemanagementsystem.logic.StageHelper;

import java.io.IOException;
import java.net.URL;


public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL resourceUrl = getClass().getResource("/org/example/realestatemanagementsystem/sign-in-up.fxml");
        if (resourceUrl != null) {
            Parent root = FXMLLoader.load(resourceUrl);
            StageHelper.configureStage(stage, root);
        } else {
            System.err.println("The FXML file couldn't be found!");
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

