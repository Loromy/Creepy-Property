package theCreepyProperty.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Object {
    private Rectangle rWall;

    public Wall(int x, int y, int width, int height, String texture) {
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createWall();

    }

    private void createWall() {
        this.rWall = new Rectangle(this.positionX, this.positionY, this.width, this.height);
        this.rWall.setFill(Color.web(this.texture));
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
