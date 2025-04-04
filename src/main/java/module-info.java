module theCreepyProperty.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;

    //requires org.controlsfx.controls;

    opens theCreepyProperty.main to javafx.fxml;
    exports theCreepyProperty.main;

    opens theCreepyProperty.menu to javafx.fxml;
    exports theCreepyProperty.menu;

    exports theCreepyProperty.Map;
    opens theCreepyProperty.Map to javafx.fxml;

    exports theCreepyProperty.scenes;
    opens theCreepyProperty.scenes to javafx.fxml;

    exports theCreepyProperty.blocks;
    opens theCreepyProperty.blocks to javafx.fxml;

    exports theCreepyProperty.entity;
    opens theCreepyProperty.entity to javafx.fxml;

    exports theCreepyProperty.save;
    opens theCreepyProperty.save to javafx.fxml;

    exports theCreepyProperty.screens;
    opens theCreepyProperty.screens to javafx.fxml;
}