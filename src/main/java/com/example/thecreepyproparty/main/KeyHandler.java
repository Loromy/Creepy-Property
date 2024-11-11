package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javax.swing.plaf.basic.BasicTableUI;

public class KeyHandler {
    private Player player;
    private KeyHandler keyHandler;
    private boolean wPresst = false;
    private boolean aPresst = false;
    private boolean sPresst = false;
    private boolean dPresst = false;
    private boolean spacePresst = false;


    public KeyHandler(Player player) {
        this.player = player;
    }

    public void addKeyListener(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPresst = true;
                case S -> sPresst = true;
                case A -> aPresst = true;
                case D -> dPresst = true;
                case SPACE -> spacePresst = true;
            }

            if (wPresst && aPresst) {
                move(-1,-1);
                wPresst = false;
                aPresst = false;
            }else if (wPresst && sPresst) {
                move(0,0);
                wPresst = false;
                sPresst = false;
            }else if (wPresst && dPresst) {
                move(1,-1);
                wPresst = false;
                dPresst = false;

            }


            else if (wPresst == true) {
                move(0,-1);
                wPresst = false;
            } else if (aPresst == true) {
                move(-1,0);
                aPresst = false;
            }else if (sPresst == true) {
                move(0,1);
                sPresst = false;
            }else if (dPresst == true) {
                move(1,0);
                dPresst = false;
            }




            else if (spacePresst == true) {

                spacePresst = false;
            }
        });
    }

    private void move(double dx, double dy) {
        this.player.setX(player.getX() + dx * player.getSpeed());
        this.player.setY(player.getY() + dy * player.getSpeed());
    }

    private void jump() {
        double jump = 0;
        for (int i = 0; i < player.getJumpHeight(); i++) {
            jump += player.getY() + 0.1;
            this.player.setY(jump);
        }

        this.player.setY(jump);
    }
}
