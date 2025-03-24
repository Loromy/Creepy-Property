package theCreepyProperty.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vacuum extends Object {
    private final ImageView ivVAcuum = new ImageView();

    public Vacuum(int x, int y, int width, int height, String texture) {
        System.out.println(".............................Key..............................");
        // Parameterwerte setzen und Wand erstellen
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createVacuum();
    }

    private void createVacuum() {
        this.ivVAcuum.setImage(new Image(this.texture));
        this.ivVAcuum.setX(this.positionX);
        this.ivVAcuum.setY(this.positionY);
        this.ivVAcuum.setFitWidth(this.width);
        this.ivVAcuum.setFitHeight(this.height);
    }

    public void startVacuum() {

    }

    // Getter Methoden
    public ImageView getIVacuum() {
        return ivVAcuum;
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
