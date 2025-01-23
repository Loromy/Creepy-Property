package theCreepyProperty.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Item extends Object {
    private Rectangle rItem;
    private final ArrayList<Item> itemList = new ArrayList<>(); // Statische Liste für alle Wände

    public Item(int x, int y, int width, int height, String texture) {
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.texture = texture;
        createItem();
        this.itemList.add(this); // Wand zur statischen Liste hinzufügen
    }

    private void createItem() {
        this.rItem = new Rectangle(this.positionX, this.positionY, this.width, this.height);
        this.rItem.setFill(Color.web(this.texture));
    }

    // Getter für Rectangle
    public ArrayList<Item> getItemListe() {
        return this.itemList;
    }

    public Rectangle getRItem() {
        return rItem;
    }

    // Getter für Position und Dimension
    public int getX() {
        return this.positionX;
    }

    public int getY() {
        return this.positionY;
    }

    public String getTexture() {
        return this.texture;
    }

    public boolean getPlayer_block_collision() {
        return player_block_collision;
    }
}
