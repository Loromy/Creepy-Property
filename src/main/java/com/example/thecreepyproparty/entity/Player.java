package com.example.thecreepyproparty.entity;


import com.example.thecreepyproparty.main.GUI;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;




public class Player {
//    private Pane playerPane;
    private Rectangle player;
    private double speed = 10; // Geschwindigkeit des Spielers
    private double x = 50;
    private double y = 50;

    private double playerVelocityX = 0;  // Geschwindigkeit in X-Richtung
    private double playerVelocityY = 0;  // Geschwindigkeit in Y-Richtung
    private double acceleration = 0.2;   // Beschleunigung
    private double maxSpeed = 10;        // Maximale Geschwindigkeit


    public Player()  {
        player = new Rectangle(x, y, 64, 64);
        player.setFill(Color.DARKRED);
        player.setStroke(Color.RED);
        player.setStrokeWidth(3);

        System.out.println("[System]: Player created!");
    }

    public Rectangle getPlayer(){
        return player;
    }

    public void move(double dx, double dy) {
//        // Bewege den Spieler basierend auf den Eingaben
//        player.setX(player.getX() + dx * speed);
//        player.setY(player.getY() + dy * speed);


        // Berechne die neue Geschwindigkeit basierend auf der Beschleunigung
        if (dx != 0) {
            playerVelocityX += dx * acceleration;
            // Begrenze die Geschwindigkeit auf die maximale Geschwindigkeit
            if (Math.abs(playerVelocityX) > maxSpeed) {
                playerVelocityX = Math.signum(playerVelocityX) * maxSpeed;
            }
        } else {
            // Verzögere die Bewegung, wenn keine Eingabe erfolgt
            playerVelocityX *= 0.9;  // Dämpfung der Bewegung
        }

        if (dy != 0) {
            playerVelocityY += dy * acceleration;
            // Begrenze die Geschwindigkeit auf die maximale Geschwindigkeit
            if (Math.abs(playerVelocityY) > maxSpeed) {
                playerVelocityY = Math.signum(playerVelocityY) * maxSpeed;
            }
        } else {
            // Verzögere die Bewegung, wenn keine Eingabe erfolgt
            playerVelocityY *= 0.9;  // Dämpfung der Bewegung
        }

        // Bewege den Spieler basierend auf der berechneten Geschwindigkeit
        player.setX(player.getX() + playerVelocityX);
        player.setY(player.getY() + playerVelocityY);
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
