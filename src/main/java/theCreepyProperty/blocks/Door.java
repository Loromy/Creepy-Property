package theCreepyProperty.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import theCreepyProperty.checker.FileCheck;

public class Door extends Object {
    private final ImageView ivDoor = new ImageView();

    private Boolean doorOpen = false;

    public Door(int x, int y, int width, int height, String texture) {
        System.out.println(".............................Door..............................");
        this.positionX = x;
        this.positionY = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        createDoor();
    }

    private void createDoor() {
        this.ivDoor.setImage(new Image(this.texture));
        this.ivDoor.setX(this.positionX);
        this.ivDoor.setY(this.positionY);
        this.ivDoor.setFitWidth(this.width);
        this.ivDoor.setFitHeight(this.height);
    }

    public void openDoor() {
        this.ivDoor.setImage(new Image(new FileCheck().checkImage("Door","file:src/resources/textures/items/DoorOpen.png")));
        this.doorOpen = true;
    }

    // Getter Methoden
    public ImageView getIvDoor() {
        return ivDoor;
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

    public Boolean getDoorOpen() {
        return doorOpen;
    }

    //Setter Methoden
    public void setX(int x) {
        this.positionX = x;
    }

    public void setY(int y) {
        this.positionY = y;
    }
}