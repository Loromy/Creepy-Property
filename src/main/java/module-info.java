module theCreepyProperty.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    //requires org.controlsfx.controls;

    opens theCreepyProperty.main to javafx.fxml;
    exports theCreepyProperty.main;
    exports theCreepyProperty.menu;
    opens theCreepyProperty.menu to javafx.fxml;
}