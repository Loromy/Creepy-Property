package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.StartScene;

public class GameOver extends VBox{
    private GUI gui;
    private GameScene scene;
    private final Pane backgroundGameOver = new Pane(); // Background
    private final Pane pGameOver = new Pane(); // Menu Items
    private final VBox vBoxGameOver = new VBox();

    private boolean gameOver_on = false;

    private final Label text;
    private final Button retryButton;
    private final Button quitButton;

    public GameOver(GUI gui, GameScene scene) {
        this.gui = gui;
        this.scene = scene;

        //overlay
        this.backgroundGameOver.setStyle("-fx-background-color: rgba(255, 0, 0, 0.7);");
        this.backgroundGameOver.setVisible(false);
        this.pGameOver.setVisible(false);

        // Add menu items
        this.text = new Label("Game Over");
        this.text.setId("game-over-text"); // Spezifische ID für den GameOver-Text
        this.retryButton = new Button("Retry");
        this.retryButton.setId("game-over"); // Spezifische ID für den Retry-Button
        this.quitButton = new Button("Quit");
        this.quitButton.setId("game-over"); // Spezifische ID für den Quit-Button

        // Add buttons to the VBox
        this.vBoxGameOver.getChildren().addAll(text, retryButton, quitButton);
        this.vBoxGameOver.setId("background");
        this.pGameOver.getChildren().add(vBoxGameOver);

        // Set size and position
        this.pGameOver.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundGameOver.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameOverPosition(300, 300, 10);
//        this.setGameOverLayout(100,50);

        // Button actions
        retryButton.setOnAction(e -> onRetry());
        quitButton.setOnAction(e -> onQuit());
    }

    public void triggerGameOver(){
        if (!gameOver_on) {
            this.scene.getpMenu().setVisible(true);
            this.backgroundGameOver.setVisible(true);
            this.pGameOver.setVisible(true);
            this.scene.setBlur(15); //Menu blur
            this.gameOver_on = true;

            this.retryButton.requestFocus();
        } else {
            this.backgroundGameOver.setVisible(false);
            this.pGameOver.setVisible(false);
            this.scene.setBlur(0); //Menu blur
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

//    private void setGameOverLayout(double width, double heigth) {
//        retryButton.setPrefSize(width, heigth);
//    }

    private void onRetry() {
        System.out.println("[Game Over]: Retry ✔");
        this.triggerGameOver();
    }

    private void onQuit() {
        System.out.println("[Game Over]: Quit ✔");
        System.exit(0);
    }

    public boolean getGameOver_On() {
        return gameOver_on;
    }

    public Pane getPGameOver() {
        return pGameOver;
    }

    public Pane getBackgroundGameOver() {
        return backgroundGameOver;
    }
}
