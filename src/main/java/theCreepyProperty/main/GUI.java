package theCreepyProperty.main;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.scenes.CreditScene;
import theCreepyProperty.scenes.LevelSelectScene;
import theCreepyProperty.scenes.StartScene;
import theCreepyProperty.scenes.GameScene;

public class GUI extends Application {

    private String filePath = "";

    private Stage stage;           // Haupt-Stage
    private StartScene startScene; // Start-Szene
    private LevelSelectScene selectScene;
    private GameScene gameScene;   // Spiel-Szene
    private CreditScene creditScene;

    public void start(Stage primaryStage) {
        System.out.println(".............................GUI..............................");
        this.stage = primaryStage;

        // StartScene initialisieren
        this.startScene = new StartScene(this);
        this.selectScene = new LevelSelectScene(this);

        // Default-Scene is StartScene
        stage.setScene(startScene.getScene());
        stage.setTitle("The Creepy Property");
        stage.getIcons().add(new Image(new FileCheck().checkImage("GUI","file:src/resources/textures/icon/icon.png")));
        stage.setResizable(false);
        stage.show();
    }

    // Scene switcher
    public void switchToStartScene() {
        // Create Scene
        if (this.startScene == null) {
            this.startScene = new StartScene(this);
        }

        // Scene Delete
        if (this.gameScene != null) {
            this.gameScene.deleteGameScene();
        }
        // Scene wechseln
        stage.setScene(this.startScene.getScene());

        // Scene zurücksetzen
        this.gameScene = null;
    }

    public void switchToGameScene() {
        // Create Scene
        if (this.gameScene == null) {
            this.gameScene = new GameScene(this);
        }
        // Scene Delete
        if (this.creditScene != null) {
            this.creditScene.deleteFinishScene();
        }

        // Scene wechseln
        stage.setScene(this.gameScene.getScene());

        this.creditScene = null;
    }

    public void switchToLevelSelectScene() {
        // Create Scene
        if (this.selectScene == null) {
            this.selectScene = new LevelSelectScene(this);
        }

        // Scene Delete
        if (this.gameScene != null) {
            this.gameScene.stopTimer();
            this.gameScene.deleteGameScene();
        }
        if (this.creditScene != null) {
            this.creditScene.deleteFinishScene();
        }

        this.selectScene.unlockLevel();

        // Scene wechseln
        stage.setScene(this.selectScene.getScene());

        this.gameScene = null;
        this.creditScene = null;
    }

    public void switchToFinishScene() {
        // Create Scene
        if (this.creditScene == null) {
            this.creditScene = new CreditScene(this);
        }

        // Scene Delete
        if (this.gameScene != null) {
            this.gameScene.stopTimer();
            this.gameScene.deleteGameScene();
        }

        // Scene wechseln
        stage.setScene(this.creditScene.getScene());

        this.gameScene = null;
        this.selectScene = null;
    }

    // reload on Death
    public void reloadGameScene() {
        switchToLevelSelectScene();
        switchToGameScene();
    }

    // Getter Methoden
    public int getWidth() {
        // Breite des Fensters
        return 1000;
    }

    public int getHeight() {
        // Höhe des Fensters
        return 600;
    }

    public StartScene getStartScene() {
        return startScene;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public LevelSelectScene getSelectScene() {
        return this.selectScene;
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    // Setter Methoden
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void startGui(){
        launch();
    }
}
