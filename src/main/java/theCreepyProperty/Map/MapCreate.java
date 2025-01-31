package theCreepyProperty.Map;

import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.main.GUI;
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

    public void createMap(GameScene scene, LevelData levelData){

        this.scene = scene;
        this.wall = scene.getWall();
        this.item = scene.getItem();
        this.door = scene.getDoor();
        this.levelData = levelData;

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {
            //System.out.println("[Map Creator]: Wall id " + i);

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());
            this.wallList.add(this.wall);
            this.scene.pGameChildren(this.wall.getRWall());

            if (this.levelData.getWalls().size()-1 == i) {
                i++;
                System.out.println("[Map Creator]: " + i + " Walls created");
            }
        }

        for (int i = 0; i < this.levelData.getItems().size(); i++) {
            //System.out.println("[Map Creator]: Item id " + i);

            this.item = new Item(levelData.getItems().get(i).getX(), levelData.getItems().get(i).getY(), levelData.getItems().get(i).getWidth(), levelData.getItems().get(i).getHeight(), levelData.getItems().get(i).getTexture());
            this.itemList.add(this.item);
            this.scene.pGameChildren(this.item.getIItem());
            if (this.levelData.getItems().size()-1 == i) {
                i++;
                System.out.println("[Map Creator]: " + i + " Items created");
            }
        }

        for (int i = 0; i < this.levelData.getDoors().size(); i++) {
            //System.out.println("[Map Creator]: Door id " + i);

            this.door = new Door(levelData.getDoors().get(i).getX(), levelData.getDoors().get(i).getY(), levelData.getDoors().get(i).getWidth(), levelData.getDoors().get(i).getHeight(), levelData.getDoors().get(i).getTexture());
            this.doorList.add(this.door);
            this.scene.pGameChildren(this.door.getIDoor());

            if (this.levelData.getDoors().size()-1 == i) {
                i++;
                System.out.println("[Map Creator]: " + i + " Doors created");
            }
        }
    }

    public Wall getWall() {
        return this.wall;
    }

    public Item getItem() {
        return this.item;
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
}