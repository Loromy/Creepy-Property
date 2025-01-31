package theCreepyProperty.main;


import javafx.scene.image.ImageView;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.entity.Player;
import theCreepyProperty.scenes.GameScene;

import javafx.scene.shape.Rectangle;

public class CollisionChecker {
    private final GameScene scene;
    private final MapCreate mapCreate;

    public CollisionChecker(GameScene scene) {
        this.scene = scene;
        this.mapCreate = scene.getMapCreate();

    }

    public void checkCollision(Player player, double nextX, double nextY) {
        Rectangle futurePlayer = new Rectangle(nextX, nextY, player.entity_size_X, player.entity_size_Y);

        for (int i = 0; i < mapCreate.getWallList().size(); i++) {
            Rectangle wall = mapCreate.getWallList().get(i).getRWall();

            if (futurePlayer.intersects(wall.getBoundsInLocal()) && mapCreate.getWallList().get(i).getPlayer_block_collision()) {
                player.collision_on = true;
                return;
            }
        }

        // Items
        for (int i = 0; i < mapCreate.getItemList().size(); i++) {
            ImageView item = mapCreate.getItemList().get(i).getIItem();

            if (futurePlayer.intersects(item.getBoundsInLocal()) && mapCreate.getItemList().get(i).getPlayer_block_collision()) {
                player.keys_eingesammelt++;

                scene.pGameChildrenRemove(mapCreate.getItemList().get(i).getIItem()); // remove Item from Pane
//                mapCreate.getItemList().get(i).getIItem().setX(-100); // position moved
//                mapCreate.getItemList().get(i).getIItem().setY(-100); // position Moved

                mapCreate.getItemList().remove(i);

                this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeyEingesammelt()); // Gui component update
                return;
            }
        }

        // Doors
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            ImageView door = mapCreate.getDoorList().get(i).getIDoor();

            if (futurePlayer.intersects(door.getBoundsInLocal()) && mapCreate.getDoorList().get(i).getPlayer_block_collision()) {
                if (player.keys_eingesammelt >= 3) {
                    this.scene.getGameWin().triggerGameWin();
                }

                return;
            }
        }
    }
}
