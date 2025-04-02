package theCreepyProperty.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.main.GUI;

public class Ghost extends Entity{
    private GUI gui;
    private double speed = 0; // speed if strg pressed
    private ImageView i_ghost = new ImageView();
    private ImageView i_ghost_overlay = new ImageView();

    private Timeline timeline;

    private double zielPosition_X = 0, zielPosition_Y = 0;
    private int playerTargetDistance = 200;

    public Ghost(GUI gui, int x, int y, int width, int height)  {
        System.out.println(".............................Ghost..............................");
        this.gui = gui;

        setDefaultValues();

        // Transfer values
        this.entity_world_X = x;
        this.entity_world_Y = y;
        this.entity_size_X = width;
        this.entity_size_Y = height;

        // Rectangle Position
        this.solid_area = new Rectangle();
        this.solid_area.setFill(Color.MAGENTA);
        this.solid_area.setVisible(false);
        this.solid_area.setX(entity_world_X+12);
        this.solid_area.setY(entity_world_Y+8);
        this.solid_area.setWidth(entity_size_X-24);
        this.solid_area.setHeight(entity_size_Y-12);

        // Player-Image bind with Rectangle
        this.i_ghost.xProperty().bind(solid_area.xProperty().subtract(12));
        this.i_ghost.yProperty().bind(solid_area.yProperty().subtract(8));
        this.i_ghost.fitWidthProperty().bind(solid_area.widthProperty().add(24));
        this.i_ghost.fitHeightProperty().bind(solid_area.heightProperty().add(12));

        // Overlay-Image bind with Rectangle
        this.i_ghost_overlay.xProperty().bind(solid_area.xProperty().subtract(200 - (solid_area.getWidth()/2)));
        this.i_ghost_overlay.yProperty().bind(solid_area.yProperty().subtract(200 - (solid_area.getHeight()/2)));
        this.i_ghost_overlay.fitWidthProperty().bind(solid_area.widthProperty().add(400 - solid_area.getWidth()));
        this.i_ghost_overlay.fitHeightProperty().bind(solid_area.heightProperty().add(400 - solid_area.getHeight()));
        this.i_ghost_overlay.setVisible(false);

        createGhostImage();

        setNewTarget();

        // timeline vor Ghost moving
        this.timeline = new Timeline(new KeyFrame(Duration.millis(16), e -> moveRectangle(this.solid_area)));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    // default values if no override
    private void setDefaultValues() {
        entity_size_X = 20;
        entity_size_Y = 29;
        entity_world_X = -100;
        entity_world_Y = -100;
        speed = 1;
        System.out.println("✔ [Ghost]: Ghost defaultValues set");
    }

    // Image Loading
    public void createGhostImage() {
        down1 = loadImage("file:src/resources/textures/ghost/down_1.png");
        down2 = loadImage("file:src/resources/textures/ghost/down_2.png");
        down3 = loadImage("file:src/resources/textures/ghost/down_3.png");
        down4 = loadImage("file:src/resources/textures/ghost/down_4.png");


        overlay = loadImage("file:src/resources/textures/overlay/ghostDistance.png");

        System.out.println("✔ [Ghost]: Image Ghost images successfully loaded");
    }


    private Image loadImage(String path) {
        return new Image(new FileCheck().checkImage("Ghost",path));
    }


    // return Ghost-Image
    public ImageView draw() {
        Image playerImage = switchSprite(down1, down2, down3, down4);

        this.i_ghost.setImage(playerImage);
        return i_ghost;
    }

    // return Overlay-Image (debug)
    public ImageView loadGhostOverlay() {
        this.i_ghost_overlay.setImage(overlay);
        return i_ghost_overlay;
    }


    // Ghost Animation
    private Image switchSprite(Image img1, Image img2, Image img3, Image img4) {
        return switch (sprite_num) {
            case 1 -> img1;
            case 2 -> img2;
            case 3 -> img3;
            case 4 -> img4;
            default -> img1;
        };
    }


    // Ghost Moving
    private void moveRectangle(Rectangle rect) {
        double playerX = this.gui.getGameScene().getPlayer().getPlayer_world_X();
        double playerY = this.gui.getGameScene().getPlayer().getPlayer_world_Y();
        double rectX = rect.getX();
        double rectY = rect.getY();

        double distanceToPlayer = Math.sqrt(Math.pow(playerX - rectX, 2) + Math.pow(playerY - rectY, 2));

        if (distanceToPlayer <= playerTargetDistance) {
            // Wenn der Spieler innerhalb von 100px ist, setze die Zielkoordinaten auf die Spielerposition
            zielPosition_X = playerX;
            zielPosition_Y = playerY;
        } else if (distanceToPlayer > playerTargetDistance && zielPosition_X == playerX && zielPosition_Y == playerY) {
            // Falls der Spieler weiter weg ist und das Ziel gerade auf den Spieler gesetzt war, neues Ziel setzen
            setNewTarget();
        }

        // Bewegung berechnen
        double dx = zielPosition_X - rectX;
        double dy = zielPosition_Y - rectY;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance > speed) { // Falls das Ziel noch nicht erreicht ist
            double vx = (dx / distance) * speed;
            double vy = (dy / distance) * speed;
            rect.setX(rectX + vx);
            rect.setY(rectY + vy);
        } else {
            rect.setX(zielPosition_X);
            rect.setY(zielPosition_Y);
            setNewTarget(); // Neues Ziel setzen, falls nötig
        }
    }

    public void showCollisionBox(boolean show) {
        if (show) {
            this.solid_area.setVisible(true);
            this.i_ghost.setOpacity(0.5);
            this.i_ghost_overlay.setVisible(true);

        } else {
            this.solid_area.setVisible(false);
            this.i_ghost.setOpacity(1);
            this.i_ghost_overlay.setVisible(false);

        }
    }

    private void setNewTarget() {
        this.zielPosition_X = Math.random() * 1000; // Damit das Rechteck nicht außerhalb liegt
        this.zielPosition_Y = Math.random() * 600;
    }


    // Getter Methoden
    public double getSpeed(){
        return this.speed;
    }

    public Rectangle getSolidAria() {
        return this.solid_area;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    // Setter Methoden
    // public
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPlayerTargetDistance(int distance) {
        this.playerTargetDistance = distance;
    }

    public void setGhostMoving(boolean value) {
        if (!value) {
            this.timeline.stop();
        } else {
            this.timeline.play();
        }

    }

    // Delete Ghost Variables
    public void deleteGhost() {
        System.out.println("⚠ [Ghost]: Alle Referenzen werden gelöscht...");

        if (timeline != null) {
            timeline.stop();
            timeline = null;
        }

        this.gui = null;
        this.speed = 0;
        this.i_ghost = null;
        this.i_ghost_overlay = null;

        this.zielPosition_X = 0;
        this.zielPosition_Y = 0;
        this.playerTargetDistance = 200;

        System.gc();
        System.out.println("✔ [Ghost]: Speicherbereinigung durchgeführt.");
    }
}

