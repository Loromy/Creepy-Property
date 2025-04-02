package theCreepyProperty.main;

import javafx.scene.shape.Rectangle;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.entity.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameWin;

public class KeyHandler {
    private Player player;
    private GameScene gameScene;
    private LevelSelectScene levelSelectScene;
    private Menu menu;
    private GameOver gameOver;
    private GameWin gameWin;
    private MapCreate mapCreate;
    private SoundPlayer soundPlayer;

    private AnimationTimer timer;

    //Movement
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    private boolean ctrlPressed = false;
    private boolean shiftPressed = false;
    private boolean escPressed = false;

    // Development
    private boolean cPressed = false;
    private boolean pPressed = false;
    private boolean oPressed = false;
    private boolean mPressed = false;
    private boolean bPressed = false;
    private boolean lastState_bPressed = true;

    private double nextPlayerX;
    private double nextPlayerY;

    private double sprintTime = 5.0; // max Sprint time
    private double maxSprintTime = sprintTime; // max Sprint time tu regenerate
    private double sprintRegenerationSpeed = 0.5; // Regenerate speed per second
    private double sprintIncreaseRate = 0.1; // Speed of regeneration per second
    private double cooldownTime = 1.0; // cooldown

    public KeyHandler(Player player, GameScene gameScene, LevelSelectScene levelSelectScene, Menu menu, GameOver gameOver, GameWin gameWin) {
        System.out.println(".............................KeyHandler..............................");
        this.player = player;
        this.gameScene = gameScene;
        this.levelSelectScene = levelSelectScene;
        this.menu = menu;
        this.gameOver = gameOver;
        this.gameWin = gameWin;
        this.mapCreate = gameScene.getMapCreate();

        this.nextPlayerX = this.player.getPlayer_world_X();
        this.nextPlayerY = this.player.getPlayer_world_Y();
    }

