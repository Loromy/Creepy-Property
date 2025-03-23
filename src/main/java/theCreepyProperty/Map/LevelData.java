package theCreepyProperty.Map;

import theCreepyProperty.checker.ImageCheck;

import java.util.ArrayList;

public class LevelData {
    private ArrayList<LevelDataWall> walls;
    private ArrayList<LevelDataItem> items;
    private ArrayList<LevelDataDoor> doors;
    private ArrayList<LevelDataGhost> ghosts;

    public LevelData() {
        System.out.println(".............................LevelData..............................");
        walls = new ArrayList<>();
        items = new ArrayList<>();
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

    // --------------- ITEM ---------------
    public class LevelDataItem {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataItem(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = new ImageCheck().checkImage("LevelDate","file:src/resources/textures/items/Key.png");
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
            this.texture = new ImageCheck().checkImage("LevelDate","file:src/resources/textures/items/Door.png");
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
    public ArrayList<LevelDataItem> getItems() { return items; }
    public ArrayList<LevelDataDoor> getDoors() { return doors; }
    public ArrayList<LevelDataGhost> getGhosts() { return ghosts; }

    // --------------- SETTER ---------------
    public void setItems(ArrayList<LevelDataItem> items) { this.items = items; }
    public void setWalls(ArrayList<LevelDataWall> walls) { this.walls = walls; }
    public void setDoors(ArrayList<LevelDataDoor> doors) { this.doors = doors; }

    public void clear() {
        this.walls.clear();
        this.items.clear();
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

        if (this.items != null) {
            this.items.clear();
            this.items = null;
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
