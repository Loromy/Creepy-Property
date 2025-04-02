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

public class GameWin {
    private GUI gui;
    private GameScene gameScene;
    private LevelSelectScene levelSelectScene;
    private Pane backgroundGameWin = new Pane(); // Background
    private Pane pGameWin = new Pane(); // Menu Items
    private VBox vBoxGameWin = new VBox();
    private SetMap setMap;

    private boolean gameWin_on = false;
    private int mapSelected = 0;

    private Label text;
    private Label time;
    private Button backButton;
    private Button nextButton;

    public GameWin(GUI gui, GameScene gameScene) {
        System.out.println(".............................GameWin..............................");
        this.gui = gui;
        this.gameScene = gameScene;
        this.levelSelectScene = this.gui.getSelectScene();
        setMap = new SetMap(this.gui);

        //overlay
        this.backgroundGameWin.setStyle("-fx-background-color: rgba(0, 255, 0, 0.5);");

        // Add menu items
        this.text = new Label("Congratulations");
        this.text.setId("game-win-text");

        this.time = new Label("Time: --h : --m : --s : --ms || - : Deaths");
        this.time.setId("game-win-text-small");

        this.backButton = new Button("Back");
        this.backButton.setId("game-win");

        this.nextButton = new Button("Next");
        this.nextButton.setId("game-win");

        // Add buttons to the VBox
        this.vBoxGameWin.getChildren().addAll(text, time, nextButton, backButton);
        this.vBoxGameWin.setId("background");
        this.pGameWin.getChildren().add(vBoxGameWin);

        // Set size and position
        this.pGameWin.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundGameWin.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameWinPosition(500, 300, 10);

        // Button actions
        backButton.setOnAction(e -> onBack());
        nextButton.setOnAction(e -> onNext());
    }

    // show Game Winn Overlay
    public void triggerGameWin(){
        if (!gameWin_on) {
            this.gameScene.getPGameWin().setVisible(true);
            this.gameScene.setBlur(15); //Menu blur
            this.gameWin_on = true;
            this.nextButton.requestFocus();
            this.mapSelected = this.levelSelectScene.getMapSelected();
            this.gameScene.getPlayer().stopGhostSound();

            this.levelSelectScene.setThisLevelTime(this.gameScene.getTime_seconds());

            showThisTime();

            for(Ghost ghosts: this.gameScene.getMapCreate().getGhostList()) {
                ghosts.getTimeline().stop();
            }

            int map = this.mapSelected;
            if (++map > 9) {
                this.nextButton.setText("Finish Game");
            }
        } else {
            this.gameScene.getPGameWin().setVisible(false);
            this.gameScene.setBlur(0); //Menu blur
            this.gameWin_on = false;
        }
    }

    // show Time Played in this Level
    private void showThisTime() {
        double time_seconds = this.levelSelectScene.getThisLevelTime();
        // Berechnung der Zeitkomponenten
        int hours = (int) (time_seconds / 3600);
        int minutes = (int) ((time_seconds % 3600) / 60);
        int seconds = (int) (time_seconds % 60);
        int milliseconds = (int) ((time_seconds * 100) % 100); // Millisekunden berechnen

        // Formated Time as HH:MM:SS:mm
        String formattedTime = String.format("%02dh : %02dm : %02ds : %02dms", hours, minutes, seconds, milliseconds);
        this.time.setText("Time: " + formattedTime + " || " + this.levelSelectScene.getThisLevelDeaths() + " : Deaths");
    }

    // set UI Element Position
    private void setGameWinPosition(double width, double height, int spacing) {
        this.vBoxGameWin.setPrefSize(width,height);
        this.vBoxGameWin.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxGameWin.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxGameWin.setSpacing(spacing);
        this.vBoxGameWin.setAlignment(Pos.CENTER);
    }

    // back to level select Scene
    private void onBack() {
        System.out.println("✔ [Game Win]: Back");
        this.gameScene.stopBackgroundMusic();
        this.levelSelectScene.getLevelMenu().getStartButton().setDisable(true);
        this.gui.switchToLevelSelectScene();
    }

    // next level
    private void onNext() {
        this.gameScene.stopBackgroundMusic();

        this.levelSelectScene.setTimePlaying(0);
        this.levelSelectScene.setThisLevelDeaths(0);

        int map = this.mapSelected;
        if (++map > 9) {
            this.gui.switchToFinishScene();
            System.out.println("✔ [GameWin]: onNext switch to Finish Scene");
        } else {
            System.out.println("✔ [Game Win]: Next");
            this.setMap.setMapPlus1();
            this.gui.reloadGameScene();
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

    // Delete Variables
    public void deleteGameWin() {
        System.out.println("⚠ [Game Win]: Alle Referenzen werden gelöscht...");

        if (this.gui != null) {
            this.gui = null;
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        if (this.levelSelectScene != null) {
            this.levelSelectScene = null;
        }

        if (this.setMap != null) {
            this.setMap.deleteSetMap();
            this.setMap = null;
        }

        if (this.text != null) {
            this.text = null;
        }

        if (this.time != null) {
            this.time = null;
        }

        if (this.backButton != null) {
            this.backButton = null;
        }

        if (this.nextButton != null) {
            this.nextButton = null;
        }

        if (this.pGameWin != null) {
            this.pGameWin = null;
        }

        if (this.backgroundGameWin != null) {
            this.backgroundGameWin = null;
        }

        if (this.vBoxGameWin != null) {
            this.vBoxGameWin = null;
        }

        this.gameWin_on = false;
        this.mapSelected = 0;

        System.gc();
        System.out.println("✔ [Game Win]: Speicherbereinigung durchgeführt.");
    }
}