    public void addKeyListener(Scene scene, GameScene gameScene) {
        // Set key-pressed ture
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPressed = true;
                case S -> sPressed = true;
                case A -> aPressed = true;
                case D -> dPressed = true;
                case P -> pPressed = true;
                case C -> cPressed = true;
                case O -> oPressed = true;
                case M -> mPressed = true;
                case B -> bPressed = true;
                case CONTROL -> ctrlPressed = true;
                case SHIFT -> shiftPressed = true;
                case ESCAPE -> escPressed = true;
            }
        });


        // Set key-pressed false
        scene.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            switch (keyCode) {
                case W -> wPressed = false;
                case S -> sPressed = false;
                case A -> aPressed = false;
                case D -> dPressed = false;
                case P -> pPressed = false;
                case C -> cPressed = false;
                case O -> oPressed = false;
                case M -> mPressed = false;
                case B -> bPressed = false;
                case CONTROL -> ctrlPressed = false;
                case SHIFT -> shiftPressed = false;
                case ESCAPE -> escPressed = false;
            }
        });

        // Darkness Overlay rotation tracking on mous
        scene.setOnMouseMoved(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            double imageX = this.player.getI_darkness_overlay().getX() + this.player.getI_darkness_overlay().getFitWidth() / 2;  // Mittelpunkt des Bildes
            double imageY = this.player.getI_darkness_overlay().getY() + this.player.getI_darkness_overlay().getFitHeight() / 2;

            // Calculate Rotation-Angle
            double angle = Math.toDegrees(Math.atan2(mouseY - imageY, mouseX - imageX));

            // set Darkness overlay rotation
            this.player.getI_darkness_overlay().setRotate(angle);
        });


        // FPS calculation
        this.timer  = new AnimationTimer()  {
            private long lastTime = System.nanoTime();
            private long lastFPSUpdate = System.nanoTime();
            private int frameCount = 0;

            //@Override
            public void handle(long now) {
                double deltaTime = (now - lastTime) / 1_000_000_000.0; // Delta-Zeit in Sekunden
                lastTime = now;

                handleMovement(deltaTime); // update Movement

                // FPS calculation
                frameCount++;
                if (now - lastFPSUpdate >= 1_000_000_000) { // Wenn 1 Sekunde vergangen ist
                    double fps = frameCount;
                    frameCount = 0;
                    lastFPSUpdate = now;

                    // show FPS in GUI-Components
                    gameScene.getGuiComponents().updateFPS(fps);
                }
            }
        };
        timer.start();
    }

    // checking if keys are pressed
    private void handleMovement(double deltaTime) {
        if (!this.menu.getMenu_on() && !this.gameOver.getGameOver_On() && !this.gameWin.getGameWin_On()) {
            double dx = 0;
            double dy = 0;

            // Movement-keys inputs
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

            // show Player Position
            if (pPressed) {
                System.out.println("[KeyHandler]: Position Player: x=" + this.player.getPlayer_world_X() + " y=" + this.player.getPlayer_world_Y());
                this.pPressed = false;
            }

            // Collision Toggle
            if (cPressed) {
                this.mapCreate.triggerCollision();
                this.cPressed = false;
            }

            // Darkness Overlay Toggle
            if (oPressed) {
                this.player.triggerOverlay();
                this.oPressed = false;
            }

            // Ghost Moving Toggle
            if(mPressed) {
                this.mapCreate.triggerGhostMoving();

                this.mPressed = false;
            }

            // Show hitBox of player & ghost Toggle
            if (bPressed) {
                if (lastState_bPressed) {
                    // show hit-box
                    this.player.showCollisionBox(true);

                    for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
                        ghost.showCollisionBox(true);
                    }
                    System.out.println("✔ [KeyHandler]: Collision Box toggled [+]");

                    lastState_bPressed = false;

                    bPressed = false;
                } else {
                    // hide hit-box
                    this.player.showCollisionBox(false);
                    for (Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
                        ghost.showCollisionBox(false);
                    }
                    System.out.println("✔ [KeyHandler]: Collision Box reset [-]");

                    lastState_bPressed = true;

                    bPressed = false;
                }
            }



            // if on Cooldown you can not sprint
            if (cooldownTime > 0) {
                cooldownTime -= deltaTime; // Cooldown ablaufen lassen
            }

            // Check if sprinting is allowed
            if (ctrlPressed && sprintTime > 0 && cooldownTime <= 0) {
                this.player.setControlSpeed(2); // Sprint-Geschwindigkeit
                sprintTime -= deltaTime; // Sprintzeit abbauen
                if (sprintTime < 0) sprintTime = 0; // Keine negativen Werte zulassen
            } else {
                this.player.setControlSpeed(0);

                for(Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
                    ghost.setSpeed(1);
                    ghost.setPlayerTargetDistance(200);
                }

                // Regenerate sprint if not pressed
                if (!ctrlPressed && cooldownTime <= 0) {
                    sprintTime += deltaTime * sprintRegenerationSpeed; // Regeneration der Sprintzeit

                    // Regenerate unless max-value reached
                    if (sprintTime < maxSprintTime) {
                        sprintTime += deltaTime * sprintIncreaseRate; // Sprintzeit langsam erhöhen
                    }

                    // if sprint-Time is bigger than max-sprint-Time
                    if (sprintTime > maxSprintTime) sprintTime = maxSprintTime; // Maximale Sprintzeit (10 Sekunden)
                }
            }

            // Sneaking for shorter Ghost Range
            if (shiftPressed) {
                this.player.setShiftSpeed();
                for(Ghost ghost : this.gameScene.getMapCreate().getGhostList()) {
                    ghost.setSpeed(0.7);
                    ghost.setPlayerTargetDistance(100);
                }
                this.gameScene.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());
            }

            // if sprint-Time is 0 than set cooldown
            if (sprintTime <= 0 && cooldownTime <= 0) {
                cooldownTime = 1.0;
            }

            // show speed in GuiComponents
            this.gameScene.getGuiComponents().getL_speed().setText("Speed: " + player.getSpeed());

            // show sprint-time remaining in guiComponents
            int sprintBarLength = (int) (sprintTime / maxSprintTime * (maxSprintTime * 2));
            String sprintBar = "sprint: " + "|".repeat(sprintBarLength);
            this.gameScene.getGuiComponents().getL_sprint().setText(sprintBar);

            // move Player
            if (dx != 0 || dy != 0) {
                move(dx, dy, deltaTime);
            }

            // check Player collision with Ghosts
            checkGhostCollision(deltaTime);
            animation(deltaTime);
        }

        // show Menu Toggle
        if (escPressed) {
            this.escPressed = false;
            if (!menu.getSettings().getSettingOn() && !menu.getSettings().getAudio().getAudioOn() && !this.gameScene.getGameWin().getGameWin_On() && !this.gameScene.getGameOver().getGameOver_On()) {
                this.menu.triggerMenu();
            }
            else if (menu.getSettings().getSettingOn() && !menu.getSettings().getAudio().getAudioOn()){
                this.menu.getSettings().triggerSettings();
                this.menu.getpMenu().setVisible(true);
            }
            else if (menu.getSettings().getSettingOn() && menu.getSettings().getAudio().getAudioOn() ) {
                this.menu.getSettings().getAudio().triggerAudio();
                this.menu.getSettings().getMenuSettings().setVisible(true);
            }
        }
    }


    // Moves the player based on speed and direction
    private void move(double dx, double dy, double deltaTime) {
        // Calculate movement vector length (normalize direction)
        double length = Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        // Adjust movement for consistent speed across different FPS
        double speed = player.getSpeed() * deltaTime * 60; // Base speed for 60 FPS
        this.nextPlayerX = player.getPlayer_world_X() + dx * speed;
        this.nextPlayerY = player.getPlayer_world_Y() + dy * speed;

        // Check X collision and update position if no collision
        player.collision_on = false;
        this.gameScene.getChecker().checkCollision(player, this.nextPlayerX, player.getPlayer_world_Y());
        if (!player.getCollision_on()) {
            player.setPlayer_world_X(this.nextPlayerX);
        }

        // Check Y collision and update position if no collision
        player.collision_on = false;
        this.gameScene.getChecker().checkCollision(player, player.getPlayer_world_X(), this.nextPlayerY);
        if (!player.getCollision_on()) {
            player.setPlayer_world_Y(this.nextPlayerY);
        }

        // Special collision check for the tutorial map
        if (this.levelSelectScene.getMapSelected() == 0) {
            boolean collidingX = this.gameScene.getChecker().isCollidingWithWall(this.player, this.nextPlayerX, player.getPlayer_world_X(), 9);
            boolean collidingY = this.gameScene.getChecker().isCollidingWithWall(this.player, player.getPlayer_world_X(), this.nextPlayerY, 9);

            // Show collision warning if the player is blocked
            if (collidingX || collidingY) {
                this.gameScene.getTutorialMapInfo().setCollisionVisible(true);
            } else {
                this.gameScene.getTutorialMapInfo().setCollisionVisible(false);
            }
        }
    }

    // Checks if the player collides with a ghost
    private void checkGhostCollision(double deltaTime) {
        // Predict player's next position
        Rectangle futurePlayer = new Rectangle(this.nextPlayerX, this.nextPlayerY, player.entity_size_X, player.entity_size_Y);

        for (Ghost ghost : mapCreate.getGhostList()) {
            Rectangle ghostNew = ghost.getSolidAria();

            // If player collides with a ghost, play sounds and trigger game over
            if (futurePlayer.intersects(ghostNew.getBoundsInLocal())) {
                this.soundPlayer = new SoundPlayer("src/resources/sounds/stabbed.wav");
                this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();
                this.soundPlayer = new SoundPlayer("src/resources/sounds/ouch.wav");
                this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                this.gameScene.getGameOver().triggerGameOver();
                return;
            }

            // Animate ghost sprite
            ghost.sprite_counter += deltaTime * 60;

            // Change sprite frame based on ghost speed
            int frameSpeed = Math.max(4, 14 - (int) ghost.getSpeed());

            if (ghost.sprite_counter > frameSpeed) {
                ghost.sprite_num = (ghost.sprite_num % 4) + 1; // Cycle: 1 → 2 → 3 → 4 → 1
                ghost.sprite_counter = 0;
            }

            // Draw the ghost
            ghost.draw();
        }
    }

    // Player image animation
    private void animation(double deltaTime) {
        // Check if movement keys are pressed
        if (this.wPressed || this.sPressed || this.aPressed || this.dPressed) {
            player.sprite_counter += deltaTime * 60;

            // Change sprite frame based on player speed
            int frameSpeed = Math.max(4, 14 - (int) player.getSpeed());

            if (player.sprite_counter > frameSpeed) {
                player.sprite_num = (player.sprite_num % 4) + 1; // Cycle: 1 → 2 → 3 → 4 → 1
                player.sprite_counter = 0;
            }
        } else {
            // Reset to default sprite when idle
            player.sprite_num = 1;
            this.player.draw();
        }
    }

    // Delete Variables
    public void deleteKeyHandler() {
        if (this.timer != null) {
            this.timer.stop();
            this.timer = null;
        }

        if (this.gameScene != null && this.gameScene.getScene() != null) {
            this.gameScene.getScene().setOnKeyPressed(null);
            this.gameScene.getScene().setOnKeyReleased(null);
        }

        if (this.soundPlayer != null) {
            this.soundPlayer.stop();
        }

        this.player = null;
        this.gameScene = null;
        this.levelSelectScene = null;
        this.menu = null;
        this.gameOver = null;
        this.gameWin = null;
        this.mapCreate = null;
        this.soundPlayer = null;

        this.wPressed = false;
        this.aPressed = false;
        this.sPressed = false;
        this.dPressed = false;
        this.cPressed = false;
        this.bPressed = false;
        this.lastState_bPressed = true;
        this.ctrlPressed = false;
        this.shiftPressed = false;
        this.escPressed = false;

        this.nextPlayerX = 0.0;
        this.nextPlayerY = 0.0;
        this.sprintTime = 0.0;
        this.maxSprintTime = 0.0;
        this.sprintRegenerationSpeed = 0.0;
        this.sprintIncreaseRate = 0.0;
        this.cooldownTime = 0.0;

        System.gc();
        System.out.println("✔ [KeyHandler]: Speicherbereinigung durchgeführt.");
    }
}