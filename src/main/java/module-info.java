module com.example.store_management {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.store_management to javafx.fxml;
    exports com.example.store_management;
}