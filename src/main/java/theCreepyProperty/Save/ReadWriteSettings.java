package theCreepyProperty.Save;

import theCreepyProperty.main.GuiComponents;
import theCreepyProperty.scenes.GameScene;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadWriteSettings {
    private GameScene gameScene;
    private GuiComponents guiComponents;
    private Map<String, Integer> settingsMap;

    public ReadWriteSettings(GuiComponents guiComponents) {
        this.guiComponents = guiComponents;
        this.settingsMap = new HashMap<>();
    }

    public void settingsRead(String filePath, GameScene gameScene, GuiComponents guiComponents) {
        this.gameScene = gameScene;
        this.guiComponents = guiComponents;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Header überspringen
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
            e.printStackTrace();
        }
    }

    // lesen
    public void setSettings(String setting, int value) {
        switch (setting) {
            case "anzeige":
                if (value == 0) {
                    this.guiComponents.setAnzeige_on(true);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"anzeige\" value: false");
                }
                else if (value == 1) {
                    this.guiComponents.setAnzeige_on(false);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"anzeige\" value: true");
                }
                else {
                    System.err.println("✖ [ReedWriteSettings]: SettingsRead \"anzeige\" wrong value: " + value + " does not exist");
                }
                this.guiComponents.triggerAnzeigeRWSettings();
                break;

            case "overlay":
                if (value == 0) {
                    this.gameScene.getPlayer().setOverlay_on(true);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"overlay\" value: false");
                }
                else if (value == 1) {
                    this.gameScene.getPlayer().setOverlay_on(false);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"overlay\" value: true ️");
                }
                else {
                    System.err.println("✖ [ReedWriteSettings]: SettingsRead \"overlay\" wrong value: " + value + " does not exist");
                }
                this.gameScene.getPlayer().triggerOverlayRWSettings(this.gameScene);
                break;

            case "collision":
                if (value == 0) {
                    this.gameScene.getMapCreate().setCollision_on(true);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"collision\" value: false");
                }
                else if (value == 1) {
                    this.gameScene.getMapCreate().setCollision_on(false);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"collision\" value: true");
                }
                else {
                    System.err.println("✖ [ReedWriteSettings]: SettingsRead \"collision\" wrong value: " + value + " does not exist");
                }
                this.gameScene.getMapCreate().triggerCollisionRWSettings(this.gameScene);
                break;

            case "master":
                if (value >= 1 && value <= 100) {
                    this.gameScene.getMenu().getSettings().getAudio().setMaster(value);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"master\" value: " + value);
                }
                else {
                    System.err.println("✖ [ReedWriteSettings]: SettingsRead \"master\" wrong value: " + value + " does not exist");
                }
                break;

            case "background":
                if (value >= 1 && value <= 100) {
                    this.gameScene.getMenu().getSettings().getAudio().setBackground(value);
                    System.out.println("✔ [ReedWriteSettings]: SettingsRead \"background\" value: " + value);
                }
                else {
                    System.err.println("✖ ReedWriteSettings]: SettingsRead \"background\" wrong value: " + value + " does not exist");
                }
                break;

            default:
                System.err.println("✖ [ReedWriteSettings]: SettingsRead wrong setting: " + setting + " does not exist");
                break;
        }
    }

    // schreiben
    public void updateSetting(String setting, int newValue) {
        String filePath = "src/resources/csv/Einstellungen/settings.csv";
        Map<String, Integer> tempSettingsMap = new HashMap<>();

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
                if (parts.length < 2) continue;

                String key = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());

                tempSettingsMap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Den gewünschten Wert aktualisieren
        tempSettingsMap.put(setting, newValue);

        // Datei mit aktualisierten Werten überschreiben
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("setting,value"); // Header beibehalten

            for (Map.Entry<String, Integer> entry : tempSettingsMap.entrySet()) {
                bw.write("\n" + entry.getKey() + "," + entry.getValue());
            }

            //System.out.println("[ReadWriteSettings]: Einstellung \"" + setting + "\" auf " + newValue + " aktualisiert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
