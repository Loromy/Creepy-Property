package theCreepyProperty.Map;

import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.LevelSelectScene;

public class SetMap {
    private final GUI gui;
    private final LevelSelectScene levelSelectScene;

    private final String path0 = "src/resources/csv/maps/map0.csv";
    private final String path1 = "src/resources/csv/maps/map1.csv";
    private final String path2 = "src/resources/csv/maps/map2.csv";
    private final String path3 = "src/resources/csv/maps/map3.csv";
    private final String path4 = "src/resources/csv/maps/map4.csv";
    private final String path5 = "src/resources/csv/maps/map5.csv";
    private final String path6 = "src/resources/csv/maps/map6.csv";
    private final String path7 = "src/resources/csv/maps/map7.csv";
    private final String path8 = "src/resources/csv/maps/map8.csv";
    private final String path9 = "src/resources/csv/maps/map9.csv";


    public SetMap(GUI gui) {
        System.out.println(".............................SetMap..............................");
        this.gui = gui;
        this.levelSelectScene = gui.getSelectScene();
    }

    public void setMapPlus1() {
        this.levelSelectScene.setMapSelected(this.levelSelectScene.getMapSelected()); //todo

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

    public void setThisMap() {
        this.levelSelectScene.setMapSelected(this.levelSelectScene.getMapSelected()); //todo

        switch (this.levelSelectScene.getMapSelected()) {
            case 0:
                this.gui.setFilePath(path0);
                System.out.println("✔ [SetMap]: 0 selected");
                this.levelSelectScene.setMapSelected(0);
                break;
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
                System.err.println("✖ [SetMap Error]: Invalid map level: " + this.levelSelectScene.getMapSelected() + " does not exist");
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
}
