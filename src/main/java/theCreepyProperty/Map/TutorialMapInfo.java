package theCreepyProperty.Map;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.scene.control.Label;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

public class TutorialMapInfo {
    private GUI gui;
    private GameScene gameScene;

    private Pane infoPaneOver = new Pane();
    private Pane infoPaneUnder = new Pane();

    private ImageView wallBlock;
    private ImageView ghostDistance;
    private ImageView info;

    // Labels and VBox elements initialized
    private Label wallLabel;
    private Label ghostLabel1;
    private Label ghostLabel2;
    private Label ghostLabel3;
    private Label ghostLabel4;
    private Label ghostLabel5;
    private Label keyLabel;
    private Label doorLabel;

    private Label keysLabel;
    private Label timeLabel;
    private Label deathsLabel;
    private Label sprintLabel;
    private Label levelLabel;

    private VBox wallInfo;
    private VBox ghostInfo;
    private VBox keyInfo;
    private VBox doorInfo;

    private VBox infoGuiKeys;
    private VBox infoGuiTime;
    private VBox infoGuiDeaths;
    private VBox infoGuiSprint;
    private VBox infoGuiLevel;


    public TutorialMapInfo(GUI gui, GameScene gameScene) {
        System.out.println(".............................TutorialMapInfo..............................");
        this.gui = gui;
        this.gameScene = gameScene;

        createTutorialInfo();
        startRequirementCheck();
    }

    private void createTutorialInfo() {
        // under Overlay
        // Wall info
        String id = "info";
        wallLabel = new Label("This is a wall, ghosts can pass through.");
        wallLabel.setId(id);
        wallInfo = new VBox(wallLabel);
        vBox_position(wallInfo, 360, 210);

        ghostLabel1 = new Label("⬆ This is a ghost.");
        ghostLabel2 = new Label("It can walk through walls.");
        ghostLabel3 = new Label("If it touches you, you will die.");
        ghostLabel4 = new Label("When it's near, you hear a heartbeat.");
        ghostLabel5 = new Label("Stay away!");
        ghostLabel1.setId(id);
        ghostLabel2.setId(id);
        ghostLabel3.setId(id);
        ghostLabel4.setId(id);
        ghostLabel5.setId(id);

        ghostInfo = new VBox(ghostLabel1, ghostLabel2, ghostLabel3, ghostLabel4, ghostLabel5);
        ghostInfo.setId("tutorial-info-feld-background");
        vBox_position(ghostInfo, 50, 420);

        // Key info
        keyLabel = new Label("⬆ This is a key. Collect it to automatically open the door.");
        keyLabel.setId(id);
        keyInfo = new VBox(keyLabel);
        keyInfo.setId("tutorial-info-feld-background");
        vBox_position(keyInfo, 620, 500);

        // Door info
        doorLabel = new Label("Find the key to open the door. ⬆");
        doorLabel.setId(id);
        doorInfo = new VBox(doorLabel);
        doorInfo.setId("tutorial-info-feld-background");
        vBox_position(doorInfo, 510, 290);

        this.wallBlock = new ImageView(new Image("file:src/resources/textures/items/Barrier.png"));
        i_position(wallBlock, 670,280,60,10);


        // above Overlay
        // Key label info
        keysLabel = new Label("⬅ See required keys here.");
        keysLabel.setId(id);
        infoGuiKeys = new VBox(keysLabel);
        infoGuiKeys.setId("tutorial-info-feld-background");
        vBox_position(infoGuiKeys, 50, 12);

        // Time label info
        timeLabel = new Label("⬅ See playtime for this level.");
        timeLabel.setId(id);
        infoGuiTime = new VBox(timeLabel);
        infoGuiTime.setId("tutorial-info-feld-background");
        vBox_position(infoGuiTime, 135, 63);

        // Deaths label info
        deathsLabel = new Label("⬅ See how often you died.");
        deathsLabel.setId(id);
        infoGuiDeaths = new VBox(deathsLabel);
        infoGuiDeaths.setId("tutorial-info-feld-background");
        vBox_position(infoGuiDeaths, 85, 103);

        // Sprint label info
        sprintLabel = new Label("⬅ See how long you can sprint.");
        sprintLabel.setId(id);
        infoGuiSprint = new VBox(sprintLabel);
        infoGuiSprint.setId("tutorial-info-feld-background");
        vBox_position(infoGuiSprint, 85, 143);

        // Level label info
        levelLabel = new Label("⬆ See which level you're playing.");
        levelLabel.setId(id);
        infoGuiLevel = new VBox(levelLabel);
        infoGuiLevel.setId("tutorial-info-feld-background");
        vBox_position(infoGuiLevel, 400, 62);

        // Steuerung Image info
        this.info = new ImageView(new Image("file:src/resources/textures/overlay/InfoSteuerung.png"));
        i_position(info, 740, 10, 250, 150);

        // Ghost-Distance Image info
        this.ghostDistance = new ImageView(new Image("file:src/resources/textures/overlay/ghostDistance.png"));
        i_position(ghostDistance, -105, 195, 400, 400);

        this.infoPaneUnder.getChildren().addAll(ghostDistance, wallInfo, keyInfo, doorInfo, ghostInfo, wallBlock);
        this.infoPaneOver.getChildren().addAll(infoGuiKeys, infoGuiTime, infoGuiDeaths, infoGuiSprint, infoGuiLevel, info);

        this.gameScene.pTutorialMapInfoUnderChildren(this.infoPaneUnder);
        this.gameScene.pTutorialMapinfoOverChildren(this.infoPaneOver);

        for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
            ghost.getTimeline().stop(); // Ghost stop moving
            this.gameScene.getMenu().setGhostCanMoves(false);
        }
    }

    public void startRequirementCheck() {
        if (this.gui.getSelectScene().getThisLevelDeaths() >= 1) {
            if (this.gameScene.getMapCreate().getWallList().size() > 9) {
                this.gameScene.getMapCreate().getWallList().get(9).getRWall().setX(-100);
                this.wallBlock.setVisible(false);
            }
        }
    }

    private void i_position(ImageView imageView, int x, int y, int width, int height) {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
    }

    private void vBox_position(VBox vBox, int x, int y) {
        vBox.setLayoutX(x);
        vBox.setLayoutY(y);
    }

    public void deleteTutorialMapInfo() {
        System.out.println("⚠ [TutorialMapInfo]: Alle Referenzen werden gelöscht...");

        this.gui = null;
        this.gameScene = null;
        this.infoPaneUnder.getChildren().clear();
        this.infoPaneUnder = null;
        this.infoPaneOver.getChildren().clear();
        this.infoPaneOver = null;

        wallLabel = null;
        ghostLabel1 = null;
        ghostLabel2 = null;
        ghostLabel3 = null;
        ghostLabel4 = null;
        ghostLabel5 = null;
        keyLabel = null;
        doorLabel = null;

        keysLabel = null;
        timeLabel = null;
        deathsLabel = null;
        sprintLabel = null;
        levelLabel = null;

        wallInfo = null;
        ghostInfo = null;
        keyInfo = null;
        doorInfo = null;

        infoGuiKeys = null;
        infoGuiTime = null;
        infoGuiDeaths = null;
        infoGuiSprint = null;
        infoGuiLevel = null;


        System.gc();

        System.out.println("✔ [TutorialMapInfo]: Speicherbereinigung durchgeführt.");
    }
}