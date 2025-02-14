package theCreepyProperty.entity;

import javafx.scene.shape.Rectangle;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.scenes.GameScene;

public class Player extends Entity{
    private final GUI gui;
    private double controlSpeed = 0; // speed if strg pressed
    private final ImageView i_player = new ImageView();
    private final ImageView i_darkness_overlay = new ImageView();

    private boolean overlay_on = true;

    public Player(GUI gui)  {
        this.gui = gui;
        setDefaultValues();
        this.solid_player_aria = new Rectangle();
        this.solid_player_aria.setVisible(false); //Collision Block of player
        this.solid_player_aria.setX(entity_world_X);
        this.solid_player_aria.setY(entity_world_Y);
        this.solid_player_aria.setWidth(entity_size_X);
        this.solid_player_aria.setHeight(entity_size_Y);

        this.i_player.xProperty().bind(solid_player_aria.xProperty().subtract(18));
        this.i_player.yProperty().bind(solid_player_aria.yProperty().subtract(20));
        this.i_player.fitWidthProperty().bind(solid_player_aria.widthProperty().add(36));
        this.i_player.fitHeightProperty().bind(solid_player_aria.heightProperty().add(20));

        this.i_darkness_overlay.xProperty().bind(solid_player_aria.xProperty().subtract(1000));
        this.i_darkness_overlay.yProperty().bind(solid_player_aria.yProperty().subtract(610));
        this.i_darkness_overlay.fitWidthProperty().bind(solid_player_aria.widthProperty().add(2000));
        this.i_darkness_overlay.fitHeightProperty().bind(solid_player_aria.heightProperty().add(1200));

        createPlayerImage();
    }

    public void createPlayerImage() {
        up1 = loadImage("file:src/resources/player/up_1.png");
        up2 = loadImage("file:src/resources/player/up_2.png");
        up3 = loadImage("file:src/resources/player/up_3.png");
        up4 = loadImage("file:src/resources/player/up_4.png");
        down1 = loadImage("file:src/resources/player/down_1.png");
        down2 = loadImage("file:src/resources/player/down_2.png");
        down3 = loadImage("file:src/resources/player/down_3.png");
        down4 = loadImage("file:src/resources/player/down_4.png");
        left1 = loadImage("file:src/resources/player/left_1.png");
        left2 = loadImage("file:src/resources/player/left_2.png");
        left3 = loadImage("file:src/resources/player/left_3.png");
        left4 = loadImage("file:src/resources/player/left_4.png");
        right1 = loadImage("file:src/resources/player/right_1.png");
        right2 = loadImage("file:src/resources/player/right_2.png");
        right3 = loadImage("file:src/resources/player/right_3.png");
        right4 = loadImage("file:src/resources/player/right_4.png");

        darknessOverlay = loadImage("file:src/resources/textures/overlay/darknessOverlay.png");

        System.out.println("[Player Image]: Player images successfully loaded ✔ ️");
    }

    private Image loadImage(String path) {
        Image image = new Image(path);
        if (image.isError()) {
            System.err.println("[Error]: Failed to load image: " + path + " ️️✖");
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
        this.solid_player_aria.setX(player_world_X);
    }

    public void setPlayer_world_Y(double player_world_Y){
        this.entity_world_Y = player_world_Y;
        this.solid_player_aria.setY(player_world_Y);
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
        System.out.println("[System]: Player defaultValues set ✔");
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
        return this.solid_player_aria;
    }
}