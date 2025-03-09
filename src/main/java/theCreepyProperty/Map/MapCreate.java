package theCreepyProperty.Map;

import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

import java.util.ArrayList;

public class MapCreate {

    private Wall wall;
    private Item item;
    private Door door;
    private Ghost ghost;
    private GUI gui;
    private GameScene gameScene;
    private LevelData levelData;

    private final ArrayList<Wall> wallList = new ArrayList<>();
    private final ArrayList<Item> itemList = new ArrayList<>();
    private final ArrayList<Door> doorList = new ArrayList<>();
    private final ArrayList<Ghost> ghostList = new ArrayList<>();

    private int netToCollectKeys = 0;
    private boolean collision_on = true;

    public MapCreate() {
        System.out.println(".............................MapCreate..............................");
    }

    public void createMap(GUI gui, GameScene scene, LevelData levelData){

        this.gui = gui;
        this.gameScene = scene;
        this.wall = this.gameScene.getWall();
        this.item = this.gameScene.getItem();
        this.door = this.gameScene.getDoor();
        this.ghost = this.gameScene.getGhost();
        this.levelData = levelData;

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());
            this.wallList.add(this.wall);
            this.gameScene.pGameItemChildren(this.wall.getRWall());

            if (this.levelData.getWalls().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Walls created");
            }
        }

        for (int i = 0; i < this.levelData.getItems().size(); i++) {
            this.netToCollectKeys++;

            this.item = new Item(levelData.getItems().get(i).getX(), levelData.getItems().get(i).getY(), levelData.getItems().get(i).getWidth(), levelData.getItems().get(i).getHeight(), levelData.getItems().get(i).getTexture());
            this.itemList.add(this.item);
            this.gameScene.pGameItemChildren(this.item.getIItem());
            if (this.levelData.getItems().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Items created");
            }
        }

        for (int i = 0; i < this.levelData.getDoors().size(); i++) {

            this.door = new Door(levelData.getDoors().get(i).getX(), levelData.getDoors().get(i).getY(), levelData.getDoors().get(i).getWidth(), levelData.getDoors().get(i).getHeight(), levelData.getDoors().get(i).getTexture());
            this.doorList.add(this.door);
            this.gameScene.pGameItemChildren(this.door.getIvDoor());

            if (this.levelData.getDoors().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Doors created");
            }
        }

        for (int i = 0; i < this.levelData.getGhosts().size(); i++) {

            this.ghost = new Ghost(this.gui, levelData.getGhosts().get(i).getX(), levelData.getGhosts().get(i).getY(), levelData.getGhosts().get(i).getWidth(), levelData.getGhosts().get(i).getHeight());
            this.ghostList.add(this.ghost);
            this.gameScene.pGameGhostsChildren(this.ghost.getSolidAria());
            this.gameScene.pGameGhostsChildren(this.ghost.draw());

            if (this.levelData.getGhosts().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Ghosts created");
            }
        }
    }

    public void triggerCollisionRWSettings(GameScene gameScene) {
        if (!collision_on) {
            for (int i = 0; i < this.gameScene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(true);
            }
            gameScene.getMenu().getSettings().getButton5().setText("Collision [ON]");
            this.collision_on = true;
        } else {
            for (int i = 0; i < this.gameScene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(false);
            }
            gameScene.getMenu().getSettings().getButton5().setText("Collision [OFF]");
            this.collision_on = false;
        }
    }

    public void triggerCollision(ReadWriteSettings readWriteSettings, GameScene gameScene) {
        if (!collision_on) {
            for (int i = 0; i < gameScene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(true);
            }
            if(gameScene.getMenu() != null) {
                gameScene.getMenu().getSettings().getButton5().setText("Collision [ON]");
            }
            readWriteSettings.updateSetting("collision", 1);
            this.collision_on = true;
        } else {
            for (int i = 0; i < gameScene.getMapCreate().getWallList().size(); i++) {
                this.wallList.get(i).setPlayer_block_collision(false);
            }
            if(gameScene.getMenu() != null) {
                gameScene.getMenu().getSettings().getButton5().setText("Collision [OFF]");
            }
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

    public ArrayList<Ghost> getGhostList() {
        return this.ghostList;
    }

    public void setCollision_on(boolean value) {
        this.collision_on = value;
    }
}