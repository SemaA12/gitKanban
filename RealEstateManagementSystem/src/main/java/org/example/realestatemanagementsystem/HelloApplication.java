package org.example.realestatemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        URL resourceUrl = getClass().getResource("hello-view.fxml");
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

