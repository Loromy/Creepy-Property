module com.example.thecreepyproparty {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thecreepyproparty to javafx.fxml;
    exports com.example.thecreepyproparty;
}