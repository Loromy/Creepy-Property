package theCreepyProperty.Map;

import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.scenes.GameScene;

import java.util.ArrayList;

public class MapCreate {

    private Wall wall;
    private Item item;
    private Door door;
    private GameScene scene;
    private LevelData levelData;

    private final ArrayList<Wall> wallList = new ArrayList<>();
    private final ArrayList<Item> itemList = new ArrayList<>();
    private final ArrayList<Door> doorList = new ArrayList<>();

    private int netToCollectKeys = 0;
    private boolean collision_on = true;

    public void createMap(GameScene scene, LevelData levelData){

        this.scene = scene;
        this.wall = scene.getWall();
        this.item = scene.getItem();
        this.door = scene.getDoor();
        this.levelData = levelData;

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());
            this.wallList.add(this.wall);
            this.scene.pGameChildren(this.wall.getRWall());

            if (this.levelData.getWalls().size()-1 == i) {
                i++;
                System.out.println("[MapCreator]: " + i + " Walls created ✔");
            }
        }

        for (int i = 0; i < this.levelData.getItems().size(); i++) {
            this.netToCollectKeys++;

            this.item = new Item(levelData.getItems().get(i).getX(), levelData.getItems().get(i).getY(), levelData.getItems().get(i).getWidth(), levelData.getItems().get(i).getHeight(), levelData.getItems().get(i).getTexture());
            this.itemList.add(this.item);
            this.scene.pGameChildren(this.item.getIItem());
            if (this.levelData.getItems().size()-1 == i) {
                i++;
                System.out.println("[MapCreator]: " + i + " Items created ✔");
            }
        }

        for (int i = 0; i < this.levelData.getDoors().size(); i++) {

            this.door = new Door(levelData.getDoors().get(i).getX(), levelData.getDoors().get(i).getY(), levelData.getDoors().get(i).getWidth(), levelData.getDoors().get(i).getHeight(), levelData.getDoors().get(i).getTexture());
            this.doorList.add(this.door);
            this.scene.pGameChildren(this.door.getIDoor());

            if (this.levelData.getDoors().size()-1 == i) {
                i++;
                System.out.println("[MapCreator]: " + i + " Doors created ✔");
            }
        }
    }

    public void triggerCollisionRWSettings(GameScene gameScene) {
        if (!collision_on) {
            for (int i = 0; i < this.scene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(true);
            }
            gameScene.getMenu().getSettings().getButton5().setText("Collision [ON]");
            this.collision_on = true;
        } else {
            for (int i = 0; i < this.scene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(false);
            }
            gameScene.getMenu().getSettings().getButton5().setText("Collision [OFF]");
            this.collision_on = false;
        }
    }

    public void triggerCollision(ReadWriteSettings readWriteSettings) {
        if (!collision_on) {
            for (int i = 0; i < this.scene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(true);
            }
            this.scene.getMenu().getSettings().getButton5().setText("Collision [ON]");
            readWriteSettings.updateSetting("collision", 1);
            this.collision_on = true;
        } else {
            for (int i = 0; i < this.scene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(false);
            }
            this.scene.getMenu().getSettings().getButton5().setText("Collision [OFF]");
            readWriteSettings.updateSetting("collision", 0);
            this.collision_on = false;
        }
    }

    public Wall getWall() {
        return this.wall;
    }

    public Item getItem() {
        return this.item;
    }

    public int getNetToCollectKeys() {
        return netToCollectKeys;
    }

    public ArrayList<Wall> getWallList() {
        return this.wallList;
    }

    public ArrayList<Item> getItemList() {
        return this.itemList;
    }

    public ArrayList<Door> getDoorList() {
        return this.doorList;
    }

    public void setCollision_on(boolean value) {
        this.collision_on = value;
    }
}