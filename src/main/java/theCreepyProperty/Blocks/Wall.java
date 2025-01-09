package theCreepyProperty.Blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall {
    private Rectangle r_Wall;

    private int positionX;
    private int positionY;
    private int width;
    private int height;
    private String color;

    public Wall() {
        setDefaultValues();
        createWall();
    }

    private void setDefaultValues() {
        positionX = 50;
        positionY = 50;
        width = 50; // Korrigiert, um konsistent zu sein
        height = 50; // Korrigiert, um konsistent zu sein
        color = "black";
    }

    private void createWall() {
        this.r_Wall = new Rectangle(positionX, positionY, width, height);
        this.r_Wall.setFill(Color.web(color)); // Farbe setzen
    }

    public Rectangle getWall() {
        return this.r_Wall; // JavaFX-Rectangle wird zurückgegeben
    }
}
