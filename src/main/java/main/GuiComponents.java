package main;


import entity.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
