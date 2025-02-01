package theCreepyProperty.main;

import javafx.scene.Scene;
import javafx.stage.Stage;
import theCreepyProperty.scenes.LevelSelectScene;
import theCreepyProperty.scenes.StartScene;
import theCreepyProperty.scenes.GameScene;

public class GUI {

    private final int width = 1000; // Breite des Fensters
    private final int height = 600; // Höhe des Fensters
    private String filePath = "";

    private Stage stage;           // Haupt-Stage
    private StartScene startScene; // Start-Szene
    private LevelSelectScene selectScene;
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

    public void switchToStartScene() {
        // GameScene initialisieren, falls noch nicht geschehen
        if (this.startScene == null) {
            this.startScene = new StartScene(stage, this);
        }

        // Scene wechseln
        stage.setScene(this.startScene.getScene());
        // Scene zurücksetzen
        this.gameScene = null;
        //this.selectScene = null;
    }

    public void switchToGameScene() {
        // GameScene initialisieren, falls noch nicht geschehen
        if (this.gameScene == null) {
            this.gameScene = new GameScene(stage, this);
        }

        // Scene wechseln
        stage.setScene(this.gameScene.getScene());
        // Scene zurücksetzen
        this.startScene = null;
        //this.selectScene = null;
    }

    public void switchToLevelSelectScene() {
        // GameScene initialisieren, falls noch nicht geschehen
        if (this.selectScene == null) {
            this.selectScene = new LevelSelectScene(stage, this, this.gameScene);
        }

        this.selectScene.unlockLevel();

        // Scene wechseln
        stage.setScene(this.selectScene.getScene());
        // Scene zurücksetzen
        this.startScene = null;
        this.gameScene = null;
    }

    // Getter Methoden
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public LevelSelectScene getSelectScene() {
        return this.selectScene;
    }

    // Setter Methoden
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
