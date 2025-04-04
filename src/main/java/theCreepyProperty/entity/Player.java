package theCreepyProperty.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.main.GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.main.SoundPlayer;
import javax.sound.sampled.LineEvent;
import java.util.ArrayList;

public class Player extends Entity{
    private GUI gui;
    private double controlSpeed = 0; // speed if strg pressed
    private ImageView i_player = new ImageView();
    private ImageView i_darkness_overlay = new ImageView();
    private ImageView i_vacuum_overlay = new ImageView();
    private Image vacuumOverlay;
    private SoundPlayer soundPlayer = new SoundPlayer("src/resources/sounds/heartbeat.wav");

    private boolean ghostSoundIsPlaying = false;
    private boolean overlay_on = true;

    private Timeline timeline;

    public Player(GUI gui)  {
        System.out.println(".............................Player..............................");
        this.gui = gui;

        setDefaultValues();

        // Rectangle Position
        this.solid_area = new Rectangle();
        this.solid_area.setFill(Color.MAGENTA);
        this.solid_area.setVisible(false);
        this.solid_area.setX(entity_world_X);
        this.solid_area.setY(entity_world_Y);
        this.solid_area.setWidth(entity_size_X);
        this.solid_area.setHeight(entity_size_Y);

        // Player-Image bind with Rectangle
        this.i_player.xProperty().bind(solid_area.xProperty().subtract(18));
        this.i_player.yProperty().bind(solid_area.yProperty().subtract(20));
        this.i_player.fitWidthProperty().bind(solid_area.widthProperty().add(36));
        this.i_player.fitHeightProperty().bind(solid_area.heightProperty().add(20));

        // Vacuum-Overlay bind with Rectangle
        this.i_vacuum_overlay.xProperty().bind(solid_area.xProperty().subtract(118));
        this.i_vacuum_overlay.yProperty().bind(solid_area.yProperty().subtract(120));
        this.i_vacuum_overlay.fitWidthProperty().bind(solid_area.widthProperty().add(236));
        this.i_vacuum_overlay.fitHeightProperty().bind(solid_area.heightProperty().add(220));

        // Overlay-Image bind with rectangle
        this.i_darkness_overlay.xProperty().bind(solid_area.xProperty().subtract(1166));
        this.i_darkness_overlay.yProperty().bind(solid_area.yProperty().subtract(1166)); //610
        this.i_darkness_overlay.fitWidthProperty().bind(solid_area.widthProperty().add(2332));
        this.i_darkness_overlay.fitHeightProperty().bind(solid_area.heightProperty().add(2332)); //1200

        createPlayerImage();
    }

    // default values if no override
    public void setDefaultValues() {
        entity_size_X = 12;
        entity_size_Y = 28;
        entity_world_X = ((double) gui.getWidth() / 2) - (entity_size_X / 2);
        entity_world_Y = ((double) gui.getHeight() / 2) - ((entity_size_Y / 2));
        speed = 3;
        direction = "down";
        System.out.println("✔ [Player]: Player defaultValues set");
    }

    // Image Loading
    public void createPlayerImage() {
        up1 = loadImage("file:src/resources/textures/player/up_1.png");
        up2 = loadImage("file:src/resources/textures/player/up_2.png");
        up3 = loadImage("file:src/resources/textures/player/up_3.png");
        up4 = loadImage("file:src/resources/textures/player/up_4.png");
        down1 = loadImage("file:src/resources/textures/player/down_1.png");
        down2 = loadImage("file:src/resources/textures/player/down_2.png");
        down3 = loadImage("file:src/resources/textures/player/down_3.png");
        down4 = loadImage("file:src/resources/textures/player/down_4.png");
        left1 = loadImage("file:src/resources/textures/player/left_1.png");
        left2 = loadImage("file:src/resources/textures/player/left_2.png");
        left3 = loadImage("file:src/resources/textures/player/left_3.png");
        left4 = loadImage("file:src/resources/textures/player/left_4.png");
        right1 = loadImage("file:src/resources/textures/player/right_1.png");
        right2 = loadImage("file:src/resources/textures/player/right_2.png");
        right3 = loadImage("file:src/resources/textures/player/right_3.png");
        right4 = loadImage("file:src/resources/textures/player/right_4.png");

        vacuumOverlay = loadImage("file:src/resources/textures/overlay/VacuumOverlay.png");
        overlay = loadImage("file:src/resources/textures/overlay/darknessOverlay3.png");

        System.out.println("✔ [Player]: Image Player images successfully loaded");
    }


