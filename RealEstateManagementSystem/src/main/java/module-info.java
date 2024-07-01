module org.example.realestatemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires itext;
    requires java.desktop;

    opens org.example.realestatemanagementsystem to javafx.fxml;
    exports org.example.realestatemanagementsystem;
    exports org.example.realestatemanagementsystem.controller;
    opens org.example.realestatemanagementsystem.controller to javafx.fxml;
    exports org.example.realestatemanagementsystem.data;
    opens org.example.realestatemanagementsystem.data to javafx.fxml;
    exports org.example.realestatemanagementsystem.logic;
    opens org.example.realestatemanagementsystem.logic to javafx.fxml;

}