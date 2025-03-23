package theCreepyProperty.Map;

import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.LevelSelectScene;

public class SetMap {
    private GUI gui;
    private LevelSelectScene levelSelectScene;

    private String path0 = "src/resources/csv/maps/map0.csv";
    private String path1 = "src/resources/csv/maps/map1.csv";
    private String path2 = "src/resources/csv/maps/map2.csv";
    private String path3 = "src/resources/csv/maps/map3.csv";
    private String path4 = "src/resources/csv/maps/map4.csv";
    private String path5 = "src/resources/csv/maps/map5.csv";
    private String path6 = "src/resources/csv/maps/map6.csv";
    private String path7 = "src/resources/csv/maps/map7.csv";
    private String path8 = "src/resources/csv/maps/map8.csv";
    private String path9 = "src/resources/csv/maps/map9.csv";

    public SetMap(GUI gui) {
        System.out.println(".............................SetMap..............................");
        this.gui = gui;
        this.levelSelectScene = gui.getSelectScene();
    }

    public void setMapPlus1() {
        this.levelSelectScene.setMapSelected(this.levelSelectScene.getMapSelected());

        int level = this.levelSelectScene.getMapSelected();
        switch (++level) {
            case 1:
                this.gui.setFilePath(path1);
                System.out.println("✔ [SetMap]: 1 selected");
                this.levelSelectScene.setMapSelected(1);
                break;
            case 2:
                this.gui.setFilePath(path2);
                System.out.println("✔ [SetMap]: 2 selected");
                this.levelSelectScene.setMapSelected(2);
                break;
            case 3:
                this.gui.setFilePath(path3);
                System.out.println("✔ [SetMap]: 3 selected");
                this.levelSelectScene.setMapSelected(3);
                break;
            case 4:
                this.gui.setFilePath(path4);
                System.out.println("✔ [SetMap]: 4 selected");
                this.levelSelectScene.setMapSelected(4);
                break;
            case 5:
                this.gui.setFilePath(path5);
                System.out.println("✔ [SetMap]: 5 selected");
                this.levelSelectScene.setMapSelected(5);
                break;
            case 6:
                this.gui.setFilePath(path6);
                System.out.println("✔ [SetMap]: 6 selected");
                this.levelSelectScene.setMapSelected(6);
                break;
            case 7:
                this.gui.setFilePath(path7);
                System.out.println("✔ [SetMap]: 7 selected");
                this.levelSelectScene.setMapSelected(7);
                break;
            case 8:
                this.gui.setFilePath(path8);
                System.out.println("✔ [SetMap]: 8 selected");
                this.levelSelectScene.setMapSelected(8);
                break;
            case 9:
                this.gui.setFilePath(path9);
                System.out.println("✔ [SetMap]: 9 selected");
                this.levelSelectScene.setMapSelected(9);
                break;
            default:
                System.err.println("✖ [SetMap Error]: Invalid map level: " + level + " does not exist");
                break;
        }
    }

    // Getter Methoden
    public String getPath0() {
        return this.path0;
    }

    public String getPath1() {
        return this.path1;
    }

    public String getPath2() {
        return this.path2;
    }

    public String getPath3() {
        return this.path3;
    }

    public String getPath4() {
        return this.path4;
    }

    public String getPath5() {
        return this.path5;
    }

    public String getPath6() {
        return this.path6;
    }

    public String getPath7() {
        return this.path7;
    }

    public String getPath8() {
        return this.path8;
    }

    public String getPath9() {
        return this.path9;
    }

    public void deleteSetMap() {
        System.out.println("⚠ [SetMap]: Alle Referenzen werden gelöscht...");

        // GUI Referenz löschen (Wird extern verwaltet)
        if (this.gui != null) {
            this.gui = null;
        }

        // LevelSelectScene Referenz löschen
        if (this.levelSelectScene != null) {
            this.levelSelectScene = null;
        }

        // Alle Pfad-Variablen auf null setzen
        this.path0 = null;
        this.path1 = null;
        this.path2 = null;
        this.path3 = null;
        this.path4 = null;
        this.path5 = null;
        this.path6 = null;
        this.path7 = null;
        this.path8 = null;
        this.path9 = null;

        // System.gc() aufrufen um Speicherbereinigung zu erzwingen
        System.gc();
        System.out.println("✔ [SetMap]: Speicherbereinigung durchgeführt.");
    }

}
