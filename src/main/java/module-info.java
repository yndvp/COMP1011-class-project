module com.example.week2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.week2 to javafx.fxml;
    exports com.example.week2;
}