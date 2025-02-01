package theCreepyProperty.main;


import javafx.scene.image.ImageView;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.entity.Player;
import theCreepyProperty.scenes.GameScene;

import javafx.scene.shape.Rectangle;

public class CollisionChecker {
    private final GameScene scene;
    private final MapCreate mapCreate;
    private SoundPlayer soundPlayer;

    private boolean isPlayed = false;

    public CollisionChecker(GameScene scene) {
        this.scene = scene;
        this.mapCreate = scene.getMapCreate();

    }

    public void checkCollision(Player player, double nextX, double nextY) {
        Rectangle futurePlayer = new Rectangle(nextX, nextY, player.entity_size_X, player.entity_size_Y);

        openDoorsInLevel(player);

        // Walls
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

                this.soundPlayer = new SoundPlayer("src/resources/sounds/itemCollect.wav");
                this.soundPlayer.play();

                scene.pGameChildrenRemove(mapCreate.getItemList().get(i).getIItem()); // remove Item from Pane
                mapCreate.getItemList().remove(i);

                this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeyEingesammelt()); // Gui component update
                return;
            }
        }

        // Doors
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            ImageView door = mapCreate.getDoorList().get(i).getIDoor();

            if (futurePlayer.intersects(door.getBoundsInLocal()) && mapCreate.getDoorList().get(i).getPlayer_block_collision()) {
                player.collision_on = true;

                if (mapCreate.getDoorList().get(i).getDoorOpen()) {
                    this.soundPlayer = new SoundPlayer("src/resources/sounds/youWin.wav");
                    this.soundPlayer.play();
                    this.soundPlayer = new SoundPlayer("src/resources/sounds/congratulations.wav");
                    this.soundPlayer.play();

                    this.scene.getGameWin().triggerGameWin();
                }

                return;
            }
        }
    }

    private void openDoorsInLevel(Player player) {
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            if (player.keys_eingesammelt >= this.mapCreate.getNetToCollectKeys() && !this.isPlayed) {
                this.isPlayed = true;
                this.soundPlayer = new SoundPlayer("src/resources/sounds/doorOpen.wav");
                this.soundPlayer.play();


                this.mapCreate.getDoorList().get(i).openDoor();
            }
        }
    }
}
