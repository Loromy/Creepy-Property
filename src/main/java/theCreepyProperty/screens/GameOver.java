package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.Map.SetMap;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

public class GameOver {
    private GUI gui;
    private GameScene gameScene;
    private LevelSelectScene levelSelectScene;
    private Pane backgroundGameOver = new Pane(); // Background
    private Pane pGameOver = new Pane(); // Menu Items
    private VBox vBoxGameOver = new VBox();
    private SetMap setMap;

    private boolean gameOver_on = false;
    private int mapSelected = 0;

    private Label text;
    private Button retryButton;
    private Button backButton;

    public GameOver(GUI gui, GameScene gameScene) {
        System.out.println(".............................GameOver..............................");
        this.gui = gui;
        this.gameScene = gameScene;
        this.levelSelectScene = gui.getSelectScene();
        setMap = new SetMap(this.gui);

        //overlay
        this.backgroundGameOver.setStyle("-fx-background-color: rgba(255, 0, 0, 0.3);");
//        this.backgroundGameOver.setVisible(false);
//        this.pGameOver.setVisible(false);

        // Add menu items
        this.text = new Label("Game Over");
        this.text.setId("game-over-text");

        this.retryButton = new Button("Retry");
        this.retryButton.setId("game-over");

        this.backButton = new Button("Back");
        this.backButton.setId("game-over");

        // Add buttons to the VBox
        this.vBoxGameOver.getChildren().addAll(text, retryButton, backButton);
        this.vBoxGameOver.setId("background-over");
        this.pGameOver.getChildren().add(vBoxGameOver);

        // Set size and position
        this.pGameOver.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundGameOver.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameOverPosition(300, 300, 10);

        // Button actions
        retryButton.setOnAction(e -> onRetry());
        backButton.setOnAction(e -> onBack());
    }

    public void triggerGameOver(){
        if (!gameOver_on) {
//            this.backgroundGameOver.setVisible(true);
//            this.pGameOver.setVisible(true);
            this.gameScene.getPGameOver().setVisible(true);
            this.gameScene.setBlur(15); //Menu blur
            this.gameOver_on = true;
            this.retryButton.requestFocus();
            this.mapSelected = this.levelSelectScene.getMapSelected();
            this.gameScene.getPlayer().stopGhostSound();

            for(Ghost ghosts: this.gameScene.getMapCreate().getGhostList()) {
                ghosts.getTimeline().stop();
            }
        } else {
//            this.backgroundGameOver.setVisible(false);
//            this.pGameOver.setVisible(false);
            this.gameScene.getPGameOver().setVisible(false);
            this.gameScene.setBlur(0); //Menu blur
            this.gameOver_on = false;
        }
    }

    private void setGameOverPosition(double width, double height, int spacing) {
        this.vBoxGameOver.setPrefSize(width,height);
        this.vBoxGameOver.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxGameOver.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxGameOver.setSpacing(spacing);
        this.vBoxGameOver.setAlignment(Pos.CENTER);
    }

    private void onRetry() {
        System.out.println("✔ [Game Over]: Retry");

        this.gui.reloadGameScene();
        //this.setMap.setThisMap();

        //this.triggerGameOver();
    }

    private void onBack() {
        System.out.println("✔ [Game Over]: back");

        this.gui.switchToLevelSelectScene();
    }

    // Getter Methoden
    public boolean getGameOver_On() {
        return gameOver_on;
    }

    public Pane getPGameOver() {
        return pGameOver;
    }

    public Pane getBackgroundGameOver() {
        return backgroundGameOver;
    }

    public void deleteGameOver() {
        System.out.println("⚠ [Game Over]: Alle Referenzen werden gelöscht...");

        // GUI Referenz löschen (Wird extern verwaltet)
        if (this.gui != null) {
            this.gui = null;
        }

        // GameScene und LevelSelectScene löschen
        if (this.gameScene != null) {
            this.gameScene = null;
        }

        if (this.levelSelectScene != null) {
            this.levelSelectScene = null;
        }

        // UI-Elemente löschen
        if (this.text != null) {
            this.text = null;
        }

        if (this.retryButton != null) {
            this.retryButton = null;
        }

        if (this.backButton != null) {
            this.backButton = null;
        }

        // Pane-Elemente löschen
        if (this.pGameOver != null) {
            this.pGameOver = null;
        }

        if (this.backgroundGameOver != null) {
            this.backgroundGameOver = null;
        }

        if (this.vBoxGameOver != null) {
            this.vBoxGameOver = null;
        }

        // SetMap löschen
        if (this.setMap != null) {
            this.setMap.deleteSetMap();
            this.setMap = null;
        }

        // Statusvariablen zurücksetzen
        this.gameOver_on = false;
        this.mapSelected = 0;

        // Garbage Collector anstoßen
        System.gc();
        System.out.println("✔ [Game Over]: Speicherbereinigung durchgeführt.");
    }
}
