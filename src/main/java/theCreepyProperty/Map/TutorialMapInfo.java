package theCreepyProperty.Map;

import javafx.scene.layout.Pane;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

import javafx.scene.control.Label;

public class TutorialMapInfo {
    private final GUI gui;
    private final GameScene gameScene;

    private final Pane infoPane = new Pane();
    private Label lInfo1;

    public TutorialMapInfo(GUI gui, GameScene gameScene) {
        this.gui = gui;
        this.gameScene = gameScene;

        createTutorialInfo();
    }

    private void createTutorialInfo() {
        this.lInfo1 = new Label("hallo");

        this.infoPane.getChildren().add(lInfo1);

        this.gameScene.pTutorialMapinfoChildren(this.infoPane);
    }

    public void trigger() {
        System.out.println("halöchen-------------------------");
    }
}
