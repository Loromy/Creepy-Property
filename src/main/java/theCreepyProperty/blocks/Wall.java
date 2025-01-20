package theCreepyProperty.blocks;

import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Wall extends Block {
    private Rectangle rWall;
    private final ArrayList<Wall> wallList = new ArrayList<>(); // Liste für alle Walls

    public Wall() {
        // Standardwerte setzen, falls keine Parameter übergeben werden.
        setDefaultValues();
        //createWall();
        wallList.add(this); // Zur Liste hinzufügen
        System.out.println("Walls: " + this.wallList.size());
    }

    public Wall(int x, int y, int width, int height, String texture) {
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        //createWallWithTexture();
        createWall();//todo Rectangel erstellen und werte übergeben
        wallList.add(this); // Zur Liste hinzufügen
    }

    private void createWall() {
        this.rWall = new Rectangle(this.positionX, this.positionY, this.width, this.height);
        this.rWall.setFill(Color.web(this.texture));
    }

    // Textur setzen
//    public void setWall_texture() {
//        this.r_Wall.setFill(Color.web(this.texture));
//    }

//    private void createWallWithTexture() {
//        this.r_Wall = new Rectangle(this.positionX, this.positionY, this.width, this.height);
//        setWall_texture();
//    }

    // Standardwerte setzen
    private void setDefaultValues() {
        positionX = 100;
        positionY = 100;
        width = 50;
        height = 50;
        texture = "black";
        System.out.println("[System]: LevelDataWall defaultValues set ✔");
    }



//    //TODO Textur setzen
//    public void setWall_texture(String texture) {
//        try {
//            Image img = new Image(texture); // Lade das Bild basierend auf dem übergebenen Pfad
//            this.r_Wall.setFill(new ImagePattern(img));
//        } catch (Exception e) {
//            System.err.println("[Error]: Failed to load texture - " + texture);
//            setWall_texture(); // Fallback auf Standardfarbe
//        }
//    }
    // Setter
//    public void setR_Wall(int x, int y, int width, int height, String texture){
//        //this.r_Wall = new Rectangle();
//        Rectangle wall = new Rectangle(this.positionX, this.positionY, this.width, this.height);
//        wall.setFill(Color.web(texture));
//    }


    // Getter für Rectangle
    public Rectangle getWall() {
        return this.rWall;
    }

//    public void createWall() {
//        this.r_Wall =  new Rectangle(this.positionX ,this.positionY, this.width, this.height);
//    }

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

    // Statische Methode, um die Liste aller Wände zurückzugeben
    public List<Wall> getWallList() {
        return this.wallList;
    }
}
