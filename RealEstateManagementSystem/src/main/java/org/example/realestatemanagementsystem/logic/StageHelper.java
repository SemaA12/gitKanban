package org.example.realestatemanagementsystem.logic;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.realestatemanagementsystem.data.Position;

public class StageHelper {
    public static void configureStage(Stage stage, Parent root) {

        Scene scene = new Scene(root);
        Position position = new Position();

        root.setOnMousePressed(event -> {
            position.setX(event.getSceneX());
            position.setY(event.getSceneY());
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - position.getX());
            stage.setY(event.getScreenY() - position.getY());
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
