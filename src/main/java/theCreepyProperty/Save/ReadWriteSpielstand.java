package theCreepyProperty.Save;

import theCreepyProperty.main.GuiComponents;
import theCreepyProperty.menu.LevelSelect;
import theCreepyProperty.scenes.GameScene;
import theCreepyProperty.scenes.LevelSelectScene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteSpielstand {
    private LevelSelectScene levelSelectScene;
    private GuiComponents guiComponents;
    private Map<Integer, Setting> settingsMap;

    public ReadWriteSpielstand(GuiComponents guiComponents) {
        this.guiComponents = guiComponents;
        this.settingsMap = new HashMap<>();
    }

    public void spielstandRead(String filePath, LevelSelectScene levelSelectScene, GuiComponents guiComponents) {
        this.guiComponents = guiComponents;
        this.levelSelectScene = levelSelectScene;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Header überspringen
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                int level = Integer.parseInt(parts[0].trim());
                boolean unlocked = Boolean.parseBoolean(parts[1].trim());
                boolean completed = Boolean.parseBoolean(parts[2].trim());

                settingsMap.put(level, new Setting(unlocked, completed));
                setSpielstand(level, unlocked, completed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // lesen
    public void setSpielstand(int level, boolean unlocked, boolean completed) {
        this.levelSelectScene.getLevelMenu().setLevelFortschritt(level, unlocked, completed);
    }

    // schreiben
    public void updateSetting(int level, boolean unlocked, boolean completed) {
        String filePath = "src/resources/csv/Save/spielstand.csv";
        Map<Integer, Setting> tempSettingsMap = new HashMap<>();

        // Datei einlesen und vorhandene Werte speichern
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Header speichern
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                int lvl = Integer.parseInt(parts[0].trim());
                boolean isUnlocked = Boolean.parseBoolean(parts[1].trim());
                boolean isCompleted = Boolean.parseBoolean(parts[2].trim());

                tempSettingsMap.put(lvl, new Setting(isUnlocked, isCompleted));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Den gewünschten Wert aktualisieren
        tempSettingsMap.put(level, new Setting(unlocked, completed));

        // Datei mit aktualisierten Werten überschreiben
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Level,unlocked,completed"); // Header beibehalten

            for (Map.Entry<Integer, Setting> entry : tempSettingsMap.entrySet()) {
                bw.write("\n" + entry.getKey() + "," + entry.getValue().unlocked + "," + entry.getValue().completed);
            }

            System.out.println("[ReadSpielstand]: Einstellung für Level " + level + " aktualisiert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Eigene Klasse für die Level-Einstellungen
    private static class Setting {
        boolean unlocked;
        boolean completed;

        public Setting(boolean unlocked, boolean completed) {
            this.unlocked = unlocked;
            this.completed = completed;
        }
    }
}
