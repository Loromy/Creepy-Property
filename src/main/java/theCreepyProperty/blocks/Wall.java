package theCreepyProperty.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Wall extends Object {
    private Rectangle rWall;
    private final ArrayList<Wall> wallList = new ArrayList<>(); // Statische Liste für alle Wände

    public Wall(int x, int y, int width, int height, String texture) {
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createWall();
        this.wallList.add(this); // Wand zur statischen Liste hinzufügen
        //System.out.println("wallListe: " + wallList.size());//todo Entfernen Test
    }

    private void createWall() {
        this.rWall = new Rectangle(this.positionX, this.positionY, this.width, this.height);
        this.rWall.setFill(Color.web(this.texture));
    }

    // Getter für Rectangle
    public ArrayList<Wall> getWallListe() {
        return this.wallList;
    }

    public Rectangle getRWall() {
        return rWall;
    }

    // Getter für Position und Dimension
    public int getX() {
        return this.positionX;
    }

    public int getY() {
        return this.positionY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public String getTexture() {
        return this.texture;
    }

    public boolean getPlayer_block_collision() {
        return player_block_collision;
    }
}
