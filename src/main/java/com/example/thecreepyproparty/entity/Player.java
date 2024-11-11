package com.example.thecreepyproparty.entity;


import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;




public class Player {
    private Rectangle r_player;
    private double speed = 5; // speed
    public double jumpHeight = 5; //Jump height
    private double x = 50; // position x
    private double y = 50; // position y


    public Player()  {
        r_player = new Rectangle(x, y, 64, 64);
        r_player.setFill(Color.DARKRED);
        r_player.setStroke(Color.RED);
        r_player.setStrokeWidth(3);

        System.out.println("[System]: Player created!");
    }

    public Rectangle getPlayer(){
        return r_player;
    }

    public void setX(double x){
        this.x = x;
        r_player.setX(x); // update player
    }

    public void setY(double y){
        this.y = y;
        r_player.setY(y); // update player
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getSpeed(){
        return speed;
    }

    public double getJumpHeight(){
        return jumpHeight;
    }
}
