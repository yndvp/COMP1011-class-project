module com.example.week2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.week2 to javafx.fxml;
    exports com.example.week2;
    exports com.example.week2.models;
    opens com.example.week2.models to javafx.fxml;
    exports com.example.week2.controllers;
    opens com.example.week2.controllers to javafx.fxml;
}