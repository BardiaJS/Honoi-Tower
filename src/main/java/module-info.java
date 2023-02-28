module com.example.mainhonoitower {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mainhonoitower to javafx.fxml;
    exports com.example.mainhonoitower;
}