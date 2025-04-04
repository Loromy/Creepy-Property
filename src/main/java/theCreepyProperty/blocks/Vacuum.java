package theCreepyProperty.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vacuum extends Object {
    private final ImageView ivVacuum = new ImageView();

    public Vacuum(int x, int y, int width, int height, String texture) {
        System.out.println(".............................Key..............................");
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createVacuum();
    }

    private void createVacuum() {
        this.ivVacuum.setImage(new Image(this.texture));
        this.ivVacuum.setX(this.positionX);
        this.ivVacuum.setY(this.positionY);
        this.ivVacuum.setFitWidth(this.width);
        this.ivVacuum.setFitHeight(this.height);
    }

    // Getter Methoden
    public ImageView getIVacuum() {
        return ivVacuum;
    }

    public int getX() {
        return this.positionX;
    }

    public int getY() {
        return this.positionY;
    }

    public boolean getPlayer_block_collision() {
        return player_block_collision;
    }

    // Setter Methoden
    public void setX(int x) {
        this.positionX = x;
    }

    public void setY(int y) {
        this.positionY = y;
    }
}