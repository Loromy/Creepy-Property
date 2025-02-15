package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.Map.SetMap;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

public class GameWin extends VBox{
    private GUI gui;
    private final GameScene gameScene;
    private final LevelSelectScene levelSelectScene;
    private final Pane backgroundGameWin = new Pane(); // Background
    private final Pane pGameWin = new Pane(); // Menu Items
    private final VBox vBoxGameWin = new VBox();
    private final SetMap setMap;

    private boolean gameWin_on = false;
    private int mapSelected = 0;

    private final Label text;
    private final Button backButton;
    private final Button nextButton;

    public GameWin(GUI gui, GameScene gameScene) {
        this.gui = gui;
        this.gameScene = gameScene;
        this.levelSelectScene = this.gui.getSelectScene();
        setMap = new SetMap(this.gui);

        //overlay
        this.backgroundGameWin.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");
        this.backgroundGameWin.setVisible(false);
        this.pGameWin.setVisible(false);

        // Add menu items
        this.text = new Label("Congratulations");
        this.text.setId("game-win-text"); // Spezifische ID für den GameOver-Text

        this.backButton = new Button("Back");
        this.backButton.setId("game-win"); // Spezifische ID für den Retry-Button

        this.nextButton = new Button("Next");
        this.nextButton.setId("game-win"); // Spezifische ID für den Retry-Button

        // Add buttons to the VBox
        this.vBoxGameWin.getChildren().addAll(text, nextButton, backButton);
        this.vBoxGameWin.setId("background");
        this.pGameWin.getChildren().add(vBoxGameWin);

        // Set size and position
        this.pGameWin.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundGameWin.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameOverPosition(500, 300, 10);

//        // disable next bei level9
//        //todo finish scene anzeigen
//        if (++this.mapSelected >= 9) {
//            this.nextButton.setText("Finish Game");
//            this.gui.switchToFinishScene();
//        }

        // Button actions
        backButton.setOnAction(e -> onBack());
        nextButton.setOnAction(e -> onNext());
    }

    public void triggerGameWin(){
        if (!gameWin_on) {
            this.backgroundGameWin.setVisible(true);
            this.pGameWin.setVisible(true);
            this.gameScene.setBlur(15); //Menu blur
            this.gameWin_on = true;
            this.nextButton.requestFocus();
            this.mapSelected = this.levelSelectScene.getMapSelected();
            this.levelSelectScene.unlockNextLevel();
            int map = this.mapSelected;
            if (++map > 9) {
                this.nextButton.setText("Finish Game");
            }
        } else {
            this.backgroundGameWin.setVisible(false);
            this.pGameWin.setVisible(false);
            this.gameScene.setBlur(0); //Menu blur
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

    private void onBack() {
        System.out.println("[Game Win]: Back ✔");
        this.gui.switchToLevelSelectScene();
    }

    private void onNext() {
        int map = this.mapSelected;
        if (++map > 9) {
            this.gui.switchToFinishScene();
            System.out.println("[GameWin]: onNext switch to Finish Scene ✔");
        } else {
            System.out.println("[Game Win]: Next ✔");
            this.gui.switchToLevelSelectScene();
            this.setMap.setMapPlus1();
            this.gui.switchToGameScene();
        }
    }

    // Getter Methoden
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
