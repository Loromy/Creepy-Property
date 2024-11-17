package com.example.thecreepyproparty.entity;


import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Player extends Entity{
    private final Rectangle r_player;
    private double strgSpeed = 0; // speed if strg pressed
    private final double speed = 3; // speed (standard 3)
    private double x = 50; // position x
    private double y = 50; // position y


    public Player()  {
        this.r_player = new Rectangle(x, y, 64, 64);

        setDefaultValues();
        getPlayerImage();
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
        try {
            up1 = ;
            //images player
        } catch (IOException e) {
            System.out.println("[ERROR]: Player texture can not be loaded!");
        }
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
