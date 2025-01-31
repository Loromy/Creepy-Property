package theCreepyProperty.main;

import javafx.scene.control.Label;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;

public class GuiComponents {
    private Player player;
    private final Label l_speed ;
    private final Label l_keys;

    private final Menu menu;

    public GuiComponents(Player player, Menu menu){
        this.player = player;

        this.l_speed = new Label("Speed: " + player.getSpeed());
        this.l_speed.setLayoutX(10);
        this.l_speed.setLayoutY(10);

        this.l_keys = new Label("Keys: " + player.getKeyEingesammelt());
        this.l_keys.setLayoutX(10);
        this.l_keys.setLayoutY(30);

        this.menu = menu; //todo menu hier erstellen


    }

    public Label getL_speed(){
        return l_speed;
    }

    public Label getL_keys() {
        return l_keys;
    }
}
