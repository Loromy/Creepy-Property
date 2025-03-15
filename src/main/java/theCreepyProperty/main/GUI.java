package theCreepyProperty.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import theCreepyProperty.checker.ImageCheck;
import theCreepyProperty.menu.Settings;
import theCreepyProperty.scenes.FinishScene;
import theCreepyProperty.scenes.LevelSelectScene;
import theCreepyProperty.scenes.StartScene;
import theCreepyProperty.scenes.GameScene;

import static javafx.application.Application.launch;

public class GUI extends Application {

    private final int width = 1000; // Breite des Fensters
    private final int height = 600; // Höhe des Fensters
    private String filePath = "";

    private final ImageCheck imageCheck = new ImageCheck();

    private Stage stage;           // Haupt-Stage
    private StartScene startScene; // Start-Szene
    private LevelSelectScene selectScene;
    private GameScene gameScene;   // Spiel-Szene
    private FinishScene finishScene;

    public void start(Stage primaryStage) {
        System.out.println(".............................GUI..............................");
        this.stage = primaryStage;

        // StartScene initialisieren
        this.startScene = new StartScene(stage, this);
        this.selectScene = new LevelSelectScene(this);

        // Standard-Scene auf StartScene setzen
        stage.setScene(startScene.getScene());
        stage.setTitle("The Creepy Property");
        stage.getIcons().add(new Image(new ImageCheck().checkImage("GUI","file:src/resources/textures/icon/icon.png")));
        stage.setResizable(false);
        stage.show();
    }

    public void switchToStartScene() {
        if (this.startScene == null) {
            this.startScene = new StartScene(stage, this);
            this.gameScene.deleteGameScene();
        }
        // Scene wechseln
        stage.setScene(this.startScene.getScene());
        this.gameScene = null;
    }

    public void switchToGameScene() {
        if (this.gameScene == null) {
            this.gameScene = new GameScene(stage, this);
        }
        if (this.finishScene != null) {
            this.finishScene.deleteFinishScene();
        }

        // Scene wechseln
        stage.setScene(this.gameScene.getScene());
        // Scene zurücksetzen
        this.finishScene = null;
    }

    public void switchToLevelSelectScene() {

        if (this.selectScene == null) {
            this.selectScene = new LevelSelectScene(this);
        }

        this.selectScene.unlockLevel();
        // Scene wechseln
        stage.setScene(this.selectScene.getScene());
        // Scene zurücksetzen
        if (this.gameScene != null) {
            this.gameScene.stopTimer();
            this.gameScene.deleteGameScene();
        }
        if (this.finishScene != null) {
            this.finishScene.deleteFinishScene();
        }

        this.gameScene = null;
        this.finishScene = null;
    }

    public void switchToFinishScene() {
        if (this.finishScene == null) {
            this.finishScene = new FinishScene(this);
        }
        // Scene wechseln
        stage.setScene(this.finishScene.getScene());
        // Scene zurücksetzen
//        this.startScene = null;
        if (this.gameScene != null) {
            this.gameScene.stopTimer();
            this.gameScene.deleteGameScene();
        }

        this.gameScene = null;
        this.selectScene = null;
    }

    public void reloadGameScene() {
        switchToLevelSelectScene();
        switchToGameScene();
    }

    // Getter Methoden
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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

    public ImageCheck getImageCheck() {
        return imageCheck;
    }

    // Setter Methoden
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static void startGui(){
        launch();
    }
}
