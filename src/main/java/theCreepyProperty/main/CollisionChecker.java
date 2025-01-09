package theCreepyProperty.main;

import theCreepyProperty.entity.Entity;
import theCreepyProperty.entity.Player;

public class CollisionChecker {
    private final GUI gui;

    public CollisionChecker(GUI gui){
        this.gui = gui;
    }

    public void checkTile(Player player) {

//        int entity_left_world_X = entity.player_world_X + entity.solid_player_aria.x;
//        int entity_right_world_X = entity.player_world_X + entity.solid_player_aria.x + entity.solid_player_aria.width;
//        int entity_top_world_Y = entity.player_world_Y + entity.solid_player_aria.y;
//        int entity_bottom_world_Y = entity.player_world_Y + entity.solid_player_aria.y + entity.solid_player_aria.height;
//
//        int entity_left_col = entity_left_world_X / gp.tile_size;
//        int entity_right_col = entity_right_world_X / gp.tile_size;
//        int entity_top_row = entity_top_world_Y / gp.tile_size;
//        int entity_bottom_row = entity_bottom_world_Y / gp.tile_size;
//
//        int tile_num_1, tile_num_2;

        // Erhalte die Bounding Box des Players
        double playerMinX = this.gui.getPlayer().i_player.getX();
        double playerMinY = this.gui.getPlayer().i_player.getY();
        double playerMaxX = playerMinX + this.gui.getPlayer().entity_size_X;
        double playerMaxY = playerMinY + this.gui.getPlayer().entity_size_Y;

        // Erhalte die Bounding Box der Wand
        double wallMinX = this.gui.getWall().getX();
        double wallMinY = this.gui.getWall().getY();
        double wallMaxX = wallMinX + this.gui.getWall().getWidth();
        double wallMaxY = wallMinY + this.gui.getWall().getHeight();

//        // Überprüfe, ob sich die Rechtecke überschneiden
//        boolean tester = playerMaxX > wallMinX && playerMinX < wallMaxX && playerMaxY > wallMinY && playerMinY < wallMaxY;
        // Prüfe auf Kollisionen in jede Richtung
        boolean isWallLeft = playerMinX <= wallMaxX && playerMinX >= wallMinX && playerMaxY > wallMinY && playerMinY < wallMaxY;
        boolean isWallRight = playerMaxX >= wallMinX && playerMaxX <= wallMaxX && playerMaxY > wallMinY && playerMinY < wallMaxY;
        boolean isWallAbove = playerMinY <= wallMaxY && playerMinY >= wallMinY && playerMaxX > wallMinX && playerMinX < wallMaxX;
        boolean isWallBelow = playerMaxY >= wallMinY && playerMaxY <= wallMaxY && playerMaxX > wallMinX && playerMinX < wallMaxX;


        switch (player.direction) {
            case "up":
                if (this.gui.getWall().getPlayer_block_collision() && isWallAbove) {
                    this.gui.getPlayer().collision_on = true;
                    this.gui.getWall().setWall_color("red");
                }
                break;
            case "down":
                if (this.gui.getWall().getPlayer_block_collision() && isWallBelow) {
                    this.gui.getPlayer().collision_on = true;
                    this.gui.getWall().setWall_color("green");
                }
                break;
            case "left":
                if (this.gui.getWall().getPlayer_block_collision() && isWallLeft) {
                    this.gui.getPlayer().collision_on = true;
                    this.gui.getWall().setWall_color("blue");
                }
                break;
            case "right":
                if (this.gui.getWall().getPlayer_block_collision() && isWallRight) {
                    this.gui.getPlayer().collision_on = true;
                    this.gui.getWall().setWall_color("yellow");
                }
                break;
        }
    }
}
