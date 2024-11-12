package com.example.thecreepyproparty.entity;


import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {
    private Rectangle2D r_player;
    private final String spriteSheetPath = "file:src/main/resources/Player/player.png";
    private Image spriteSheet;
    private final ImageView imageView;
    private final SpriteAnimation spriteAnimation = new SpriteAnimation(this, 1, 64, 64);;

    private double strgSpeed = 0; // speed if strg pressed
    private double speed = 3; // speed (standard 3)
    private double x = 50; // position x
    private double y = 50; // position y


    public Player()  {

        this.spriteSheet = new Image(spriteSheetPath);

        this.imageView = new ImageView(this.spriteSheet);

        r_player = new Rectangle2D(x, y, 64, 64);
        imageView.setViewport(this.r_player);

//        r_player.setFill(Color.DARKRED);
//        r_player.setStroke(Color.RED);
//        r_player.setStrokeWidth(3);

        spriteSheet = new Image(spriteSheetPath);

        this.playAnimation();  // Starte die Animation

        System.out.println("[System]: Player created!");
    }

    // set methode
//    public void setX(double x){
//        this.x = x;
//        //r_player.setX(this.x); // update player
//    }
//
//    public void setY(double y){
//        this.y = y;
//        //r_player.setY(this.y); // update player
//    }

    public void updateViewport(double x, double y) {
        // Setze das Rechteck neu mit den aktuellen Koordinaten
        this.x = x;
        this.y = y;
        r_player = new Rectangle2D(x, y, 64, 64);
        imageView.setViewport(r_player);  // ImageView-Viewport neu setzen
    }

    public void setSpeed(double speed){this.speed = speed;}

    public void setStrgSpeed(double speed) {
        this.strgSpeed = speed;
    }

    public void setShiftSpeed() {
        this.strgSpeed = (this.speed - 2) * (-1);
    }

    // get methode
    public Rectangle2D getPlayer(){
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

    // Startet die Spieleranimation
    public void playAnimation() {
        spriteAnimation.play();
    }

    // Stoppt die Spieleranimation
    public void stopAnimation() {
        spriteAnimation.stop();
    }

    // Gibt das ImageView des Spielers zurück
    public ImageView getImageView() {
        return this.imageView;
    }

    public String getSpriteSheet() {
        return this.spriteSheetPath;
    }

    public void setSpriteSheet(Image spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
}
