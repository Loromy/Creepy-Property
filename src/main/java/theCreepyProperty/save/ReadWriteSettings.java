package theCreepyProperty.save;

import theCreepyProperty.main.GuiComponents;
import theCreepyProperty.scenes.GameScene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadWriteSettings {
    private GameScene gameScene;
    private GuiComponents guiComponents;
    private Map<String, Integer> settingsMap;

    private static final Logger LOGGER = Logger.getLogger("ReadWriteSettings");

    public ReadWriteSettings(GuiComponents guiComponents) {
        System.out.println(".............................ReadWriteSettings..............................");
        this.guiComponents = guiComponents;
        this.settingsMap = new HashMap<>();
    }

    // Read settings from a file and apply them
    public void settingsRead(String filePath, GameScene gameScene, GuiComponents guiComponents) {
        this.gameScene = gameScene;
        this.guiComponents = guiComponents;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                String setting = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                settingsMap.put(setting, value);

                setSettings(setting, value);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to read the settings file: ", e);
        }
    }

    // Apply a setting based on its key and value
    public void setSettings(String setting, int value) {
        switch (setting) {
            case "anzeige":
                this.guiComponents.setAnzeige_on(value == 0);
                System.out.println("✔ [ReadWriteSettings]: \"anzeige\" set to " + (value == 0 ? "false" : "true"));
                this.guiComponents.triggerAnzeigeRWSettings();
                break;

            case "master":
                if (value >= 1 && value <= 100) {
                    this.gameScene.getMenu().getSettings().getAudio().setMaster(value);
                    System.out.println("✔ [ReadWriteSettings]: \"master\" set to " + value);
                } else {
                    System.err.println("✖ [ReadWriteSettings]: Invalid \"master\" value: " + value);
                }
                break;

            case "background":
                if (value >= 1 && value <= 100) {
                    this.gameScene.getMenu().getSettings().getAudio().setBackground(value);
                    System.out.println("✔ [ReadWriteSettings]: \"background\" set to " + value);
                } else {
                    System.err.println("✖ [ReadWriteSettings]: Invalid \"background\" value: " + value);
                }
                break;

            default:
                System.err.println("✖ [ReadWriteSettings]: Unknown setting: " + setting);
                break;
        }
    }

    // Update a specific setting in the settings file
    public void updateSetting(String setting, int newValue) {
        String filePath = "src/resources/csv/save/settings.csv";
        Map<String, Integer> tempSettingsMap = new HashMap<>();

        // Read existing settings
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Keep header
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 2) continue;

                tempSettingsMap.put(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to read the settings file to Update the settings File: ", e);
        }

        // Update the desired setting
        tempSettingsMap.put(setting, newValue);

        // Write the updated settings back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("setting,value"); // Keep header

            for (Map.Entry<String, Integer> entry : tempSettingsMap.entrySet()) {
                bw.write("\n" + entry.getKey() + "," + entry.getValue());
            }

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to update the settings file: ", e);
        }
    }

    public void deleteReadWriteSettings() {
        System.out.println("⚠ [ReadWriteSettings]: Alle Referenzen werden gelöscht...");

        // Setze alle Referenzen auf null
        this.gameScene = null;
        this.guiComponents = null;

        // Setze das Map zurück
        if (this.settingsMap != null) {
            this.settingsMap.clear();  // Lösche alle Einträge aus dem Map
            this.settingsMap = null;   // Setze die Map auf null
        }

        // Führe Garbage Collection aus
        System.gc();
        System.out.println("✔ [ReadWriteSettings]: Speicherbereinigung durchgeführt.");
    }
}