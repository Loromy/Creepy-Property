package theCreepyProperty.entity;

import javafx.scene.shape.Rectangle;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

import javax.sound.sampled.LineEvent;
import java.util.ArrayList;

public class Player extends Entity{
    private final GUI gui;
    private double controlSpeed = 0; // speed if strg pressed
    private final ImageView i_player = new ImageView();
    private final ImageView i_darkness_overlay = new ImageView();
    private SoundPlayer soundPlayer = new SoundPlayer("src/resources/sounds/heartbeat.wav");

    private boolean overlay_on = true;
    private boolean ghostSoundIsPlaying = false;
    private double distance;

    public Player(GUI gui)  {
        this.gui = gui;
        setDefaultValues();
        this.solid_aria = new Rectangle();
        this.solid_aria.setVisible(false); //Collision Block of player anzeigen
        this.solid_aria.setX(entity_world_X);
        this.solid_aria.setY(entity_world_Y);
        this.solid_aria.setWidth(entity_size_X);
        this.solid_aria.setHeight(entity_size_Y);

        this.i_player.xProperty().bind(solid_aria.xProperty().subtract(18));
        this.i_player.yProperty().bind(solid_aria.yProperty().subtract(20));
        this.i_player.fitWidthProperty().bind(solid_aria.widthProperty().add(36));
        this.i_player.fitHeightProperty().bind(solid_aria.heightProperty().add(20));

        this.i_darkness_overlay.xProperty().bind(solid_aria.xProperty().subtract(1000));
        this.i_darkness_overlay.yProperty().bind(solid_aria.yProperty().subtract(610));
        this.i_darkness_overlay.fitWidthProperty().bind(solid_aria.widthProperty().add(2000));
        this.i_darkness_overlay.fitHeightProperty().bind(solid_aria.heightProperty().add(1200));

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

        darknessOverlay = loadImage("file:src/resources/textures/overlay/darknessOverlay.png");

        System.out.println("✔ [Player]: Image Player images successfully loaded");
    }

    private Image loadImage(String path) {
        Image image = new Image(path);
        if (image.isError()) {
            System.err.println("✖ [Player]: loadImage Failed to load image: " + path);
            if (image.getException() != null) {
                image.getException().printStackTrace();
            }
        }
        return image;
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
        this.i_darkness_overlay.setImage(darknessOverlay);
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

    public void checkForNearbyGhosts(ArrayList<Ghost> ghosts) {
        for (int i = 0 ; i < ghosts.size() ; i++) {
            this.distance = 1000;
            this.distance = Math.sqrt(Math.pow(ghosts.get(i).getGhost_world_X() - this.entity_world_X, 2) + Math.pow(ghosts.get(i).getGhost_world_Y() - this.entity_world_Y, 2));



            if (this.distance <= 200) {
                playGhostSound();
                break;
            }
        }
    }

    private void playGhostSound() {
        if (!this.ghostSoundIsPlaying && !this.gui.getGameScene().getMenu().getMenu_on() && !this.gui.getGameScene().getGameWin().getGameWin_On() && !this.gui.getGameScene().getGameOver().getGameOver_On()) {
            this.ghostSoundIsPlaying = true;


            this.soundPlayer.setVolume(this.gui.getGameScene().getMenu().getSettings().getAudio().getMaster(),"heartbeat");

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

    public void triggerOverlayRWSettings(GameScene gameScene) {
        if (!overlay_on) {
            this.i_darkness_overlay.setImage(darknessOverlay);
            gameScene.getMenu().getSettings().getButton3().setText("Overlay [ON]");
            this.overlay_on = true;
        } else {
            this.i_darkness_overlay.setImage(null);
            gameScene.getMenu().getSettings().getButton3().setText("Overlay [OFF]");
            this.overlay_on = false;
        }
    }

    public void triggerOverlay(ReadWriteSettings readWriteSettings) {
        if (!overlay_on) {
            this.i_darkness_overlay.setImage(darknessOverlay);
            this.gui.getGameScene().getMenu().getSettings().getButton3().setText("Overlay [ON]");
            readWriteSettings.updateSetting("overlay", 1);
            this.overlay_on = true;
        } else {
            this.i_darkness_overlay.setImage(null);
            this.gui.getGameScene().getMenu().getSettings().getButton3().setText("Overlay [OFF]");
            readWriteSettings.updateSetting("overlay", 0);
            this.overlay_on = false;
        }
    }

    public void setPlayer_world_X(double player_world_X){
        this.entity_world_X = player_world_X;
        this.solid_aria.setX(player_world_X);
    }

    public void setPlayer_world_Y(double player_world_Y){
        this.entity_world_Y = player_world_Y;
        this.solid_aria.setY(player_world_Y);
    }

    public void setControlSpeed(double speed) {
        this.controlSpeed = speed;
    }

    public void setShiftSpeed() {
        this.controlSpeed = (this.speed - 2) * (-1);
    }

    public void setOverlay_on(boolean value) {
        this.overlay_on = value;
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
        return this.solid_aria;
    }
}