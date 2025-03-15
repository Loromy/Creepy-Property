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

    private ArrayList<Wall> wallList = new ArrayList<>();
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<Door> doorList = new ArrayList<>();
    private ArrayList<Ghost> ghostList = new ArrayList<>();

    private int netToCollectKeys = 0;

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

    public void deleteMapCreate() {
        System.out.println("⚠ [MapCreate]: Alle Referenzen werden gelöscht...");

        // Löschen der GUI und GameScene Referenzen
        if (this.gui != null) {
            this.gui = null; // GUI auf null setzen
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        // Löschen der einzelnen Objekte wie Wall, Item, Door, Ghost
        if (this.wall != null) {
            this.wall = null;
        }

        if (this.item != null) {
            this.item = null;
        }

        if (this.door != null) {
            this.door = null;
        }

        if (this.ghost != null) {
            this.ghost = null;
        }

        // Löschen der Listen
        if (this.wallList != null) {
            this.wallList.clear(); // Liste der Wände leeren
            this.wallList = null; // Liste auf null setzen
        }

        if (this.itemList != null) {
            this.itemList.clear(); // Liste der Items leeren
            this.itemList = null; // Liste auf null setzen
        }

        if (this.doorList != null) {
            this.doorList.clear(); // Liste der Türen leeren
            this.doorList = null; // Liste auf null setzen
        }

        if (this.ghostList != null) {
            this.ghostList.clear(); // Liste der Geister leeren
            this.ghostList = null; // Liste auf null setzen
        }

        // Zurücksetzen von Statusvariablen
        this.netToCollectKeys = 0;

        // Falls es eine LevelData-Instanz gibt, könnte man auch hier eine delete-Methode aufrufen, falls erforderlich:
        if (this.levelData != null) {this.levelData.deleteLevelData();
            this.levelData.deleteLevelData();
            this.levelData = null;
        }

        // Garbage Collector anstoßen
        System.gc();

        System.out.println("✔ [MapCreate]: Speicherbereinigung durchgeführt.");
    }

}