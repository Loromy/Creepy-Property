package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyHandler {
    private Player player;
    private static final double MOVE_STEP = 10; // Bewegungsschritt

    public KeyHandler(Player player) {
        this.player = player;
    }

    public void addKeyListener(Scene scene){
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> player.move(0, -1);
                case S -> player.move(0, 1);
                case A -> player.move(-1, 0);
                case D -> player.move(1, 0);
            }
        });
    }
}
