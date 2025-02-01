package theCreepyProperty.main;
import theCreepyProperty.Map.LevelData;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.entity.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameWin;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KeyHandler {
    private final Player player;
    private final GameScene scene;
    private final Menu menu;
    private final GameOver gameOver;
    private final GameWin gameWin;

    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean cPressed = false;
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;
    private boolean escPressed = false;

    public KeyHandler(Player player, GameScene scene, Menu menu, GameOver gameOver, GameWin gameWin) {
        this.player = player;
        this.scene = scene;
        this.menu = menu;
        this.gameOver = gameOver;
        this.gameWin = gameWin;
    }

    public void addKeyListener(Scene scene, GameScene gameScene) {
        // KeyPressed: Setze Tastenstatus auf "gedrückt"
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPressed = true;
                case S -> sPressed = true;
                case A -> aPressed = true;
                case D -> dPressed = true;
                case C -> cPressed = true;
                case CONTROL -> ctrlPressed = true;
                case SHIFT -> shiftPressed = true;
                case ESCAPE -> escPressed = true;
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
                case C -> cPressed = false;
                case CONTROL -> ctrlPressed = false;
                case SHIFT -> shiftPressed = false;
                case ESCAPE -> escPressed = false;
            }
        });

        // **FPS-unabhängige Bewegungsberechnung mit AnimationTimer**
        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = System.nanoTime();
            private long lastFPSUpdate = System.nanoTime();
            private int frameCount = 0;
            private double fps = 0;

            @Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1_000_000_000.0; // Delta-Zeit in Sekunden
                lastTime = now;

                handleMovement(deltaTime); // Bewegung aktualisieren

                // **FPS-Berechnung**
                frameCount++;
                if (now - lastFPSUpdate >= 1_000_000_000) { // Wenn 1 Sekunde vergangen ist
                    fps = frameCount;  // FPS speichern
                    frameCount = 0;  // Frame-Zähler zurücksetzen
                    lastFPSUpdate = now;  // Zeitpunkt der letzten Messung aktualisieren
                    //System.out.println("FPS: " + fps); // FPS ausgeben

                    // FPS in der GUI anzeigen
                    gameScene.getGuiComponents().updateFPS(fps);
                }
            }
        };
        timer.start();
    }

    // Funktion für die Bewegungssteuerung basierend auf den gedrückten Tasten
    private void handleMovement(double deltaTime) {
        if (!this.menu.getMenu_on() && !this.gameOver.getGameOver_On() && !this.gameWin.getGameWin_On()) {
            double dx = 0;
            double dy = 0;

            if (wPressed) {
                dy -= 1;
                this.player.setDirection("up");
            }
            if (sPressed) {
                dy += 1;
                this.player.setDirection("down");
            }
            if (aPressed) {
                dx -= 1;
                this.player.setDirection("left");
            }
            if (dPressed) {
                dx += 1;
                this.player.setDirection("right");
            }

            if (cPressed) {
                System.out.println("Position Player: x=" + this.player.getPlayer_world_X() + " y=" + this.player.getPlayer_world_Y());
                this.cPressed = false;
            }

            if (ctrlPressed) {
                this.player.setControlSpeed(5);
                this.scene.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            } else if (shiftPressed) {
                this.player.setShiftSpeed();
                this.scene.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            } else {
                this.player.setControlSpeed(0);
                this.scene.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            }

            if (dx != 0 || dy != 0) {
                move(dx, dy, deltaTime);
            }
            animation(deltaTime);
        }

        if (escPressed) {
            this.escPressed = false;
            if (!menu.getSettings().getSettingOn()) {
                this.menu.triggerMenu();
            } else {
                this.menu.getSettings().triggerSettings();
                this.menu.getpMenu().setVisible(true);
            }
        }

    }

    // Bewegung basierend auf Geschwindigkeits- und Bewegungsrichtung
    private void move(double dx, double dy, double deltaTime) {
        // Länge des Bewegungsvektors berechnen
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        // FPS-unabhängige Bewegung berechnen
        double speed = player.getSpeed() * deltaTime * 60; // Normale Geschwindigkeit für 60 FPS
        double nextX = player.getPlayer_world_X() + dx * speed;
        double nextY = player.getPlayer_world_Y() + dy * speed;

        // X-Kollision prüfen
        player.collision_on = false;
        this.scene.getChecker().checkCollision(player, nextX, player.getPlayer_world_Y());
        if (!player.getCollision_on()) {
            player.setPlayer_world_X(nextX);
        }

        // Y-Kollision prüfen
        player.collision_on = false;
        this.scene.getChecker().checkCollision(player, player.getPlayer_world_X(), nextY);
        if (!player.getCollision_on()) {
            player.setPlayer_world_Y(nextY);
        }
    }


    private void animation(double deltaTime) {
        if (this.wPressed || this.sPressed || this.aPressed || this.dPressed) {
            //player.sprite_counter++;
            player.sprite_counter += deltaTime * 60;

            // Sprite-Wechsel abhängig von der Spieler-Geschwindigkeit
            int frameSpeed = Math.max(4, 14 - (int) player.getSpeed());

            if (player.sprite_counter > frameSpeed) {
                player.sprite_num = (player.sprite_num % 4) + 1; // Zyklus: 1 → 2 → 3 → 4 → 1
                player.sprite_counter = 0;
            }
        }
    }

}
