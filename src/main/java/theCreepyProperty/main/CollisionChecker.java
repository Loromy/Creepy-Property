package theCreepyProperty.main;

import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.entity.Player;
import theCreepyProperty.scenes.GameScene;

import javafx.scene.image.ImageView;
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
        if (keysToCollect <= this.mapCreate.getKeyList().size()) {
            this.keysToCollect = this.mapCreate.getKeyList().size();
            this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeysCollected() + "/" + keysToCollect); // Gui component update
        }

        openDoorsInLevel(player);

        // check Player-Collision with Walls
        for (int i = 0; i < mapCreate.getWallList().size(); i++) {
            Rectangle wall = mapCreate.getWallList().get(i).getRWall();

            if (futurePlayer.intersects(wall.getBoundsInLocal()) && mapCreate.getWallList().get(i).getPlayer_block_collision()) {
                player.collision_on = true;
                return;
            }
        }

        // check Player-Collision with Keys
        for (int i = 0; i < mapCreate.getKeyList().size(); i++) {
            ImageView key = mapCreate.getKeyList().get(i).getIKey();

            if (futurePlayer.intersects(key.getBoundsInLocal()) && mapCreate.getKeyList().get(i).getPlayer_block_collision()) {
                player.keys_collected++;

                this.soundPlayer = new SoundPlayer("src/resources/sounds/key-collect.wav");
                this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                scene.pGameItemChildrenRemove(mapCreate.getKeyList().get(i).getIKey()); // remove Key from Pane
                mapCreate.getKeyList().remove(i);

                this.scene.getGuiComponents().getL_keys().setText("Keys: " + player.getKeysCollected() + "/" + keysToCollect); // Gui component update

                this.scene.getGuiComponents().collectKey(keysToCollect);
                return;
            }
        }

        // check Player-Collision with Vacuums
        for (int i = 0; i < mapCreate.getVacuumsList().size(); i++) {
            ImageView vacuum = mapCreate.getVacuumsList().get(i).getIVacuum();

            if (futurePlayer.intersects(vacuum.getBoundsInLocal()) && mapCreate.getVacuumsList().get(i).getPlayer_block_collision()) {
                this.soundPlayer = new SoundPlayer("src/resources/sounds/item-equip.wav");
                this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                this.scene.getPlayer().startVacuum();

                scene.pGameItemChildrenRemove(mapCreate.getVacuumsList().get(i).getIVacuum()); // remove Item from Pane
                mapCreate.getVacuumsList().remove(i);

                return;
            }
        }

        // check Player-Collision with Door
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            ImageView door = mapCreate.getDoorList().get(i).getIvDoor();

            if (futurePlayer.intersects(door.getBoundsInLocal()) && mapCreate.getDoorList().get(i).getPlayer_block_collision()) {
                player.collision_on = true;

                if (mapCreate.getDoorList().get(i).getDoorOpen()) {
                    this.soundPlayer = new SoundPlayer("src/resources/sounds/youWin.wav");
                    this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                    this.soundPlayer.play();

                    this.scene.getGameWin().triggerGameWin();
                }
                return;
            }
        }
    }

    // Checking collision with Wall
    public boolean isCollidingWithWall(Player player, double nextX, double nextY, int indexWall) {
        boolean colliding = false;

        Rectangle futurePlayer = new Rectangle(nextX, nextY, player.entity_size_X, player.entity_size_Y);

        Rectangle wall = mapCreate.getWallList().get(indexWall).getRWall();

        if (futurePlayer.intersects(wall.getBoundsInLocal()) && mapCreate.getWallList().get(indexWall).getPlayer_block_collision()) {
            colliding = true;
        }

        return colliding;
    }

    // Opening Doors if all Keys Collected
    private void openDoorsInLevel(Player player) {
        for (int i = 0; i < mapCreate.getDoorList().size(); i++) {
            if (player.keys_collected >= this.mapCreate.getNetToCollectKeys() && !this.isPlayed) {
                this.isPlayed = true;
                this.scene.getGuiComponents().get_hBox_keys().setStyle("-fx-background-color: rgba(3, 59, 1, 0.8);");

                this.soundPlayer = new SoundPlayer("src/resources/sounds/doorOpen.wav");
                this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
                this.soundPlayer.play();

                this.mapCreate.getDoorList().get(i).openDoor();
            }
        }
    }

    // Delete Variables
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