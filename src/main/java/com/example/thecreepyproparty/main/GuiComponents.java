package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GuiComponents {
    private Player player;
    private final Label l_speed ;
    private Menu menu;
    private final Button b_menu;

    public GuiComponents(Player player, Menu menu){
        this.player = player;
        this.l_speed = new Label("Speed: " + player.getSpeed());

        this.menu = menu;
        this.b_menu = new Button("Menu");
        this.b_menu.setLayoutX(0);
        this.b_menu.setLayoutY(30);
    }

    public Label getL_speed(){
        return l_speed;
    }

    public Button getB_menu() {return b_menu;}
}
