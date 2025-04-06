package theCreepyProperty.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.scenes.GameScene;

public class TutorialMapInfo {
    private GameScene gameScene;

    private Pane infoPaneOver = new Pane();
    private Pane infoPaneUnder = new Pane();

    private ImageView wallBlock;
    private ImageView ghostDistance;
    private ImageView info;

    // Labels and VBox elements initialized
    private Label wallLabel;
    private Label ghostLabel1;
    private Label keyLabel;
    private Label vacuumLable;
    private Label doorLabel;

    private Label keysLabel;
    private Label timeLabel;
    private Label deathsLabel;
    private Label sprintLabel;
    private Label levelLabel;
    private Label buttonLabel1;
    private Label buttonLabel2;
    private Label buttonLabel3;
    private Label buttonLabel4;
    private Label buttonLabel5;

    private Label collidingInfo;

    private VBox wallInfo;
    private VBox ghostInfo;
    private VBox keyInfo;
    private VBox vacuumInfo;
    private VBox doorInfo;

    private VBox infoGuiKeys;
    private VBox infoGuiTime;
    private VBox infoGuiDeaths;
    private VBox infoGuiSprint;
    private VBox infoGuiLevel;
    private VBox infoBarrier;

    private VBox infoButton1;
    private VBox infoButton2;
    private VBox infoButton3;
    private VBox infoButton4;
    private VBox infoButton5;

    public TutorialMapInfo(GameScene gameScene) {
        System.out.println(".............................TutorialMapInfo..............................");
        this.gameScene = gameScene;

        createTutorialInfo();
        startRequirementCheck();
    }

