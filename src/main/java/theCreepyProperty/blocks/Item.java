package theCreepyProperty.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item extends Object {
    private Image itemImage;
    private ImageView ivItem = new ImageView();

    public Item(int x, int y, int width, int height, String texture) {
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createItem();
        //this.itemList.add(this); // Wand zur statischen Liste hinzufügen
    }

    private void createItem() {
        this.ivItem.setImage(new Image(this.texture));
        this.ivItem.setX(this.positionX);
        this.ivItem.setY(this.positionY);
        this.ivItem.setFitWidth(this.width);
        this.ivItem.setFitHeight(this.height);
    }

    // Getter für Rectangle

    public ImageView getIItem() {
        return ivItem;
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

    public void setX(int x) {
        this.positionX = x;
    }

    public void setY(int y) {
        this.positionY = y;
    }
}
