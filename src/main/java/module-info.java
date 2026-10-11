module org.example.lodgebilling {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.lodgebilling to javafx.fxml;
    exports org.example.lodgebilling;
}