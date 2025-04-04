package theCreepyProperty.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapReader {
    private LevelData levelData;

    private final Logger LOGGER = Logger.getLogger("ReadWriteSettings");

    public MapReader(LevelData levelData) {
        System.out.println(".............................MapReader..............................");
        this.levelData = levelData;
    }

    // read a CSV-file and extract map-data into LevelData
    public LevelData readCsvFile(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {  // Open and read the file line by line
            String line;
            boolean isFirstLine = true;  // Flag to skip the first line (header)

            // Loop through each line in the file
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;  // Skip the first line (header)
                    continue;
                }

                String[] parts = line.split(",", -1);

                // If line has less than 3 parts (invalid), skip it
                if (parts.length < 3) {
                    System.err.println("⚠ [MapReader]: Invalid line: " + line);
                    continue;
                }

                // Extract the entity type and coordinates from the line
                String type = parts[0].trim();
                int x = Integer.parseInt(parts[1].trim());
                int y = Integer.parseInt(parts[2].trim());

                // If width and height are missing, skip the line
                if (parts.length < 5) {
                    System.err.println("⚠ [MapReader]: Missing width/height for type: " + type);
                    continue;
                }

                // Extract width and height
                int width = Integer.parseInt(parts[3].trim());
                int height = Integer.parseInt(parts[4].trim());

                // Create the appropriate entity based on the type and add it to LevelData
                switch (type.toLowerCase()) {
                    case "wall":
                        levelData.getWalls().add(levelData.new LevelDataWall(x, y, width, height));
                        break;
                    case "key":
                        levelData.getKeys().add(levelData.new LevelDataKey(x, y, width, height));
                        break;
                    case "vacuum":
                        levelData.getVacuums().add(levelData.new LevelDataVacuum(x, y, width, height));
                        break;
                    case "door":
                        levelData.getDoors().add(levelData.new LevelDataDoor(x, y, width, height));
                        break;
                    case "ghost":
                        levelData.getGhosts().add(levelData.new LevelDataGhost(x, y, width, height));
                        break;
                    default:
                        System.err.println("⚠ [MapReader]: Unknown entity type: " + type);
                        break;
                }
            }
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, "✖ [MapReader]: Invalid number format in file: ", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "✖ [MapReader]: Unable to read file: " + filePath + ": ", e);
        }

        return levelData;
    }

    // Delete Variables
    public void deleteMapReader() {
        System.out.println("⚠ [MapReader]: Deleting all references...");

        if (this.levelData != null) {
            this.levelData.clear();
            this.levelData = null;
        }

        System.gc();
        System.out.println("✔ [MapReader]: Memory cleanup complete.");  // Confirm cleanup
    }
}