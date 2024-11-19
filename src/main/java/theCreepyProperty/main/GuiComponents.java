package theCreepyProperty.main;

import javafx.scene.control.Label;
import theCreepyProperty.entity.Player;

public class GuiComponents {
    private Player player;
    private final Label l_speed ;
    private Menu menu;

    public GuiComponents(Player player, Menu menu){
        this.player = player;
        this.l_speed = new Label("Speed: " + player.getSpeed());
    }

    public Label getL_speed(){
        return l_speed;
    }
}
