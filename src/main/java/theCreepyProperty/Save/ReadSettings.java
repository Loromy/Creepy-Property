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
        System.out.println("1gui: ---------" + this.guiComponents);
        System.out.println("(readsettings) gamescreen: " + gameScene);

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
        System.out.println("2gui: ---------" + this.guiComponents);
        switch (setting) {
            case "anzeige":
                if (value == 0) {
                    this.guiComponents.setAnzeige_on(true);
                    System.out.println("[GuiComponents]: SettingsRead value: false ✔️");
                } else if (value == 1) {
                    this.guiComponents.setAnzeige_on(false);
                    System.out.println("[GuiComponents]: SettingsRead value: true ✔️");
                } else {
                    System.out.println("[GuiComponents]: SettingsRead wrong value: " + value + " does not exist ✖");
                }
                //System.out.println("guiComponents: " + this.gameScene.getGuiComponents());
                this.guiComponents.triggerAnzeige(this.gameScene);
                System.out.println("anzeige: " + this.guiComponents.getAnzeige_on());
                break;
            case "devMode":
                break;
            default:
                System.out.println("[GuiComponents]: SettingsRead wrong setting: " + setting + " does not exist ✖");
                break;
        }
    }
}
