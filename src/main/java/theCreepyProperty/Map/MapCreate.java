package theCreepyProperty.Map;

import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.main.GUI;

public class MapCreate {

    private Wall wall;
    private Item item;
    private GUI gui;
    private LevelData levelData;

    public void createMap(GUI gui, LevelData levelData){

        this.gui = gui;
        this.wall = gui.getWall();
        this.item = gui.getItem();
        this.levelData = levelData;

        for (int i = 0; i < this.levelData.getWalls().size(); i++) {
            System.out.println("[Map Creator]: Wall id " + i);

            this.wall = new Wall(levelData.getWalls().get(i).getX(), levelData.getWalls().get(i).getY(), levelData.getWalls().get(i).getWidth(), levelData.getWalls().get(i).getHeight(), levelData.getWalls().get(i).getTexture());
            this.gui.pGameChildren(this.wall.getRWall());
        }

        for (int i = 0; i < this.levelData.getItems().size(); i++) {
            System.out.println("[Map Creator]: Item id " + i);

            this.item = new Item(levelData.getItems().get(i).getX(), levelData.getItems().get(i).getY(), levelData.getItems().get(i).getWidth(), levelData.getItems().get(i).getHeight(), levelData.getItems().get(i).getTexture());
            this.gui.pGameChildren(this.item.getRItem());
        }
    }

    public Wall getWall() {
        return this.wall;
    }

    public Item getItem() {
        return this.item;
    }
}