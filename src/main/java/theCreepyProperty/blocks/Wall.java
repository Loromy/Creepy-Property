package theCreepyProperty.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Block{
    private Rectangle r_Wall;

    public Wall() {
        setDefaultValues();
        createWall();
    }

    private void createWall() {
        this.r_Wall = new Rectangle(positionX, positionY, width, height);
        setWall_color(); // Farbe setzen
    }

    // set methode
    private void setDefaultValues() {
        positionX = 100;
        positionY = 100;
        width = 50; // Korrigiert, um konsistent zu sein
        height = 50; // Korrigiert, um konsistent zu sein
        color = "black";
        System.out.println("[System]: Wall defaultValues set ✔"); //✖
    }

    public void setWall_color(String color) {
        this.r_Wall.setFill(Color.web(color));
    }
    public void setWall_color() {
        this.r_Wall.setFill(Color.web("black"));
    }

    // get methode
    public Rectangle getWall() {
        return this.r_Wall; // JavaFX-Rectangle wird zurückgegeben
    }

    public int getX(){
        return this.positionX;
    }

    public int getY(){
        return this.positionY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getPlayer_block_collision() {
        return player_block_collision;
    }
}
