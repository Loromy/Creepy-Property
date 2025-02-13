package theCreepyProperty.Save;

import theCreepyProperty.main.GuiComponents;
import theCreepyProperty.scenes.GameScene;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadSettings {
    private GameScene gameScene;
    private GuiComponents guiComponents;

    public ReadSettings(GuiComponents guiComponents) {
        this.guiComponents = guiComponents;
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

                // Zeile in Teile aufspalten
                String[] parts = line.split(",");
                String setting = parts[0];
                int value = Integer.parseInt(parts[1]);

                if (setting.equals("anzeige")) {
                    //levelData.getWalls().add(levelData.new LevelDataWall(x, y, width, height, texture));
                    setSettings(setting, value);
                } else if (setting.equals("devMode")) {
                    //levelData.getItems().add(new LevelData.LevelDataItem(x, y, width, height, itemTexture));
                    setSettings(setting, value);
                } else if (setting.equals("Master")) {
                    //levelData.getDoors().add(new LevelData.LevelDataDoor(x, y, width, height, itemTexture));
                    setSettings(setting, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSettings(String setting, int value) {
        switch (setting) {
            case "anzeige":
                if (value == 0) {
                    this.guiComponents.setAnzeige_on(true);
                    System.out.println("[GuiComponents]: SettingsRead \"anzeige\" value: false ✔️");
                } else if (value == 1) {
                    this.guiComponents.setAnzeige_on(false);
                    System.out.println("[GuiComponents]: SettingsRead \"anzeige\" value: true ✔️");
                } else {
                    System.out.println("[GuiComponents]: SettingsRead \"anzeige\" wrong value: " + value + " does not exist ✖");
                }
                this.guiComponents.triggerAnzeige(this.gameScene);
                break;
            case "devMode":
                if (value == 0) {
                    this.gameScene.getMapCreate().setCollision_on(false);
                    this.gameScene.getPlayer().setOverlay_on(false);
                    System.out.println("[GuiComponents]: SettingsRead \"devMode\" value: false ✔️");
                } else  if (value == 1) {
                    this.gameScene.getMapCreate().setCollision_on(true);
                    this.gameScene.getPlayer().setOverlay_on(true);
                    System.out.println("[GuiComponents]: SettingsRead \"devMode\" value: true ✔️");
                } else {
                    System.out.println("[GuiComponents]: SettingsRead \"devMode\" wrong value: " + value + " does not exist ✖");
                }
                this.gameScene.getMapCreate().triggerCollision();
                this.gameScene.getPlayer().triggerOverlay();
                break;
            default:
                System.out.println("[GuiComponents]: SettingsRead wrong setting: " + setting + " does not exist ✖");
                break;
        }
    }
}
