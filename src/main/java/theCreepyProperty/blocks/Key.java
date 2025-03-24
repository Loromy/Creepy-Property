package theCreepyProperty.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Key extends Object {
    private final ImageView ivKey = new ImageView();

    public Key(int x, int y, int width, int height, String texture) {
        System.out.println(".............................Key..............................");
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createKey();
    }

    private void createKey() {
        this.ivKey.setImage(new Image(this.texture));
        this.ivKey.setX(this.positionX);
        this.ivKey.setY(this.positionY);
        this.ivKey.setFitWidth(this.width);
        this.ivKey.setFitHeight(this.height);
    }

    // Getter Methoden
    public ImageView getIKey() {
        return ivKey;
    }

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
