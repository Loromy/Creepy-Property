package theCreepyProperty.main;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.scenes.LevelSelectScene;

public class GuiComponents {
    private final Player player;
    private final GUI gui;
    private final LevelSelectScene levelSelectScene;

    private final Label l_speed ;
    private final Label l_keys;
    private final Label l_fps;
    private final Label l_level;

    public GuiComponents(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
        this.levelSelectScene = gui.getSelectScene();

        this.l_level = new Label("Level " + this.levelSelectScene.getMapSelected());
        this.l_level.setLayoutX((double) gui.getWidth() / 2);
        this.l_level.setLayoutY(10);
        this.l_level.setScaleX(2);
        this.l_level.setScaleY(2);
        this.l_level.setId("gui-components");


        this.l_speed = new Label("Speed: " + player.getSpeed());
        this.l_speed.setLayoutX(10);
        this.l_speed.setLayoutY(30);
        this.l_speed.setId("gui-components");

        this.l_keys = new Label("Keys: " + player.getKeyEingesammelt());
        this.l_keys.setLayoutX(10);
        this.l_keys.setLayoutY(50);
        this.l_keys.setId("gui-components");

        this.l_fps = new Label("FPS: 0");
        this.l_fps.setLayoutX(10);
        this.l_fps.setLayoutY(10);
        this.l_fps.setId("gui-components");
    }

    public void updateFPS(double fps) {
        l_fps.setText("FPS: " + (int) fps);
    }

    public Label getL_level() {
        return l_level;
    }

    public Label getL_speed(){
        return l_speed;
    }

    public Label getL_keys() {
        return l_keys;
    }

    public Label getL_fps() {
        return  l_fps;
    }
}