    private Image loadImage(String path) {
        return new Image(new FileCheck().checkImage("Player",path));
    }


    // return Player-Image
    public ImageView draw() {
        Image playerImage = switch (direction) {
            case "up" -> switchSprite(up1, up2, up3, up4);
            case "down" -> switchSprite(down1, down2, down3, down4);
            case "left" -> switchSprite(left1, left2, left3, left4);
            case "right" -> switchSprite(right1, right2, right3, right4);
            default -> null;
        };

        this.i_player.setImage(playerImage);
        return i_player;
    }

    // return Vacuum-Overlay-Image
    public ImageView loadVacuumOverlay() {
        return i_vacuum_overlay;
    }

    // return Darkness-Overlay-Image
    public ImageView loadOverlay() {
        this.i_darkness_overlay.setImage(overlay);
        this.i_darkness_overlay.setRotate(90);
        return i_darkness_overlay;
    }


    // Player Animation
    private Image switchSprite(Image img1, Image img2, Image img3, Image img4) {
        return switch (sprite_num) {
            case 2 -> img2;
            case 3 -> img3;
            case 4 -> img4;
            default -> img1;
        };
    }


    // Ghost sounds
    private void playGhostSound() {
        if (!this.ghostSoundIsPlaying && this.gui.getGameScene().getMenu().getMenu_on() && !this.gui.getGameScene().getGameWin().getGameWin_On() && !this.gui.getGameScene().getGameOver().getGameOver_On()) {
            this.ghostSoundIsPlaying = true;


            this.soundPlayer.setVolume(this.gui.getGameScene().getMenu().getSettings().getAudio().getMaster());

            // Listener registrieren, um das Ende des Sounds zu erkennen
            this.soundPlayer.getClip().addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    this.ghostSoundIsPlaying = false; // Reset, wenn der Sound endet
                }
            });
            this.soundPlayer.stop();
            this.soundPlayer.play();
        }
    }

    public void stopGhostSound() {
        this.soundPlayer.stop();
    }

    public double checkForGhost(Ghost ghost, double entityCenterX, double entityCenterY) {

        // Calculate center of Ghost
        double ghostCenterX = ghost.getSolidAria().getX() + ghost.getSolidAria().getWidth() / 2;
        double ghostCenterY = ghost.getSolidAria().getY() + ghost.getSolidAria().getHeight() / 2;

        // Calculate distance between Ghost and Player
        return Math.sqrt(Math.pow(ghostCenterX - entityCenterX, 2) + Math.pow(ghostCenterY - entityCenterY, 2));
    }

    // play sound if Ghost in 120px distance
    public void checkForNearbyGhostsPlaySound(ArrayList<Ghost> ghosts) {
        // Calculate center of Player
        double entityCenterX = this.entity_world_X + this.entity_size_X / 2;
        double entityCenterY = this.entity_world_Y + this.entity_size_Y / 2;

        for (Ghost ghost : ghosts) {
            if (checkForGhost(ghost, entityCenterX, entityCenterY) <= 120) {
                playGhostSound();
                break;
            }
        }
    }


    // Vacuum Start
    public void startVacuum() {
        System.out.println("[Vacuum]: Vacuum running");
        this.i_vacuum_overlay.setImage(vacuumOverlay);

        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), _ -> {
            if (deleteNearbyGhosts(this.gui.getGameScene().getMapCreate().getGhostList())) {
                timeline.stop();
                this.i_vacuum_overlay.setImage(null);
            }
        }));
        timeline.setOnFinished(_ -> this.i_vacuum_overlay.setImage(null));

        timeline.setCycleCount(30); // 30 x 100ms = 3 Seconds
        timeline.play();
    }

    // Remove Ghost with Vacuum
    public boolean deleteNearbyGhosts(ArrayList<Ghost> ghosts) {
        boolean caught = false;
        // Calculate center of Player
        double entityCenterX = this.entity_world_X + this.entity_size_X / 2;
        double entityCenterY = this.entity_world_Y + this.entity_size_Y / 2;

        for (int i = 0 ; i < ghosts.size() ; i++) {

            if (checkForGhost(ghosts.get(i), entityCenterX, entityCenterY) <= 100) {
                this.gui.getGameScene().pGhostsChildrenRemove(ghosts.get(i).draw());
                this.gui.getGameScene().pGhostsChildrenRemove(ghosts.get(i).loadGhostOverlay());
                this.gui.getGameScene().pGhostsChildrenRemove(ghosts.get(i).getSolidAria());

                this.gui.getGameScene().getMapCreate().getGhostList().get(i).deleteGhost();
                this.gui.getGameScene().getMapCreate().getGhostList().remove(i);

                caught = true;
                break;
            }
        }
        return caught;
    }


    // Overlay Toggle
    public void triggerOverlay() {
        if (!overlay_on) {
            this.i_darkness_overlay.setImage(overlay);
            this.overlay_on = true;
        } else {
            this.i_darkness_overlay.setImage(null);
            this.overlay_on = false;
        }
    }

    // Collision-Box Toggle
    public void showCollisionBox(boolean show) {
        if (show) {
            this.solid_area.setVisible(true);
            this.i_player.setOpacity(0.5);
        } else {
            this.solid_area.setVisible(false);
            this.i_player.setOpacity(1);
        }
    }

    // Getter Methoden
    public double getPlayer_world_X(){
        return entity_world_X;
    }

    public double getPlayer_world_Y(){
        return entity_world_Y;
    }

    public double getSpeed(){
        return this.speed + this.controlSpeed;
    }

    public int getKeysCollected() {
        return keys_collected;
    }

    public boolean getCollision_on(){
        return !this.collision_on;
    }

    public Rectangle getSolidPlayerAria() {
        return this.solid_area;
    }

    public ImageView getI_darkness_overlay() {
        return this. i_darkness_overlay;
    }

    // Setter Methoden
    public void setPlayer_world_X(double player_world_X){
        this.entity_world_X = player_world_X;
        this.solid_area.setX(player_world_X);
    }

    public void setPlayer_world_Y(double player_world_Y){
        this.entity_world_Y = player_world_Y;
        this.solid_area.setY(player_world_Y);
    }

    public void setControlSpeed(double speed) {
        this.controlSpeed = speed;
    }

    public void setShiftSpeed() {
        this.controlSpeed = (this.speed - 2) * (-1);
    }

    public void setDirection(String direction) {
        this.direction = direction;
        this.draw();
    }

    // Delete Player Variables
    public void deletePlayer() {
        System.out.println("⚠ [Player]: Alle Referenzen werden gelöscht...");

        if (this.gui != null) {
            this.gui = null;
        }

        if (this.soundPlayer != null) {
            this.soundPlayer.stop();  // sicherstellen, dass der Sound gestoppt wird
            this.soundPlayer = null;
        }

        if (this.i_player != null) {
            this.i_player.setImage(null);
            this.i_player = null;
        }

        if (this.i_darkness_overlay != null) {
            this.i_darkness_overlay.setImage(null);
            this.i_darkness_overlay = null;
        }

        if (this.i_vacuum_overlay != null) {
            this.i_vacuum_overlay.setImage(null);
            this.i_vacuum_overlay = null;
        }

        if (this.solid_area != null) {
            this.solid_area.setVisible(false);  // Sichtbarkeit zurücksetzen
            this.solid_area = null;
        }

        if (this.timeline != null) {
            timeline.stop();
            timeline = null;
        }

        this.controlSpeed = 0;
        this.ghostSoundIsPlaying = false;

        up1 = null;
        up2 = null;
        up3 = null;
        up4 = null;
        down1 = null;
        down2 = null;
        down3 = null;
        down4 = null;
        left1 = null;
        left2 = null;
        left3 = null;
        left4 = null;
        right1 = null;
        right2 = null;
        right3 = null;
        right4 = null;
        overlay = null;

        this.direction = null;

        this.collision_on = false;

        System.gc();
        System.out.println("✔ [Player]: Speicherbereinigung durchgeführt.");
    }
}