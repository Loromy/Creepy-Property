module theCreepyProperty.main {
    requires javafx.controls;
    requires javafx.fxml;

    //requires org.controlsfx.controls;

    opens theCreepyProperty.main to javafx.fxml;
    exports theCreepyProperty.main;
}