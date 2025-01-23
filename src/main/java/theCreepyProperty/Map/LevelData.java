package theCreepyProperty.Map;

import java.util.ArrayList;
import java.util.List;

public class LevelData {
    private ArrayList<LevelDataWall> walls;
    private ArrayList<Item> items;

    public LevelData() {
        walls = new ArrayList<>();
        items = new ArrayList<>();
    }

    // Getter und Setter
    public ArrayList<LevelDataWall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<LevelDataWall> walls) {
        this.walls = walls;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    // Inner classes für Walls und Items
    public class LevelDataWall {
        private final int x, y, width, height;
        private final String texture;

        public LevelDataWall(int x, int y, int width, int height, String texture) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.texture = texture;
        }

        // Getter
        public int getX() { return x; }
        public int getY() { return y; }
        public int getWidth() { return width; }
        public int getHeight() { return height; }
        public String getTexture() {return texture; }
    }

    public static class Item {
        private String type;
        private int x, y;

        public Item(String type, int x, int y) {
            this.type = type;
            this.x = x;
            this.y = y;
        }

        // Getter
        public String getType() { return type; }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}

