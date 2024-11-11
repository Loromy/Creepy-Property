package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_BLUEPeer;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class KeyHandler {
    private Player player;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean strgPressed = false;

    public KeyHandler(Player player) {
        this.player = player;
    }

    public void addKeyListener(Scene scene) {
        // KeyPressed: Setze Tastenstatus auf "gedrückt"
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPressed = true;
                case S -> sPressed = true;
                case A -> aPressed = true;
                case D -> dPressed = true;
                case CONTROL -> strgPressed = true;
            }
        });

        // KeyReleased: Setze Tastenstatus auf "nicht gedrückt"
        scene.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPressed = false;
                case S -> sPressed = false;
                case A -> aPressed = false;
                case D -> dPressed = false;
                case CONTROL -> strgPressed = false;
            }
        });

        // AnimationTimer für kontinuierliche Abfrage der Tasten
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                handleMovement();
            }
        };
        timer.start();
    }

    // Funktion für die Bewegungssteuerung basierend auf den gedrückten Tasten
    private void handleMovement() {
        double dx = 0;
        double dy = 0;

        if (wPressed) dy -= 1;
        if (sPressed) dy += 1;
        if (aPressed) dx -= 1;
        if (dPressed) dx += 1;

        if (dx != 0 || dy != 0) {
            move(dx, dy);
        }

        if (strgPressed) {
            this.player.setStrgSpeed(2);
            this.player.getPlayer().setFill(Color.LIGHTBLUE);
        } else {
            this.player.setStrgSpeed(0);
            this.player.getPlayer().setFill(Color.DARKRED);
        }

    }

    // Bewegung basierend auf Geschwindigkeits- und Bewegungsrichtung
    private void move(double dx, double dy) {
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        this.player.setX(player.getX() + dx * player.getSpeed());
        this.player.setY(player.getY() + dy * player.getSpeed());
    }
}
