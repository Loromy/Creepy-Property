package theCreepyProperty.entity;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Player extends Entity{
    private final Rectangle r_player;
    private double strgSpeed = 0; // speed if strg pressed
    private final double speed = 3; // speed (standard 3)
    private double x = 50; // position x
    private double y = 50; // position y


    public Player()  {
        this.r_player = new Rectangle(x, y, 64, 64);

        setDefaultValues();
        //getPlayerImage();
    }

    // set methode
    public void setDefaultValues(){
        this.r_player.setFill(Color.DARKRED);
        this.r_player.setStroke(Color.RED);
        this.r_player.setStrokeWidth(3);

        System.out.println("[System]: Player created!");
    }

    public void setX(double x){
        this.x = x;
        r_player.setX(x); // update player
    }

    public void setY(double y){
        this.y = y;
        r_player.setY(y); // update player
    }

    public void setStrgSpeed(double speed) {
        this.strgSpeed = speed;
    }

    public void setShiftSpeed() {
        this.strgSpeed = (this.speed - 2) * (-1);
    }

    // get methode
    public void getPlayerImage(){
        up1 = new Image("src/resources/player/up_1.png"); // updated pfad
        up2 = new Image("src/resources/player/up_2.png");
        up3 = new Image("src/resources/player/up_3.png");
        up4 = new Image("src/resources/player/up_4.png");

        down1 = new Image("src/resources/player/down_1.png");
        down2 = new Image("src/resources/player/down_2.png");
        down3 = new Image("src/resources/player/down_3.png");
        down4 = new Image("src/resources/player/down_4.png");

        left1 = new Image("src/resources/player/left_1.png");
        left2 = new Image("src/resources/player/left_2.png");
        left3 = new Image("src/resources/player/left_3.png");
        left4 = new Image("src/resources/player/left_4.png");

        right1 = new Image("src/resources/player/right_1.png");
        right2 = new Image("src/resources/player/right_2.png");
        right3 = new Image("src/resources/player/right_3.png");
        right4 = new Image("src/resources/player/right_4.png");
    }

    public Rectangle getPlayer(){
        return r_player;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getSpeed(){
        return this.speed + this.strgSpeed;
    }
}
