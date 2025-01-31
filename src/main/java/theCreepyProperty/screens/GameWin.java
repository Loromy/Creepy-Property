package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

public class GameWin extends VBox{
    private GUI gui;
    private GameScene scene;
    private final Pane backgroundGameWin = new Pane(); // Background
    private final Pane pGameWin = new Pane(); // Menu Items
    private final VBox vBoxGameWin = new VBox();

    private boolean gameWin_on = false;

    private final Label text;
    private final Button retryButton;
    private final Button quitButton;

    public GameWin(GUI gui, GameScene scene) {
        this.gui = gui;
        this.scene = scene;

        //overlay
        this.backgroundGameWin.setStyle("-fx-background-color: rgba(0, 255, 0, 0.7);");
        this.backgroundGameWin.setVisible(false);
        this.pGameWin.setVisible(false);

        // Add menu items
        this.text = new Label("congratulations");
        this.text.setId("game-win-text"); // Spezifische ID für den GameOver-Text
        this.retryButton = new Button("Back");
        this.retryButton.setId("game-win"); // Spezifische ID für den Retry-Button
        this.quitButton = new Button("Quit");
        this.quitButton.setId("game-win"); // Spezifische ID für den Quit-Button

        // Add buttons to the VBox
        this.vBoxGameWin.getChildren().addAll(text, retryButton, quitButton);
        this.vBoxGameWin.setId("background");
        this.pGameWin.getChildren().add(vBoxGameWin);

        // Set size and position
        this.pGameWin.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundGameWin.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameOverPosition(500, 300, 10);

        // Button actions
        retryButton.setOnAction(e -> onRetry());
        quitButton.setOnAction(e -> onQuit());
    }

    public void triggerGameWin(){
        if (!gameWin_on) {
            this.backgroundGameWin.setVisible(true);
            this.pGameWin.setVisible(true);
            this.scene.setBlur(15); //Menu blur
            this.gameWin_on = true;

            this.retryButton.requestFocus();
        } else {
            this.backgroundGameWin.setVisible(false);
            this.pGameWin.setVisible(false);
            this.scene.setBlur(0); //Menu blur
            this.gameWin_on = false;
        }
    }

    private void setGameOverPosition(double width, double height, int spacing) {
        this.vBoxGameWin.setPrefSize(width,height);
        this.vBoxGameWin.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxGameWin.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxGameWin.setSpacing(spacing);
        this.vBoxGameWin.setAlignment(Pos.CENTER);
    }

    private void onRetry() {
        System.out.println("[Game Over]: Retry");
        this.triggerGameWin();
        this.gui.switchToLevelSelectScene();
    }

    private void onQuit() {
        System.out.println("[Game Over]: Quit");
        System.exit(0);
    }

    public boolean getGameWin_On() {
        return gameWin_on;
    }

    public Pane getPGameWin() {
        return pGameWin;
    }

    public Pane getBackgroundGameWin() {
        return backgroundGameWin;
    }
}
