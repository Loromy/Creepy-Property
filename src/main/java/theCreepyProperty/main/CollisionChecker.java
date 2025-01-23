package theCreepyProperty.main;

import theCreepyProperty.Map.LevelData;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CollisionChecker {
    private final GUI gui;
    private final LevelData levelData;
    private final Wall wall;
    public CollisionChecker(GUI gui) {
        this.gui = gui;
        this.levelData = gui.getLevelData();
        this.wall = gui.getWall();
    }

    public void checkTile(Player player, ArrayList<LevelData.LevelDataWall> walls, MapCreate mapCreate) {
        // Erhalte die Bounding Box des Players
        double playerMinX = player.i_player.getX();
        double playerMinY = player.i_player.getY();
        double playerMaxX = playerMinX + player.entity_size_X;
        double playerMaxY = playerMinY + player.entity_size_Y;

        // Spieler Kollision zurücksetzen
        player.collision_on = false;

        // Iteriere durch alle Wände
        for (int i = 0; i < walls.size() ; i++) {
            // Erhalte die Bounding Box der aktuellen Wand
            double wallMinX = this.levelData.getWalls().get(i).getX();
            double wallMinY = this.levelData.getWalls().get(i).getY();
            double wallMaxX = wallMinX + this.levelData.getWalls().get(i).getWidth();
            double wallMaxY = wallMinY + this.levelData.getWalls().get(i).getHeight();

            // Prüfe auf Kollisionen in jede Richtung
            boolean isWallLeft = playerMinX <= wallMaxX && playerMinX >= wallMinX && playerMaxY > wallMinY && playerMinY < wallMaxY;
            boolean isWallRight = playerMaxX >= wallMinX && playerMaxX <= wallMaxX && playerMaxY > wallMinY && playerMinY < wallMaxY;
            boolean isWallAbove = playerMinY <= wallMaxY && playerMinY >= wallMinY && playerMaxX > wallMinX && playerMinX < wallMaxX;
            boolean isWallBelow = playerMaxY >= wallMinY && playerMaxY <= wallMaxY && playerMaxX > wallMinX && playerMinX < wallMaxX;

            // Prüfe die Richtung des Spielers und setze Kollisionsstatus
            switch (player.direction) {
                case "up":
                    if (mapCreate.getWall().getPlayer_block_collision() && isWallAbove) {
                        player.collision_on = true;
                        return; // Stoppe, da eine Kollision erkannt wurde
                    }
                    break;
                case "down":
                    if (mapCreate.getWall().getPlayer_block_collision() && isWallBelow) {
                        player.collision_on = true;
                        return;
                    }
                    break;
                case "left":
                    if (mapCreate.getWall().getPlayer_block_collision() && isWallLeft) {
                        player.collision_on = true;
                        return;
                    }
                    break;
                case "right":
                    if (mapCreate.getWall().getPlayer_block_collision() && isWallRight) {
                        player.collision_on = true;
                        return;
                    }
                    break;
            }
        }
    }
}
