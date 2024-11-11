package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.scene.control.Label;

public class GuiComponents {
    private Player player;
    private Label l_speed ;

    public GuiComponents(Player player){
        this.player = player;
        this.l_speed = new Label("Speed: " + player.getSpeed());
    }

    public Label getL_speed(){
        return l_speed;
    }
}
