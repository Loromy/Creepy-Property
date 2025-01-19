package theCreepyProperty.main;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.entity.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import theCreepyProperty.menu.Menu;

public class KeyHandler {
    private final Player player;
    private final GUI gui;
    private final Menu menu;
    private final GameOver gameOver;
    public boolean wPressed = false;
    public boolean aPressed = false;
    public boolean sPressed = false;
    public boolean dPressed = false;
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;
    private boolean escPressed = false;

    public KeyHandler(Player player, GUI gui, Menu menu, GameOver gameOver) {
        this.player = player;
        this.gui = gui;
        this.menu = menu;
        this.gameOver = gameOver;
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
                case CONTROL -> ctrlPressed = false;
                case SHIFT -> shiftPressed = false;
                case ESCAPE -> escPressed = false;
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
        if (!this.menu.getMenu_on() && !this.gameOver.getGameOver_On()) {
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

            if (ctrlPressed) {
                this.player.setControlSpeed(2);
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            } else if (shiftPressed) {
                this.player.setShiftSpeed();
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            } else {
                this.player.setControlSpeed(0);
                this.gui.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            }

            if (dx != 0 || dy != 0) {
                move(dx, dy);
            }
            animation();
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
    private void move(double dx, double dy) {

        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        //CHECK LevelDataWall COLLISION
        this.player.collision_on = false;
        //gui.getChecker().checkTile(this.player); //TODO bearbeiten

        if(!this.player.getCollision_on()) {
            this.player.setPlayer_world_X(player.getPlayer_world_X() + dx * player.getSpeed());
            this.player.setPlayer_world_Y(player.getPlayer_world_Y() + dy * player.getSpeed());
        }
    }


    private void animation(){
        if (this.wPressed || this.sPressed || this.aPressed || this.dPressed) {

            player.sprite_counter++;
            if (player.sprite_counter > 13) {

                if (player.sprite_num == 1) {
                    player.sprite_num = 2;
                }
                else if (player.sprite_num == 2) {
                    player.sprite_num = 3;
                }
                else if (player.sprite_num == 3) {
                    player.sprite_num = 4;
                }
                else if (player.sprite_num == 4) {
                    player.sprite_num = 1;
                }

                player.sprite_counter = 0;
            }
        }
    }
}
