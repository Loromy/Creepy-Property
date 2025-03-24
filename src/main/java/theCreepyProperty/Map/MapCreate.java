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

    public void createMap(GUI gui, GameScene scene, LevelData levelData){

        this.gui = gui;
        this.gameScene = scene;
        this.wall = this.gameScene.getWall();
        this.key = this.gameScene.getItem();
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

        for (int i = 0; i < this.levelData.getKeys().size(); i++) {
            this.netToCollectKeys++;

            this.key = new Key(levelData.getKeys().get(i).getX(), levelData.getKeys().get(i).getY(), levelData.getKeys().get(i).getWidth(), levelData.getKeys().get(i).getHeight(), levelData.getKeys().get(i).getTexture());
            this.keyList.add(this.key);
            this.gameScene.pGameItemChildren(this.key.getIKey());
            if (this.levelData.getKeys().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Keys created");
            }
        }

        for (int i = 0; i < this.levelData.getVacuums().size(); i++) {
            this.vacuum = new Vacuum(levelData.getVacuums().get(i).getX(), levelData.getVacuums().get(i).getY(), levelData.getVacuums().get(i).getWidth(), levelData.getVacuums().get(i).getHeight(), levelData.getVacuums().get(i).getTexture());

            this.vacuumsList.add(this.vacuum);
            this.gameScene.pGameItemChildren(this.vacuum.getIVacuum());
            if (this.levelData.getVacuums().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Vacuums created");
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
            this.gameScene.pGameGhostsChildren(this.ghost.loadGhostOverlay());
            this.gameScene.pGameGhostsChildren(this.ghost.draw());

            if (this.levelData.getGhosts().size()-1 == i) {
                i++;
                System.out.println("✔ [MapCreator]: " + i + " Ghosts created");
            }
        }
    }

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

    public Wall getWall() {
        return this.wall;
    }

    public Key getItem() {
        return this.key;
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

    public void deleteMapCreate() {
        System.out.println("⚠ [MapCreate]: Alle Referenzen werden gelöscht...");

        // Löschen der GUI und GameScene Referenzen
        if (this.gui != null) {
            this.gui = null; // GUI auf null setzen
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        // Löschen der einzelnen Objekte wie Wall, Key, Door, Ghost
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

        // Löschen der Listen
        if (this.wallList != null) {
            this.wallList.clear(); // Liste der Wände leeren
            this.wallList = null; // Liste auf null setzen
        }

        if (this.keyList != null) {
            this.keyList.clear(); // Liste der Keys leeren
            this.keyList = null; // Liste auf null setzen
        }

        if (this.vacuumsList != null) {
            this.vacuumsList.clear(); // Liste der Vacuums leeren
            this.vacuumsList = null; // Liste auf null setzen
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