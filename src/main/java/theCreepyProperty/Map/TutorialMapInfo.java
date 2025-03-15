package theCreepyProperty.Map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

import javafx.scene.control.Label;

public class TutorialMapInfo {
    private GUI gui;
    private GameScene gameScene;

    private Pane infoPaneOver = new Pane();
    private Pane infoPaneUnder = new Pane();


    public TutorialMapInfo(GUI gui, GameScene gameScene) {
        System.out.println(".............................TutorialMapInfo..............................");
        this.gui = gui;
        this.gameScene = gameScene;

        //this.infoPane.setVisible(false);

        createTutorialInfo();
    }

    private void createTutorialInfo() {
        // under Overlay
        // label info Wall
        String id = "info";
        Label wallLabel = new Label("This is a wall, ghosts can pass through.");
        wallLabel.setId(id);
        VBox wallInfo = new VBox(wallLabel);
        vBox_position(wallInfo,360,210);

        // label info Ghost
        Label ghostLabel1 = new Label("⬆ This is a ghost.");
        Label ghostLabel2 = new Label("It can walks through walls.");
        Label ghostLabel3 = new Label("If it touches you, you will die.");
        Label ghostLabel4 = new Label("When it's near, you hear a heartbeat.");
        Label ghostLabel5 = new Label("Stay away!");

        ghostLabel1.setId(id);
        ghostLabel2.setId(id);
        ghostLabel3.setId(id);
        ghostLabel4.setId(id);
        ghostLabel5.setId(id);

        VBox ghostInfo = new VBox(ghostLabel1, ghostLabel2, ghostLabel3, ghostLabel4, ghostLabel5);
        ghostInfo.setId("tutorial-info-feld-background");
        vBox_position(ghostInfo,50,420);

        // label info Key
        Label keyLabel = new Label("⬆ This is a key. Collect it to automatically open the door.");
        keyLabel.setId(id);
        VBox keyInfo = new VBox(keyLabel);
        keyInfo.setId("tutorial-info-feld-background");
        vBox_position(keyInfo,620,500);

        // label info Door
        Label doorLabel = new Label("Find the key to open the door. ⬆");
        doorLabel.setId(id);
        VBox doorInfo = new VBox(doorLabel);
        doorInfo.setId("tutorial-info-feld-background");
        vBox_position(doorInfo,510,290);

        // above Overlay
        // label key
        Label keysLabel = new Label("⬅ See required keys here.");
        keysLabel.setId(id);
        VBox infoGuiKeys = new VBox(keysLabel);
        infoGuiKeys.setId("tutorial-info-feld-background");
        vBox_position(infoGuiKeys,50,12);

        // label Timer
        Label timeLabel = new Label("⬅ See playtime for this level.");
        timeLabel.setId(id);
        VBox infoGuiTime = new VBox(timeLabel);
        infoGuiTime.setId("tutorial-info-feld-background");
        vBox_position(infoGuiTime,135,63);

        // label Sprint
        Label sprintLabel = new Label("⬅ See how log you can sprint.");
        sprintLabel.setId(id);
        VBox infoGuiSprint = new VBox(sprintLabel);
        infoGuiSprint.setId("tutorial-info-feld-background");
        vBox_position(infoGuiSprint,85,103);

        // label Level
        Label levelLabel = new Label("⬆ See which level you're playing.");
        levelLabel.setId(id);
        VBox infoGuiLevel = new VBox(levelLabel);
        infoGuiLevel.setId("tutorial-info-feld-background");
        vBox_position(infoGuiLevel,400,62);


        ImageView info = new ImageView(new Image("file:src/resources/textures/overlay/InfoSteuerung.png"));
        i_position(info,740,10,250,150);

        ImageView ghostDistance = new ImageView(new Image("file:src/resources/textures/overlay/ghostDistance.png"));
        i_position(ghostDistance,-105,195,400,400);




        this.infoPaneUnder.getChildren().addAll(ghostDistance,wallInfo,keyInfo,doorInfo,ghostInfo);
        this.infoPaneOver.getChildren().addAll(infoGuiKeys,infoGuiTime,infoGuiSprint,infoGuiLevel,info);

        this.gameScene.pTutorialMapInfoUnderChildren(this.infoPaneUnder);
        this.gameScene.pTutorialMapinfoOverChildren(this.infoPaneOver);



        for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
            ghost.getTimeline().stop(); // Ghost stop moving
            this.gameScene.getMenu().setGhostCanMoves(false);
        }
    }

    private void i_position(ImageView imageView, int x, int y, int with, int height) {
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitWidth(with);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
    }

    private void vBox_position(VBox vBox, int x, int y) {
        vBox.setLayoutX(x);
        vBox.setLayoutY(y);
    }

    public void trigger() {
        this.infoPaneOver.setVisible(true);
        System.out.println("✔ [TutorialMapInfo]: tutorial info anzeigen");
    }

    public void deleteTutorialMapInfo() {
        System.out.println("⚠ [TutorialMapInfo]: Alle Referenzen werden gelöscht...");

        // Löschen der Referenzen zu den GUI und GameScene
        if (this.gui != null) {
            this.gui = null;
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        // Löschen der Pane-Referenzen
        if (this.infoPaneUnder != null) {
            this.infoPaneUnder.getChildren().clear();  // Alle untergeordneten Elemente löschen
            this.infoPaneUnder = null;
        }

        if (this.infoPaneOver != null) {
            this.infoPaneOver.getChildren().clear();  // Alle untergeordneten Elemente löschen
            this.infoPaneOver = null;
        }

        // Garbage Collector anstoßen
        System.gc();

        System.out.println("✔ [TutorialMapInfo]: Speicherbereinigung durchgeführt.");
    }

}
