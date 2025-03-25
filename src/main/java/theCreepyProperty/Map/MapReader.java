package theCreepyProperty.Map;

import java.io.BufferedReader;
import java.io.FileReader;

public class MapReader {
    LevelData levelData;

    public MapReader(LevelData levelData) {
        System.out.println(".............................MapReader..............................");
        this.levelData = levelData;
    }

    public LevelData readCsvFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Header überspringen
                    continue;
                }

                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                if (type.equals("wall")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    levelData.getWalls().add(levelData.new LevelDataWall(x, y, width, height));
                } else if (type.equals("key")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    levelData.getKeys().add(levelData.new LevelDataKey(x, y, width, height));
                } else if (type.equals("vacuum")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    levelData.getVacuums().add(levelData.new LevelDataVacuum(x, y, width, height));
                }else if (type.equals("door")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    levelData.getDoors().add(levelData.new LevelDataDoor(x, y, width, height));
                } else if (type.equals("ghost")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    levelData.getGhosts().add(levelData.new LevelDataGhost(x, y, width, height));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return levelData;
    }

    public void deleteMapReader() {
        System.out.println("⚠ [MapReader]: Alle Referenzen werden gelöscht...");

        // Setze die LevelData Referenz auf null
        if (this.levelData != null) {
            this.levelData.clear();
            this.levelData = null;
        }

        // Führe Garbage Collection aus
        System.gc();
        System.out.println("✔ [MapReader]: Speicherbereinigung durchgeführt.");
    }
}
