module org.example.realestatemanagementsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;



    opens org.example.realestatemanagementsystem to javafx.fxml;
    exports org.example.realestatemanagementsystem;
}