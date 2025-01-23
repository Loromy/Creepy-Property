package theCreepyProperty.main;

import javafx.stage.Stage;
import theCreepyProperty.scenes.StartScene;
import theCreepyProperty.scenes.GameScene;

public class GUI {

    private final int width = 1000; // Breite des Fensters
    private final int height = 600; // Höhe des Fensters

    private Stage stage;           // Haupt-Stage
    private StartScene startScene; // Start-Szene
    private GameScene gameScene;   // Spiel-Szene

    public void start(Stage primaryStage) {
        this.stage = primaryStage;

        // StartScene initialisieren
        this.startScene = new StartScene(stage, this);

        // Standard-Scene auf StartScene setzen
        stage.setScene(startScene.getScene());
        stage.setTitle("The Creepy Property");
        stage.setResizable(false);
        stage.show();
    }

    public void switchToGameScene() {
        // GameScene initialisieren, falls noch nicht geschehen
        if (this.gameScene == null) {
            this.gameScene = new GameScene(stage, this);
        }

        // Scene wechseln
        stage.setScene(this.gameScene.getScene());
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
