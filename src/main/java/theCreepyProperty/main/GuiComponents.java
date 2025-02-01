package theCreepyProperty.main;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;

public class GuiComponents {
    private Player player;
    private final Menu menu;

    private final Label l_speed ;
    private final Label l_keys;
    private final Label l_fps;

    public GuiComponents(Player player, Menu menu){
        this.player = player;

        this.l_speed = new Label("Speed: " + player.getSpeed());
        this.l_speed.setLayoutX(10);
        this.l_speed.setLayoutY(30);

        this.l_keys = new Label("Keys: " + player.getKeyEingesammelt());
        this.l_keys.setLayoutX(10);
        this.l_keys.setLayoutY(50);

        this.l_fps = new Label("FPS: 0");
        this.l_fps.setLayoutX(10);
        this.l_fps.setLayoutY(10);

        this.menu = menu; //todo menu hier erstellen


    }

    public void updateFPS(double fps) {
        l_fps.setText("FPS: " + (int) fps);
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
