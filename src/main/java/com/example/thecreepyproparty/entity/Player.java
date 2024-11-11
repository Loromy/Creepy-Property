package com.example.thecreepyproparty.entity;


import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;




public class Player {
    private Rectangle r_player;
    private double strgSpeed = 0; // speed if strg pressed
    private double speed = 3; // speed (standard 3)
    private double x = 50; // position x
    private double y = 50; // position y


    public Player()  {
        r_player = new Rectangle(x, y, 64, 64);
        r_player.setFill(Color.DARKRED);
        r_player.setStroke(Color.RED);
        r_player.setStrokeWidth(3);

        System.out.println("[System]: Player created!");
    }

    // set methode
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
