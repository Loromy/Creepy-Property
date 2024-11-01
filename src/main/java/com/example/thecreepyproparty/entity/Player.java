package com.example.thecreepyproparty.entity;


import com.example.thecreepyproparty.main.GUI;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;




public class Player {
//    private Pane playerPane;
    private Rectangle player;
    private int x = 50;
    private int y = 50;


    public Player()  {
        player = new Rectangle(x, y, 16, 16);
        player.setFill(Color.BLUE);
        player.setStrokeWidth(3);

        System.out.println("[System]: Player created!"); // System Ausgabe
    }

    public Rectangle getPlayer(){
        return player;
    }

    public void setXY(int x, int y){
        player.setX(x);
        player.setY(y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
