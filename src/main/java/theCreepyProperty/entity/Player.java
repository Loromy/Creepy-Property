package theCreepyProperty.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import theCreepyProperty.checker.ImageCheck;
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
    private SoundPlayer soundPlayer = new SoundPlayer("src/resources/sounds/heartbeat.wav");

    private boolean ghostSoundIsPlaying = false;

    public Player(GUI gui)  {
        System.out.println(".............................Player..............................");
        this.gui = gui;
        setDefaultValues();
        this.solid_area = new Rectangle();
        this.solid_area.setFill(Color.MAGENTA);
        this.solid_area.setVisible(false);
        this.solid_area.setX(entity_world_X);
        this.solid_area.setY(entity_world_Y);
        this.solid_area.setWidth(entity_size_X);
        this.solid_area.setHeight(entity_size_Y);

        // player Image auf 48x48px zoom und collision rechteck mittig-unten
        this.i_player.xProperty().bind(solid_area.xProperty().subtract(18));
        this.i_player.yProperty().bind(solid_area.yProperty().subtract(20));
        this.i_player.fitWidthProperty().bind(solid_area.widthProperty().add(36));
        this.i_player.fitHeightProperty().bind(solid_area.heightProperty().add(20));

        this.i_darkness_overlay.xProperty().bind(solid_area.xProperty().subtract(1000));
        this.i_darkness_overlay.yProperty().bind(solid_area.yProperty().subtract(610));
        this.i_darkness_overlay.fitWidthProperty().bind(solid_area.widthProperty().add(2000));
        this.i_darkness_overlay.fitHeightProperty().bind(solid_area.heightProperty().add(1200));

        createPlayerImage();
    }

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

        overlay = loadImage("file:src/resources/textures/overlay/darknessOverlay.png");

        System.out.println("✔ [Player]: Image Player images successfully loaded");
    }

    private Image loadImage(String path) {
        return new Image(new ImageCheck().checkImage("Player",path));
    }

    public ImageView draw() {
        Image playerImage = null;

        switch (direction) {
            case "up":
                playerImage = switchSprite(up1, up2, up3, up4);
                break;
            case "down":
                playerImage = switchSprite(down1, down2, down3, down4);
                break;
            case "left":
                playerImage = switchSprite(left1, left2, left3, left4);
                break;
            case "right":
                playerImage = switchSprite(right1, right2, right3, right4);
                break;
        }
        this.i_player.setImage(playerImage);
        return i_player;
    }

    public ImageView loadOverlay() {
        this.i_darkness_overlay.setImage(overlay);
        //this.i_darkness_overlay.setImage(null); //TODO Overlay ausblenden
        return i_darkness_overlay;
    }

    private Image switchSprite(Image img1, Image img2, Image img3, Image img4) {
        return switch (sprite_num) {
            case 1 -> img1;
            case 2 -> img2;
            case 3 -> img3;
            case 4 -> img4;
            default -> img1;
        };
    }

    // play sound if Ghost in 120px distance
    public void checkForNearbyGhosts(ArrayList<Ghost> ghosts) {
        // Berechne den Mittelpunkt der Entity (angenommen, Entity hat eine Breite und Höhe)
        double entityCenterX = this.entity_world_X + this.entity_size_X / 2;
        double entityCenterY = this.entity_world_Y + this.entity_size_Y / 2;

        for (Ghost ghost : ghosts) {
            // Berechne den Mittelpunkt des Geistes (angenommen, Ghost hat eine Breite und Höhe)
            double ghostCenterX = ghost.getSolidAria().getX() + ghost.getSolidAria().getWidth() / 2;
            double ghostCenterY = ghost.getSolidAria().getY() + ghost.getSolidAria().getHeight() / 2;

            // Berechne die Distanz zwischen den Mittelpunkten
            double distance = Math.sqrt(Math.pow(ghostCenterX - entityCenterX, 2) + Math.pow(ghostCenterY - entityCenterY, 2));

            if (distance <= 120) {
                playGhostSound();
                break;
            }
        }
    }


    private void playGhostSound() {
        if (!this.ghostSoundIsPlaying && !this.gui.getGameScene().getMenu().getMenu_on() && !this.gui.getGameScene().getGameWin().getGameWin_On() && !this.gui.getGameScene().getGameOver().getGameOver_On()) {
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

    public void setDefaultValues() {
        entity_size_X = 12;
        entity_size_Y = 28;
        entity_world_X = ((double) gui.getWidth() / 2) - (entity_size_X / 2);
        entity_world_Y = ((double) gui.getHeight() / 2) - ((entity_size_Y / 2 ) + 19);
        speed = 3;
        direction = "down";
        System.out.println("✔ [Player]: Player defaultValues set");
    }

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

    public int getKeyEingesammelt() {
        return keys_eingesammelt;
    }

    public boolean getCollision_on(){
        return this.collision_on;
    }

    public Rectangle getSolidPlayerAria() {
        return this.solid_area;
    }

    public void deletePlayer() {
        System.out.println("⚠ [Player]: Alle Referenzen werden gelöscht...");

        // GUI Referenzen löschen
        if (this.gui != null) {
            this.gui = null;
        }

        // SoundPlayer löschen
        if (this.soundPlayer != null) {
            this.soundPlayer.stop();  // sicherstellen, dass der Sound gestoppt wird
            this.soundPlayer = null;
        }

        // ImageViews auf null setzen
        if (this.i_player != null) {
            this.i_player.setImage(null);
            this.i_player = null;
        }

        if (this.i_darkness_overlay != null) {
            this.i_darkness_overlay.setImage(null);
            this.i_darkness_overlay = null;
        }

        // SolidArea löschen
        if (this.solid_area != null) {
            this.solid_area.setVisible(false);  // Sichtbarkeit zurücksetzen
            this.solid_area = null;
        }

        // Variablen zurücksetzen
        this.controlSpeed = 0;
        this.ghostSoundIsPlaying = false;

        // Bild-Dateien entfernen
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

        // Abstand zu Geistern und andere Checks zurücksetzen
        this.direction = null;

        // Spieler-Kollisionsstatus zurücksetzen
        this.collision_on = false;

        // Garbage Collector anstoßen
        System.gc();
        System.out.println("✔ [Player]: Speicherbereinigung durchgeführt.");
    }

}