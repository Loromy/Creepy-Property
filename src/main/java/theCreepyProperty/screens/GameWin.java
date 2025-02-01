package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

public class GameWin extends VBox{
    private final GUI gui;
    private final GameScene gameScene;
    private final LevelSelectScene levelSelectScene;
    private final Pane backgroundGameWin = new Pane(); // Background
    private final Pane pGameWin = new Pane(); // Menu Items
    private final VBox vBoxGameWin = new VBox();

    private boolean gameWin_on = false;

    private final Label text;
    private final Button backButton;
    private final Button nextButton;

    public GameWin(GUI gui, GameScene gameScene) {
        this.gui = gui;
        this.gameScene = gameScene;
        this.levelSelectScene = this.gui.getSelectScene();

        //overlay
        this.backgroundGameWin.setStyle("-fx-background-color: rgba(0, 255, 0, 0.7);");
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

            this.backButton.requestFocus();

            System.out.println("selected Level________" + this.levelSelectScene.getMapSelected());
            this.levelSelectScene.setLevelCompleted(this.levelSelectScene.getMapSelected(), true);
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

    private void setMap() {
         this.levelSelectScene.setMapSelected(this.levelSelectScene.getMapSelected()); //todo

        int level = this.levelSelectScene.getMapSelected() + 1;
        switch (level) {
            case 1:
                this.gui.setFilePath("src/resources/csv/maps/map1.csv");
                System.out.println("[Level]: 1 selected ✔");
                this.levelSelectScene.setMapSelected(1);
                break;
            case 2:
                this.gui.setFilePath("src/resources/csv/maps/map2.csv");
                System.out.println("[Level]: 2 selected ✔");
                this.levelSelectScene.setMapSelected(2);
                break;
            case 3:
                this.gui.setFilePath("src/resources/csv/maps/map3.csv");
                System.out.println("[Level]: 3 selected ✔");
                this.levelSelectScene.setMapSelected(3);
                break;
            default:
                System.out.println("[Error]: Invalid map selection ✖");
                break;
        }
    }

    private void onBack() {
        System.out.println("[Game Win]: Back ✔");
        this.gui.switchToLevelSelectScene();
    }

    private void onNext() {
        System.out.println("[Game Win]: Next ✔");
        this.gui.switchToLevelSelectScene();
        setMap();
        this.gui.switchToGameScene();
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
