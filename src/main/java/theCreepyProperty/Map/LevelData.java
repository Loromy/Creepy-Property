package theCreepyProperty.Map;

import theCreepyProperty.checker.FileCheck;
import java.util.ArrayList;

// Handles level data including walls, keys, vacuums, doors, and ghosts
public class LevelData {
    private ArrayList<LevelDataWall> walls;
    private ArrayList<LevelDataKey> keys;
    private ArrayList<LevelDataVacuum> vacuums;
    private ArrayList<LevelDataDoor> doors;
    private ArrayList<LevelDataGhost> ghosts;

    public LevelData() {
        System.out.println(".............................LevelData..............................");
        // Initialize lists for all entities and blocks
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

    // --------------- KEY ---------------
    public class LevelDataKey {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataKey(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = new FileCheck().checkImage("LevelData", "file:src/resources/textures/items/Key.png");
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
        public String getTexture() { return texture; }
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
            this.texture = new FileCheck().checkImage("LevelData", "file:src/resources/textures/items/Door.png");
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

    // --------------- GETTERS ---------------
    public ArrayList<LevelDataWall> getWalls() { return walls; }
    public ArrayList<LevelDataKey> getKeys() { return keys; }
    public ArrayList<LevelDataVacuum> getVacuums() { return vacuums; }
    public ArrayList<LevelDataDoor> getDoors() { return doors; }
    public ArrayList<LevelDataGhost> getGhosts() { return ghosts; }

    // --------------- CLEAR / DELETE ---------------
    public void clear() {
        // Clears all level data lists
        this.walls.clear();
        this.keys.clear();
        this.vacuums.clear();
        this.doors.clear();
        this.ghosts.clear();
    }

    // Delete Variables
    public void deleteLevelData() {
        System.out.println("⚠ [LevelData]: Deleting all references...");

        // Clear and nullify level data lists
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

        System.gc();
        System.out.println("✔ [LevelData]: Memory cleanup complete.");
    }
}