    private void createTutorialInfo() {
        String id = "info";
        String moveId = "move-info";
        // under Overlay
        // Wall info
        this.wallLabel = new Label("This is a wall, ghosts can pass through.");
        this.wallLabel.setId(id);
        this.wallInfo = new VBox(this.wallLabel);
        vBox_position(this.wallInfo, 360, 210);

        this.ghostLabel1 = new Label("""
                ⬆ This is a ghost.
                It can walk through walls.
                If it touches you, you will die.
                When it's near, you hear a heartbeat.
                Stay away!""");
        this.ghostLabel1.setId(id);

        this.ghostInfo = new VBox(this.ghostLabel1);
        this.ghostInfo.setId("tutorial-info-feld-background");
        vBox_position(ghostInfo, 50, 420);

        // Key info
        this.keyLabel = new Label("⬆ This is a key. Collect it to automatically open the door.");
        this.keyLabel.setId(id);
        this.keyInfo = new VBox(this.keyLabel);
        this.keyInfo.setId("tutorial-info-feld-background");
        vBox_position(this.keyInfo, 620, 500);

        // Vacuum info
        this.vacuumLable = new Label("""
                ⬆ This is a Vacuum.
                Collect it to remove one Ghost
                when its in your distance.
                You can vacuum vor 3 sec""");
        this.vacuumLable.setId(id);
        this.vacuumInfo = new VBox(this.vacuumLable);
        this.vacuumInfo.setId("tutorial-info-feld-background");
        vBox_position(this.vacuumInfo,400,500);

        // Door info
        this.doorLabel = new Label("Find the key to open the door. ⬆");
        this.doorLabel.setId(id);
        this.doorInfo = new VBox(this.doorLabel);
        this.doorInfo.setId("tutorial-info-feld-background");
        vBox_position(this.doorInfo, 510, 290);

        this.wallBlock = new ImageView(new Image("file:src/resources/textures/items/Barrier.png"));
        i_position(this.wallBlock, 670,280,60,10);


        // above Overlay
        // Key label info
        this.keysLabel = new Label("⬅ See required keys here.");
        this.keysLabel.setId(id);
        this.infoGuiKeys = new VBox(this.keysLabel);
        this.infoGuiKeys.setId("tutorial-info-feld-background");
        vBox_position(this.infoGuiKeys, 50, 12);

        // Time label info
        this.timeLabel = new Label("⬅ See playtime for this level.");
        this.timeLabel.setId(id);
        this.infoGuiTime = new VBox(this.timeLabel);
        this.infoGuiTime.setId("tutorial-info-feld-background");
        vBox_position(this.infoGuiTime, 135, 63);

        // Deaths label info
        this.deathsLabel = new Label("⬅ See how often you died.");
        this.deathsLabel.setId(id);
        this.infoGuiDeaths = new VBox(this.deathsLabel);
        this.infoGuiDeaths.setId("tutorial-info-feld-background");
        vBox_position(this.infoGuiDeaths, 85, 103);

        // Sprint label info
        this.sprintLabel = new Label("⬅ See how long you can sprint.");
        this.sprintLabel.setId(id);
        this.infoGuiSprint = new VBox(this.sprintLabel);
        this.infoGuiSprint.setId("tutorial-info-feld-background");
        vBox_position(this.infoGuiSprint, 85, 143);

        // Level label info
        this.levelLabel = new Label("⬆ See which level you're playing.");
        this.levelLabel.setId(id);
        this.infoGuiLevel = new VBox(this.levelLabel);
        this.infoGuiLevel.setId("tutorial-info-feld-background");
        vBox_position(this.infoGuiLevel, 400, 62);

        // Button label info
        this.buttonLabel1 = new Label("⬅ Use to Walk");
        this.buttonLabel1.setId(moveId);
        this.infoButton1 = new VBox(this.buttonLabel1);
        vBox_position(this.infoButton1,825,35);

        this.buttonLabel2 = new Label("⬅ Press to Sprint\n     (sprint lasts only a few sec)");
        this.buttonLabel2.setId(moveId);
        this.infoButton2 = new VBox(this.buttonLabel2);
        vBox_position(this.infoButton2,825,70);

        this.buttonLabel3 = new Label("⬅ Press to Sneak\n     (Ghosts have a smaller range)");
        this.buttonLabel3.setId(moveId);
        this.infoButton3 = new VBox(this.buttonLabel3);
        vBox_position(this.infoButton3,825,105);

        this.buttonLabel4 = new Label("⬅ Move the Maus to see More");
        this.buttonLabel4.setId(moveId);
        this.infoButton4 = new VBox(this.buttonLabel4);
        vBox_position(this.infoButton4,825,140);

        this.buttonLabel5 = new Label("⬅ Press to open Menu");
        this.buttonLabel5.setId(moveId);
        this.infoButton5 = new VBox(this.buttonLabel5);
        vBox_position(this.infoButton5,825,165);


        // Collision Lable Info
        this.collidingInfo = new Label("Find the ghost to remove the barrier.");
        this.collidingInfo.setId(id);
        this.infoBarrier = new VBox(this.collidingInfo);
        this.infoBarrier.setId("tutorial-info-feld-background-error");
        vBox_position(this.infoBarrier, 550, 240);
        this.infoBarrier.setVisible(false);

        // Steuerung Image info
        this.info = new ImageView(new Image("file:src/resources/textures/overlay/Info5.png"));
        i_position(info, 730, 5, 300, 200); // 250,150
        this.info.setOpacity(0.7);

        // Ghost-Distance Image info
        this.ghostDistance = new ImageView(new Image("file:src/resources/textures/overlay/ghostDistance.png"));
        i_position(ghostDistance, -105, 195, 400, 400);

        this.infoPaneUnder.getChildren().addAll(this.ghostDistance, this.wallInfo, this.keyInfo, this.vacuumInfo, this.doorInfo, this.ghostInfo, this.wallBlock);
        this.infoPaneOver.getChildren().addAll(this.infoGuiKeys, this.infoGuiTime, this.infoGuiDeaths, this.infoGuiSprint, this.infoGuiLevel, this.info, this.infoButton1, this.infoButton2, this.infoButton3, this.infoButton4, this.infoButton5, this.infoBarrier);

        this.gameScene.pTutorialMapInfoUnderChildren(this.infoPaneUnder);
        this.gameScene.pTutorialMapinfoOverChildren(this.infoPaneOver);

        for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
            ghost.getTimeline().stop(); // Ghost stop moving
            this.gameScene.getMenu().setGhostCanMoves(false);
        }
    }

    // Checking if player is in distance of Player
    public void startRequirementCheck() {

        for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
            // Calculate center of Player
            double entityCenterX = this.gameScene.getPlayer().entity_world_X + this.gameScene.getPlayer().entity_size_X / 2;
            double entityCenterY = this.gameScene.getPlayer().entity_world_Y + this.gameScene.getPlayer().entity_size_Y / 2;

            if(this.gameScene.getPlayer().checkForGhost(ghost, entityCenterX, entityCenterY) <= 100) {
                this.gameScene.getMapCreate().getWallList().get(9).getRWall().setX(-100);
                this.wallBlock.setVisible(false);
            }
        }
    }

    // set Image Position
    private void i_position(ImageView imageView, int x, int y, int width, int height) {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
    }

    // set vBox Position
    private void vBox_position(VBox vBox, int x, int y) {
        vBox.setLayoutX(x);
        vBox.setLayoutY(y);
    }

    // show barrier warning
    public void setCollisionVisible(boolean visible) {
        this.infoBarrier.setVisible(visible);
    }

    // Delete Variables
    public void deleteTutorialMapInfo() {
        System.out.println("⚠ [TutorialMapInfo]: Alle Referenzen werden gelöscht...");

        this.gameScene = null;
        this.infoPaneUnder.getChildren().clear();
        this.infoPaneUnder = null;
        this.infoPaneOver.getChildren().clear();
        this.infoPaneOver = null;

        // ImageView-Variablen auf null setzen
        wallBlock = null;
        ghostDistance = null;
        info = null;

        // Label-Variablen auf null setzen
        wallLabel = null;
        ghostLabel1 = null;
        keyLabel = null;
        vacuumLable = null;
        doorLabel = null;

        keysLabel = null;
        timeLabel = null;
        deathsLabel = null;
        sprintLabel = null;
        levelLabel = null;
        buttonLabel1 = null;
        buttonLabel2 = null;
        buttonLabel3 = null;
        buttonLabel4 = null;
        buttonLabel5 = null;

        collidingInfo = null;

        // VBox-Variablen auf null setzen
        wallInfo = null;
        ghostInfo = null;
        keyInfo = null;
        vacuumInfo = null;
        doorInfo = null;

        infoGuiKeys = null;
        infoGuiTime = null;
        infoGuiDeaths = null;
        infoGuiSprint = null;
        infoGuiLevel = null;
        infoBarrier = null;

        infoButton1 = null;
        infoButton2 = null;
        infoButton3 = null;
        infoButton4 = null;
        infoButton5 = null;

        System.gc();
        System.out.println("✔ [TutorialMapInfo]: Speicherbereinigung durchgeführt.");
    }
}