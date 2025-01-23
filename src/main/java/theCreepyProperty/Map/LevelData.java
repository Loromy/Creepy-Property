package theCreepyProperty.Map;

import java.util.ArrayList;
import java.util.List;

public class LevelData {
    private ArrayList<LevelDataWall> walls;
    private ArrayList<LevelDataItem> items;

    public LevelData() {
        walls = new ArrayList<>();
        items = new ArrayList<>();
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

    public static class LevelDataItem {
        private final String texture;
        private final int x, y, width, height;

        public LevelDataItem(int x, int y, int width, int height, String texture) {
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
        public String getTexture() { return texture; }

    }

    // Getter und Setter
    public ArrayList<LevelDataWall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<LevelDataWall> walls) {
        this.walls = walls;
    }

    public ArrayList<LevelDataItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LevelDataItem> items) {
        this.items = items;
    }
}

