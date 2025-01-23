package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import theCreepyProperty.main.GUI;
import theCreepyProperty.screens.StartMenue;

public class StartScene {

    private final Stage stage;  // Referenz zur Haupt-Stage
    private final GUI gui;      // Referenz zur GUI-Klasse
    private Scene startScene;        // Szene der Startseite

    // Start Scene Classes
    private final StartMenue startMenue;

    public StartScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.startMenue = new StartMenue(this.gui);
        createScene();
    }

    private void createScene() {
//        StackPane root = new StackPane();
//
//        // Buttons erstellen
//        Button startButton = new Button("Start Game");
//        Button quitButton = new Button("Quit");
//
//        // Aktionen für die Buttons
//        startButton.setOnAction(e -> {
//            System.out.println("[StartScene]: Wechsel zu GameScene");
//            gui.switchToGameScene();
//        });
//
//        quitButton.setOnAction(e -> {
//            System.out.println("[StartScene]: Anwendung wird geschlossen");
//            System.exit(0);
//        });
//
//        // Buttons zum Layout hinzufügen
//        root.getChildren().addAll(startButton, quitButton);
//
//        // Szene initialisieren
//        this.scene = new Scene(root, gui.getWidth(), gui.getHeight());

        // Start Screen
        StackPane startScreen = new StackPane();

        startScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        startScreen.getChildren().add(this.startMenue.getBackgroundStartMenu());
        startScreen.getChildren().add(this.startMenue.getpStartMenu());
        this.startScene = new Scene(startScreen, gui.getWidth(), gui.getHeight());

        startMenue.getStartButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Spiel wird gestartet...");
            gui.switchToGameScene();
        });
        startMenue.getQuitButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Quit");
            System.exit(0);
        });
    }

    public Scene getScene() {
        return this.startScene;
    }
}
