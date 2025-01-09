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

        // Überprüfe, ob sich die Rechtecke überschneiden
        boolean tester = playerMaxX > wallMinX && playerMinX < wallMaxX && playerMaxY > wallMinY && playerMinY < wallMaxY;


        switch (player.direction) {
            case "up":
                System.out.println("up");
                if (gui.getWall().getPlayer_block_collision()) {
                    player.collision_on = true;
                }
                break;
            case "down":
//                entity_bottom_row = (entity_bottom_world_Y + entity.speed) / gp.tile_size;
//                tile_num_1 = gp.tile_manager.level_tile_number[entity_left_col][entity_bottom_row];
//                tile_num_2 = gp.tile_manager.level_tile_number[entity_right_col][entity_bottom_row];
//                if (gp.tile_manager.tile[tile_num_1].player_tile_collision || gp.tile_manager.tile[tile_num_2].player_tile_collision) {
//                    entity.collision_on = true;
//                }
                break;
            case "left":
//                entity_left_col = (entity_left_world_X - entity.speed) / gp.tile_size;
//                tile_num_1 = gp.tile_manager.level_tile_number[entity_left_col][entity_top_row];
//                tile_num_2 = gp.tile_manager.level_tile_number[entity_left_col][entity_bottom_row];
//                if (gp.tile_manager.tile[tile_num_1].player_tile_collision || gp.tile_manager.tile[tile_num_2].player_tile_collision) {
//                    entity.collision_on = true;
//                }
                break;
            case "right":
//                entity_right_col = (entity_right_world_X + entity.speed) / gp.tile_size;
//                tile_num_1 = gp.tile_manager.level_tile_number[entity_right_col][entity_top_row];
//                tile_num_2 = gp.tile_manager.level_tile_number[entity_right_col][entity_bottom_row];
//                if (gp.tile_manager.tile[tile_num_1].player_tile_collision || gp.tile_manager.tile[tile_num_2].player_tile_collision) {
//                    entity.collision_on = true;
//                }
                break;
        }
    }
}
