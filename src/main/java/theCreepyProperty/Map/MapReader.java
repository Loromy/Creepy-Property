package theCreepyProperty.Map;

import java.io.BufferedReader;
import java.io.FileReader;

public class MapReader {
    LevelData levelData;

    public LevelData readCsvFile(String filePath, LevelData levelData) {
        this.levelData = levelData;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Header überspringen
                    continue;
                }

                // Zeile in Teile aufspalten
                String[] parts = line.split(",");
                String type = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                if (type.equals("wall")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    String texture = parts[5];
                    levelData.getWalls().add(levelData.new LevelDataWall(x, y, width, height, texture));
                } else if (type.equals("item")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    String itemTexture = parts[5];
                    levelData.getItems().add(new LevelData.LevelDataItem(x, y, width, height, itemTexture));
                } else if (type.equals("door")) {
                    int width = Integer.parseInt(parts[3]);
                    int height = Integer.parseInt(parts[4]);
                    String itemTexture = parts[5];
                    levelData.getDoors().add(new LevelData.LevelDataDoor(x, y, width, height, itemTexture));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return levelData;
    }
}
