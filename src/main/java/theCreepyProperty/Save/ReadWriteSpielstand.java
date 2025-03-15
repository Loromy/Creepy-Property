package theCreepyProperty.Save;

import theCreepyProperty.scenes.LevelSelectScene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteSpielstand {
    private LevelSelectScene levelSelectScene;
    private Map<Integer, Setting> settingsMap;
    private final String filePath = "src/resources/csv/Save/spielstand.csv";

    public ReadWriteSpielstand() {
        System.out.println(".............................ReadWriteSpielstand..............................");
        this.settingsMap = new HashMap<>();
    }

    public void spielstandRead(String filePath, LevelSelectScene levelSelectScene) {
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

    public void setSpielstand(int level, boolean unlocked, boolean completed, double time, int deaths) {
        this.levelSelectScene.setLevelCompleted(level, completed);
        this.levelSelectScene.setLevelUnlocked(level, unlocked);
        this.levelSelectScene.setLevelTime(level, time);
        this.levelSelectScene.setLevelDeaths(level, deaths); // Neue Methode für Todeszahlen
    }

    public void updateSpielstand(int level, boolean unlocked, boolean completed, double time, int deaths) {
        Map<Integer, Setting> tempSettingsMap = new HashMap<>();

        // Datei einlesen und vorhandene Werte speichern
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
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

        // Neuen Wert setzen
        tempSettingsMap.put(level, new Setting(unlocked, completed, time, deaths));

        // Datei mit aktualisierten Werten überschreiben
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Level,unlocked,completed,time,deaths"); // Header beibehalten

            for (Map.Entry<Integer, Setting> entry : tempSettingsMap.entrySet()) {
                bw.write("\n" + entry.getKey() + "," + entry.getValue().unlocked + "," + entry.getValue().completed + ","
                        + entry.getValue().time + "," + entry.getValue().deaths);
            }

            System.out.println("✔ [ReadWriteSpielstand]: Einstellung für Level " + level + " aktualisiert");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void deleteReadWriteSpielstand() {
        System.out.println("⚠ [ReadWriteSpielstand]: Alle Referenzen werden gelöscht...");

        // Setze alle relevanten Instanzen auf null
        this.levelSelectScene = null;

        // Lösche die gespeicherten Einstellungen
        if (this.settingsMap != null) {
            this.settingsMap.clear();  // Entferne alle Einträge aus dem Map
            this.settingsMap = null;   // Setze die Map auf null
        }

        // Führe Garbage Collection aus
        System.gc();
        System.out.println("✔ [ReadWriteSpielstand]: Speicherbereinigung durchgeführt.");
    }
}
