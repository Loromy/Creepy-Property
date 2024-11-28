package theCreepyProperty.entity;

import javafx.scene.image.Image;

import java.awt.*;

public class Entity {
    public double entity_world_X, entity_world_Y;
    public double speed;
    public Image up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4;
    public String direction;
    public int sprite_counter = 0;
    public int sprite_num = 1;
    public Rectangle solid_player_aria;
    public int solid_aria_default_x, solid_aria_default_y;
    public boolean collision_on = false;
}
