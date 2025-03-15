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
    private int keysToCollect = 0;

    public CollisionChecker(GameScene scene) {
        System.out.println(".............................CollisionChecker..............................");
        this.scene = scene;
        this.mapCreate = scene.getMapCreate();
    }

    public void checkCollision(Player player, double nextX, double nextY) {
        Rectangle futurePlayer = new Rectangle(nextX, nextY, player.entity_size_X, player.entity_size_Y);

        // guiComponents key background green
        if (keysToCollect <= this.mapCreate.getItemList().size()) {
            this.keysToCollect = this.mapCreate.getItemList().size();
            this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeyEingesammelt() + "/" + keysToCollect); // Gui component update
        }

        openDoorsInLevel(player);

        // Walls
        for (int i = 0; i < mapCreate.getWallList().size(); i++) {
            Rectangle wall = mapCreate.getWallList().get(i).getRWall();

            if (futurePlayer.intersects(wall.getBoundsInLocal()) && mapCreate.getWallList().get(i).getPlayer_block_collision()) {
                player.collision_on = true; //TODO deactivate Collision false
                return;
            }
        }

        // Items
        for (int i = 0; i < mapCreate.getItemList().size(); i++) {
            ImageView item = mapCreate.getItemList().get(i).getIItem();

            if (futurePlayer.intersects(item.getBoundsInLocal()) && mapCreate.getItemList().get(i).getPlayer_block_collision()) {
                player.keys_eingesammelt++;

                this.soundPlayer = new SoundPlayer("src/resources/sounds/key-collect.wav");
                this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                scene.pGameItemChildrenRemove(mapCreate.getItemList().get(i).getIItem()); // remove Item from Pane
                mapCreate.getItemList().remove(i);

                this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeyEingesammelt() + "/" + keysToCollect); // Gui component update

                this.scene.getGuiComponents().collectKey(keysToCollect);
                return;
            }
        }

        // Doors
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            ImageView door = mapCreate.getDoorList().get(i).getIvDoor();

            if (futurePlayer.intersects(door.getBoundsInLocal()) && mapCreate.getDoorList().get(i).getPlayer_block_collision()) {
                player.collision_on = true;

                if (mapCreate.getDoorList().get(i).getDoorOpen()) {
                    this.soundPlayer = new SoundPlayer("src/resources/sounds/youWin.wav");
                    this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                    this.soundPlayer.play();
//                    this.soundPlayer = new SoundPlayer("src/resources/sounds/congratulations.wav");
//                    this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
//                    this.soundPlayer.play();

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
                this.scene.getGuiComponents().gethBox_keys().setStyle("-fx-background-color: rgba(3, 59, 1, 0.8);");

                this.soundPlayer = new SoundPlayer("src/resources/sounds/doorOpen.wav");
                this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                this.mapCreate.getDoorList().get(i).openDoor();
            }
        }
    }

    public void deleteCollisionChecker() {
        System.out.println("⚠ [CollisionChecker]: Alle Referenzen werden gelöscht...");

        // Entferne den SoundPlayer, falls er existiert
        if (this.soundPlayer != null) {
            this.soundPlayer.stop();
            this.soundPlayer = null;
        }

        // Setze keysToCollect zurück
        this.keysToCollect = 0;

        // Setze den Status von isPlayed zurück
        this.isPlayed = false;

        // Führe Garbage Collection aus
        System.gc();
        System.out.println("✔ [CollisionChecker]: Speicherbereinigung durchgeführt.");
    }
}
