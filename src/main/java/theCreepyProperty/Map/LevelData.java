package theCreepyProperty.Map;

import theCreepyProperty.checker.FileCheck;

import java.util.ArrayList;

public class LevelData {
    private ArrayList<LevelDataWall> walls;
    private ArrayList<LevelDataKey> keys;
    private ArrayList<LevelDataVacuum> vacuums;
    private ArrayList<LevelDataDoor> doors;
    private ArrayList<LevelDataGhost> ghosts;

    public LevelData() {
        System.out.println(".............................LevelData..............................");
        walls = new ArrayList<>();
        keys = new ArrayList<>();
        vacuums = new ArrayList<>();

        doors = new ArrayList<>();
        ghosts = new ArrayList<>();
    }

    // --------------- WALL ---------------
    public class LevelDataWall {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataWall(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = "#361a00";
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public String getTexture() { return texture; }
    }

    // --------------- Key ---------------
    public class LevelDataKey {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataKey(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = new FileCheck().checkImage("LevelDate","file:src/resources/textures/items/Key.png");
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public String getTexture() { return texture; }
    }

    // --------------- VACUUM ---------------
    public class LevelDataVacuum {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataVacuum(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = new FileCheck().checkImage("LevelData", "file:src/resources/textures/items/Vacuum.png");
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public String getTexture() {return texture;}
    }

    // --------------- DOOR ---------------
    public class LevelDataDoor {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataDoor(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = new FileCheck().checkImage("LevelDate","file:src/resources/textures/items/Door.png");
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public String getTexture() { return texture; }
    }

    // --------------- GHOST ---------------
    public class LevelDataGhost {
        private final int x, y, width, height;

        public LevelDataGhost(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
    }

    // --------------- GETTER ---------------
    public ArrayList<LevelDataWall> getWalls() { return walls; }
    public ArrayList<LevelDataKey> getKeys() { return keys; }
    public ArrayList<LevelDataVacuum> getVacuums() { return vacuums; }
    public ArrayList<LevelDataDoor> getDoors() { return doors; }
    public ArrayList<LevelDataGhost> getGhosts() { return ghosts; }

    // --------------- SETTER ---------------
    public void setKeys(ArrayList<LevelDataKey> keys) { this.keys = keys; }
    public void setWalls(ArrayList<LevelDataWall> walls) { this.walls = walls; }
    public void setDoors(ArrayList<LevelDataDoor> doors) { this.doors = doors; }

    // --------------- CLEAR / DELETE ---------------
    public void clear() {
        this.walls.clear();
        this.keys.clear();
        this.vacuums.clear();
        this.doors.clear();
        this.ghosts.clear();
    }

    public void deleteLevelData() {
        System.out.println("⚠ [LevelData]: Alle Referenzen werden gelöscht...");

        // Löschen der Listen mit LevelData-Elementen
        if (this.walls != null) {
            this.walls.clear();
            this.walls = null;
        }

        if (this.keys != null) {
            this.keys.clear();
            this.keys = null;
        }

        if (this.vacuums != null) {
            this.vacuums.clear();
            this.vacuums = null;
        }

        if (this.doors != null) {
            this.doors.clear();
            this.doors = null;
        }

        if (this.ghosts != null) {
            this.ghosts.clear();
            this.ghosts = null;
        }

        // Garbage Collector anstoßen
        System.gc();

        System.out.println("✔ [LevelData]: Speicherbereinigung durchgeführt.");
    }
}
