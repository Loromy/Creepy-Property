package theCreepyProperty.entity;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;


public class Entity {
    public double entity_world_X, entity_world_Y;
    public double entity_size_X, entity_size_Y;
    public double speed;
    public int keys_eingesammelt = 0;
    public Image up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4, overlay;
    public String direction;
    public double sprite_counter = 0;
    public int sprite_num = 1;
    public Rectangle solid_area;
    public boolean collision_on = false;
}
