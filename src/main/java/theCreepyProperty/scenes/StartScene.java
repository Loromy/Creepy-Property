package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import theCreepyProperty.main.GUI;
import theCreepyProperty.screens.StartMenu;

public class StartScene {

    private final Stage stage;
    private final GUI gui;
    private Scene startScene;

    private final StartMenu startMenu;

    public StartScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.startMenu = new StartMenu(this.gui);
        createScene();
    }

    private void createScene() {
        StackPane startScreen = new StackPane();

        startScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        startScreen.getChildren().add(this.startMenu.getBackgroundStartMenu());
        startScreen.getChildren().add(this.startMenu.getpStartMenu());
        this.startScene = new Scene(startScreen, gui.getWidth(), gui.getHeight());

        startMenu.getStartButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Spiel wird gestartet... ✔");
            gui.switchToLevelSelectScene();
        });
        startMenu.getQuitButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Quit ✔");
            System.exit(0);
        });
    }

    // Getter Methode
    public Scene getScene() {
        return this.startScene;
    }
}