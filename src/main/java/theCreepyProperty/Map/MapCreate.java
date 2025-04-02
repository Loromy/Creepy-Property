package theCreepyProperty.Map;

import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Key;
import theCreepyProperty.blocks.Vacuum;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

import java.util.ArrayList;

public class MapCreate {

    private Wall wall;
    private Key key;
    private Vacuum vacuum;
    private Door door;
    private Ghost ghost;
    private GUI gui;
    private GameScene gameScene;
    private LevelData levelData;

    private ArrayList<Wall> wallList = new ArrayList<>();
    private ArrayList<Key> keyList = new ArrayList<>();
    private ArrayList<Vacuum> vacuumsList = new ArrayList<>();
    private ArrayList<Door> doorList = new ArrayList<>();
    private ArrayList<Ghost> ghostList = new ArrayList<>();

    private int netToCollectKeys = 0;
    private boolean collision_on = true;
    private boolean ghostMoving_on = true;

    public MapCreate() {
        System.out.println(".............................MapCreate..............................");
    }

    public void createMap(GUI gui, GameScene scene, LevelData levelData) {

        this.gui = gui;
        this.gameScene = scene;
        this.wall = this.gameScene.getWall();
        this.key = this.gameScene.getItem();
        this.door = this.gameScene.getDoor();
        this.ghost = this.gameScene.getGhost();
        this.levelData = levelData;

        // Walls
        for (LevelData.LevelDataWall wallData : this.levelData.getWalls()) {
            this.wall = new Wall(wallData.getX(), wallData.getY(), wallData.getWidth(), wallData.getHeight(), wallData.getTexture());
            this.wallList.add(this.wall);
            this.gameScene.pGameItemChildren(this.wall.getRWall());
        }
        System.out.println("✔ [MapCreator]: " + this.levelData.getWalls().size() + " Walls created");

        // Keys
        for (LevelData.LevelDataKey keyData : this.levelData.getKeys()) {
            this.netToCollectKeys++;
            this.key = new Key(keyData.getX(), keyData.getY(), keyData.getWidth(), keyData.getHeight(), keyData.getTexture());
            this.keyList.add(this.key);
            this.gameScene.pGameItemChildren(this.key.getIKey());
        }
        System.out.println("✔ [MapCreator]: " + this.levelData.getKeys().size() + " Keys created");

        // Vacuums
        for (LevelData.LevelDataVacuum vacuumData : this.levelData.getVacuums()) {
            this.vacuum = new Vacuum(vacuumData.getX(), vacuumData.getY(), vacuumData.getWidth(), vacuumData.getHeight(), vacuumData.getTexture());
            this.vacuumsList.add(this.vacuum);
            this.gameScene.pGameItemChildren(this.vacuum.getIVacuum());
        }
        System.out.println("✔ [MapCreator]: " + this.levelData.getVacuums().size() + " Vacuums created");

        // Doors
        for (LevelData.LevelDataDoor doorData : this.levelData.getDoors()) {
            this.door = new Door(doorData.getX(), doorData.getY(), doorData.getWidth(), doorData.getHeight(), doorData.getTexture());
            this.doorList.add(this.door);
            this.gameScene.pGameItemChildren(this.door.getIvDoor());
        }
        System.out.println("✔ [MapCreator]: " + this.levelData.getDoors().size() + " Doors created");

        // Ghosts
        for (LevelData.LevelDataGhost ghostData : this.levelData.getGhosts()) {
            this.ghost = new Ghost(this.gui, ghostData.getX(), ghostData.getY(), ghostData.getWidth(), ghostData.getHeight());
            this.ghostList.add(this.ghost);
            this.gameScene.pGameGhostsChildren(this.ghost.getSolidAria());
            this.gameScene.pGameGhostsChildren(this.ghost.loadGhostOverlay());
            this.gameScene.pGameGhostsChildren(this.ghost.draw());
        }
        System.out.println("✔ [MapCreator]: " + this.levelData.getGhosts().size() + " Ghosts created");
    }

    // Toggle Wall collision
    public void triggerCollision() {
        if (!collision_on) {
            for (Wall wall : this.wallList) {
                wall.setPlayer_block_collision(true);
            }
            this.collision_on = true;
        } else {
            for (Wall wall : this.wallList) {
                wall.setPlayer_block_collision(false);
            }
            this.collision_on = false;
        }
    }

    //Toggle gost isMoving
    public void triggerGhostMoving() {
        if (!ghostMoving_on) {
            for (Ghost ghost : this.ghostList) {
                ghost.setGhostMoving(true);
            }
            this.ghostMoving_on = true;
        } else {
            for (Ghost ghost : this.ghostList) {
                ghost.setGhostMoving(false);
            }
            this.ghostMoving_on = false;
        }
    }

    // Getter Methoden
    public Wall getWall() {
        return this.wall;
    }

    public int getNetToCollectKeys() {
        return netToCollectKeys;
    }

    public ArrayList<Wall> getWallList() {
        return this.wallList;
    }

    public ArrayList<Key> getKeyList() {
        return this.keyList;
    }

    public ArrayList<Vacuum> getVacuumsList() {
        return this.vacuumsList;
    }

    public ArrayList<Door> getDoorList() {
        return this.doorList;
    }

    public ArrayList<Ghost> getGhostList() {
        return this.ghostList;
    }

    // Delete Variables
    public void deleteMapCreate() {
        System.out.println("⚠ [MapCreate]: Alle Referenzen werden gelöscht...");

        if (this.gui != null) {
            this.gui = null;
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        if (this.wall != null) {
            this.wall = null;
        }

        if (this.key != null) {
            this.key = null;
        }

        if (this.door != null) {
            this.door = null;
        }

        if (this.ghost != null) {
            this.ghost = null;
        }

        if (this.wallList != null) {
            this.wallList.clear();
            this.wallList = null;
        }

        if (this.keyList != null) {
            this.keyList.clear();
            this.keyList = null;
        }

        if (this.vacuumsList != null) {
            this.vacuumsList.clear();
            this.vacuumsList = null;
        }

        if (this.doorList != null) {
            this.doorList.clear();
            this.doorList = null;
        }

        if (this.ghostList != null) {
            this.ghostList.clear();
            this.ghostList = null;
        }

        this.netToCollectKeys = 0;
        this.collision_on = false;
        this.ghostMoving_on = false;

        if (this.levelData != null) {this.levelData.deleteLevelData();
            this.levelData.deleteLevelData();
            this.levelData = null;
        }

        System.gc();
        System.out.println("✔ [MapCreate]: Speicherbereinigung durchgeführt.");
    }
}