package theCreepyProperty.Save;

import theCreepyProperty.scenes.LevelSelectScene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteSpielstand {
    private LevelSelectScene levelSelectScene;
    private Map<Integer, Setting> settingsMap;

    public ReadWriteSpielstand() {
        System.out.println(".............................ReadWriteSpielstand..............................");
        this.settingsMap = new HashMap<>();
    }

    // Read saved game data from file
    public void spielstandRead(String filePath, LevelSelectScene levelSelectScene) {
        this.levelSelectScene = levelSelectScene;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                int level = Integer.parseInt(parts[0].trim());
                boolean unlocked = Boolean.parseBoolean(parts[1].trim());
                boolean completed = Boolean.parseBoolean(parts[2].trim());
                double time = Double.parseDouble(parts[3].trim());
                int deaths = Integer.parseInt(parts[4].trim());

                settingsMap.put(level, new Setting(unlocked, completed, time, deaths));
                setSpielstand(level, unlocked, completed, time, deaths);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Apply saved game data to the LevelSelectScene
    public void setSpielstand(int level, boolean unlocked, boolean completed, double time, int deaths) {
        this.levelSelectScene.setLevelCompleted(level, completed);
        this.levelSelectScene.setLevelUnlocked(level, unlocked);
        this.levelSelectScene.setLevelTime(level, time);
        this.levelSelectScene.setLevelDeaths(level, deaths); // Track deaths
    }

    // Update saved game data in the file
    public void updateSpielstand(int level, boolean unlocked, boolean completed, double time, int deaths) {
        Map<Integer, Setting> tempSettingsMap = new HashMap<>();

        // Read existing data
        String filePath = "src/resources/csv/Save/spielstand.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 5) continue;

                int lvl = Integer.parseInt(parts[0].trim());
                boolean isUnlocked = Boolean.parseBoolean(parts[1].trim());
                boolean isCompleted = Boolean.parseBoolean(parts[2].trim());
                double savedTime = Double.parseDouble(parts[3].trim());
                int savedDeaths = Integer.parseInt(parts[4].trim());

                tempSettingsMap.put(lvl, new Setting(isUnlocked, isCompleted, savedTime, savedDeaths));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Update the specific level's data
        tempSettingsMap.put(level, new Setting(unlocked, completed, time, deaths));

        // Overwrite the file with updated data
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Level,unlocked,completed,time,deaths"); // Keep header

            for (Map.Entry<Integer, Setting> entry : tempSettingsMap.entrySet()) {
                bw.write("\n" + entry.getKey() + "," + entry.getValue().unlocked + "," + entry.getValue().completed + ","
                        + entry.getValue().time + "," + entry.getValue().deaths);
            }

            System.out.println("✔ [ReadWriteSpielstand]: Level " + level + " updated");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // class to store settings for each level
    private static class Setting {
        boolean unlocked;
        boolean completed;
        double time;
        int deaths;

        public Setting(boolean unlocked, boolean completed, double time, int deaths) {
            this.unlocked = unlocked;
            this.completed = completed;
            this.time = time;
            this.deaths = deaths;
        }
    }

    // Delete Variables
    public void deleteReadWriteSpielstand() {
        System.out.println("⚠ [ReadWriteSpielstand]: Deleting all references...");

        this.levelSelectScene = null;

        if (this.settingsMap != null) {
            this.settingsMap.clear();
            this.settingsMap = null;
        }

        System.gc();
        System.out.println("✔ [ReadWriteSpielstand]: Memory cleanup done.");
    }
}