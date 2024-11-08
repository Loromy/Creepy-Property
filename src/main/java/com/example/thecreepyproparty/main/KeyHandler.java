package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import javax.swing.plaf.basic.BasicTableUI;

public class KeyHandler {
    private Player player;
    private KeyHandler keyHandler;

    public KeyHandler(Player player) {
        this.player = player;
    }

    public void addKeyListener(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> move(0, -1);
                case S -> move(0, 1);
                case A -> move(-1, 0);
                case D -> move(1, 0);
                case SPACE -> jump();
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
        }

        this.player.setY(jump);
    }
}
