package theCreepyProperty.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import theCreepyProperty.main.GUI;

public class Ghost extends Entity{
    private final GUI gui;
    private double speed = 0; // speed if strg pressed
    private final ImageView i_ghost = new ImageView();

    private Timeline timeline;

    private double zielPosition_X = 0, zielPosition_Y = 0;

    public Ghost(GUI gui, int x, int y, int width, int height)  {
        this.gui = gui;

        setDefaultValues();

        this.entity_world_X = x;
        this.entity_world_Y = y;
        this.entity_size_X = width;
        this.entity_size_Y = height;

//        this.i_ghost.setX(x);
//        this.i_ghost.setY(y);

        this.solid_aria = new Rectangle();
        this.solid_aria.setVisible(false); //Collision Block of Ghost anzeigen
        this.solid_aria.setX(entity_world_X);
        this.solid_aria.setY(entity_world_Y);
        this.solid_aria.setWidth(entity_size_X);
        this.solid_aria.setHeight(entity_size_Y);

        this.i_ghost.xProperty().bind(solid_aria.xProperty());
        this.i_ghost.yProperty().bind(solid_aria.yProperty());
        this.i_ghost.fitWidthProperty().bind(solid_aria.widthProperty());
        this.i_ghost.fitHeightProperty().bind(solid_aria.heightProperty());

        createGhostImage();

        setNewTarget();
        this.timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> moveRectangle(this.solid_aria)));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    public void createGhostImage() {
        up1 = loadImage("file:src/resources/textures/player/up_1.png");
        up2 = loadImage("file:src/resources/textures/player/up_2.png");
        up3 = loadImage("file:src/resources/textures/player/up_3.png");
        up4 = loadImage("file:src/resources/textures/player/up_4.png");
        down1 = loadImage("file:src/resources/textures/ghost/Ghost.png");
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

        System.out.println("✔ [Ghost]: Image Ghost images successfully loaded");
    }

    private Image loadImage(String path) {
        Image image = new Image(path);
        if (image.isError()) {
            System.err.println("✖ [Ghost]: loadImage Failed to load image: " + path);
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
        this.i_ghost.setImage(playerImage);
        return i_ghost;
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

    private void moveRectangle(Rectangle rect) {
        double dx = zielPosition_X - rect.getX();
        double dy = zielPosition_Y - rect.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > speed) { // Falls das Ziel noch nicht erreicht ist
            double vx = (dx / distance) * speed;
            double vy = (dy / distance) * speed;
            rect.setX(rect.getX() + vx);
            rect.setY(rect.getY() + vy);
        } else {
            rect.setX(zielPosition_X);
            rect.setY(zielPosition_Y);
            setNewTarget(); // Neues Ziel setzen
        }
    }

    private void setNewTarget() {
        this.zielPosition_X = Math.random() * 1000; // Damit das Rechteck nicht außerhalb liegt
        this.zielPosition_Y = Math.random() * 600;
    }

    public void setPlayer_world_X(double player_world_X){
        this.entity_world_X = player_world_X;
        this.solid_aria.setX(player_world_X);
    }

    public void setPlayer_world_Y(double player_world_Y){
        this.entity_world_Y = player_world_Y;
        this.solid_aria.setY(player_world_Y);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDirection(String direction) {
        this.direction = direction;
        this.draw();
    }

    public void setDefaultValues() {
        entity_size_X = 32;
        entity_size_Y = 32;
        entity_world_X = -100;
        entity_world_Y = -100;
        speed = 1;
        direction = "down";
        System.out.println("✔ [Ghost]: Ghost defaultValues set");
    }

    // Getter Methoden
    public double getPlayer_world_X(){
        return entity_world_X;
    }

    public double getPlayer_world_Y(){
        return entity_world_Y;
    }

    public double getSpeed(){
        return this.speed + this.speed;
    }

    public Rectangle getSolidAria() {
        return this.solid_aria;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }
}
