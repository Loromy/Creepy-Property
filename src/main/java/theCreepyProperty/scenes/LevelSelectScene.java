package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import theCreepyProperty.Map.SetMap;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.main.GUI;
import theCreepyProperty.menu.LevelSelect;

public class LevelSelectScene {
    private GUI gui;
    //private final GameScene gameScene;
    private Scene levelSelectScene;
    private final LevelSelect levelMenu;
    private ReadWriteSpielstand readWriteSpielstand;

    private final Pane pLevelBlur = new Pane();
    private final Pane pLevelComponents = new Pane();
    private final SetMap setMap;

    private int mapSelected = 0;

    private boolean level1Completed = false;
    private boolean level2Completed = false;
    private boolean level3Completed = false;
    private boolean level4Completed = false;
    private boolean level5Completed = false;
    private boolean level6Completed = false;
    private boolean level7Completed = false;
    private boolean level8Completed = false;
    private boolean level9Completed = false;

    private boolean level1Unlocked = false;
    private boolean level2Unlocked = false;
    private boolean level3Unlocked = false;
    private boolean level4Unlocked = false;
    private boolean level5Unlocked = false;
    private boolean level6Unlocked = false;
    private boolean level7Unlocked = false;
    private boolean level8Unlocked = false;
    private boolean level9Unlocked = false;


    public LevelSelectScene(Stage stage, GUI gui) {
        this.gui = gui;
        //this.gameScene = gameScene;

        this.readWriteSpielstand = new ReadWriteSpielstand();

        this.levelMenu = new LevelSelect(this.gui,this);
        setMap = new SetMap(this.gui);
        createScene();

        this.readWriteSpielstand.spielstandRead("src/resources/csv/Save/spielstand.csv",this);
    }

    private void createScene() {
        StackPane levelSelectScreen = new StackPane();

        levelSelectScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        pLevelBlur.getChildren().add(this.levelMenu.getBackgroundLevelSelect());
        pLevelComponents.getChildren().add(this.levelMenu.getpLevelSelect());

        levelSelectScreen.getChildren().addAll(pLevelBlur,pLevelComponents);
        this.levelSelectScene = new Scene(levelSelectScreen, gui.getWidth(), gui.getHeight());
        this.levelMenu.getStartButton().requestFocus();

        levelMenu.getbL1().setOnAction(e ->{
            setMap(1);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL2().setOnAction(e ->{
            setMap(2);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL3().setOnAction(e ->{
            setMap(3);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL4().setOnAction(e ->{
            setMap(4);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL5().setOnAction(e ->{
            setMap(5);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL6().setOnAction(e ->{
            setMap(6);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL7().setOnAction(e ->{
            setMap(7);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL8().setOnAction(e ->{
            setMap(8);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getbL9().setOnAction(e ->{
            setMap(9);
            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();
        });

        levelMenu.getStartButton().setOnAction(e -> {
            System.out.println("[LevelSelectScene]: Spiel wird gestartet... ✔");
            this.gui.switchToGameScene();
        });
        levelMenu.getBackButton().setOnAction(e -> {
            System.out.println("[LevelSelectScene]: Start Menu ✔");
            this.gui.switchToStartScene();
        });
    }

    public void setMap(int map) {
        this.mapSelected = map;

        switch (mapSelected) {
            case 1:
                this.gui.setFilePath(this.setMap.getPath1());
                System.out.println("[LevelSelectScene]: 1 selected ✔");
                this.mapSelected = 1;
                break;
            case 2:
                this.gui.setFilePath(this.setMap.getPath2());
                System.out.println("[LevelSelectScene]: 2 selected ✔");
                this.mapSelected = 2;
                break;
            case 3:
                this.gui.setFilePath(this.setMap.getPath3());
                System.out.println("[LevelSelectScene]: 3 selected ✔");
                this.mapSelected = 3;
                break;
            case 4:
                this.gui.setFilePath(this.setMap.getPath4());
                System.out.println("[LevelSelectScene]: 4 selected ✔");
                this.mapSelected = 4;
                break;
            case 5:
                this.gui.setFilePath(this.setMap.getPath5());
                System.out.println("[LevelSelectScene]: 5 selected ✔");
                this.mapSelected = 5;
                break;
            case 6:
                this.gui.setFilePath(this.setMap.getPath6());
                System.out.println("[LevelSelectScene]: 6 selected ✔");
                this.mapSelected = 6;
                break;
            case 7:
                this.gui.setFilePath(this.setMap.getPath7());
                System.out.println("[LevelSelectScene]: 7 selected ✔");
                this.mapSelected = 7;
                break;
            case 8:
                this.gui.setFilePath(this.setMap.getPath8());
                System.out.println("[LevelSelectScene]: 8 selected ✔");
                this.mapSelected = 8;
                break;
            case 9:
                this.gui.setFilePath(this.setMap.getPath9());
                System.out.println("[LevelSelectScene]: 9 selected ✔");
                this.mapSelected = 9;
                break;
            default:
                System.out.println("[LevelSelectScene]: Invalid map selection: " + map + " ✖");
                break;
        }
    }

    public void levelCompleted() {
        if (level1Completed) {
            this.readWriteSpielstand.updateSpielstand(1,true,true);
            this.levelMenu.getbL1().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL1().setText("Level 1 ✔");
        }
        if (level2Completed) {
            this.readWriteSpielstand.updateSpielstand(2,true,true);
            this.levelMenu.getbL2().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL2().setText("Level 2 ✔");
        }
        if (level3Completed) {
            this.readWriteSpielstand.updateSpielstand(3,true,true);
            this.levelMenu.getbL3().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL3().setText("Level 3 ✔");
        }
        if (level4Completed) {
            this.readWriteSpielstand.updateSpielstand(4,true,true);
            this.levelMenu.getbL4().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL4().setText("Level 4 ✔");
        }
        if (level5Completed) {
            this.readWriteSpielstand.updateSpielstand(5,true,true);
            this.levelMenu.getbL5().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL5().setText("Level 5 ✔");
        }
        if (level6Completed) {
            this.readWriteSpielstand.updateSpielstand(6,true,true);
            this.levelMenu.getbL6().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL6().setText("Level 6 ✔");
        }
        if (level7Completed) {
            this.readWriteSpielstand.updateSpielstand(7,true,true);
            this.levelMenu.getbL7().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL7().setText("Level 7 ✔");
        }
        if (level8Completed) {
            this.readWriteSpielstand.updateSpielstand(8,true,true);
            this.levelMenu.getbL8().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL8().setText("Level 8 ✔");
        }
        if (level9Completed) {
            this.readWriteSpielstand.updateSpielstand(9,true,true);
            this.levelMenu.getbL9().setStyle("-fx-text-fill: #11b30e;");
            this.levelMenu.getbL9().setText("Level 9 ✔");
        }
        outputCU();
    }

    private void outputCU() {
        System.out.println("[LevelSelectScene]: outputCU");
        System.out.println("----------------------------------------------------");
        System.out.println("Level1 Unlocked: " + level1Unlocked + " | Level1 Completed: " + level1Completed);
        System.out.println("Level2 Unlocked: " + level2Unlocked + " | Level2 Completed: " + level2Completed);
        System.out.println("Level3 Unlocked: " + level3Unlocked + " | Level3 Completed: " + level3Completed);
        System.out.println("Level4 Unlocked: " + level4Unlocked + " | Level4 Completed: " + level4Completed);
        System.out.println("Level5 Unlocked: " + level5Unlocked + " | Level5 Completed: " + level5Completed);
        System.out.println("Level6 Unlocked: " + level6Unlocked + " | Level6 Completed: " + level6Completed);
        System.out.println("Level7 Unlocked: " + level7Unlocked + " | Level7 Completed: " + level7Completed);
        System.out.println("Level8 Unlocked: " + level8Unlocked + " | Level8 Completed: " + level8Completed);
        System.out.println("Level9 Unlocked: " + level9Unlocked + " | Level9 Completed: " + level9Completed);
        System.out.println("----------------------------------------------------");
    }

    public void unlockLevel() {
        if (level1Unlocked) {
            levelMenu.getbL1().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(1,true,false);
        }
        if (level2Unlocked) {
            levelMenu.getbL2().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(2,true,false);
        }
        if (level3Unlocked) {
            levelMenu.getbL3().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(3,true,false);
        }
        if (level4Unlocked) {
            levelMenu.getbL4().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(4,true,false);
        }
        if (level5Unlocked) {
            levelMenu.getbL5().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(5,true,false);
        }
        if (level6Unlocked) {
            levelMenu.getbL6().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(6,true,false);
        }
        if (level7Unlocked) {
            levelMenu.getbL7().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(7,true,false);
        }
        if (level8Unlocked) {
            levelMenu.getbL8().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(8,true,false);
        }
        if (level9Unlocked) {
            levelMenu.getbL9().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(9,true,false);
        }
        levelCompleted();
    }

    public void unlockNextLevel() {
        int nextMap = this.mapSelected;
        System.out.println("____________________\nmapSelecte:" + this.mapSelected + " | thismap: " + nextMap);
        if(++nextMap <= 10) {
            System.out.println("2____________________\nmapSelecte:" + this.mapSelected + " | thismap: " + nextMap);
            setLevelCompleted(this.mapSelected, true);
            setLevelUnlocked(++this.mapSelected, true);
        }
    }


    public boolean getLevelCompleted(int level) {
        boolean thisLevel = false;

        switch (level) {
            case 1:
                thisLevel = this.level1Completed;
                break;
            case 2:
                thisLevel = this.level2Completed;
                break;
            case 3:
                thisLevel = this.level3Completed;
                break;
            case 4:
                thisLevel = this.level4Completed;
                break;
            case 5:
                thisLevel = this.level5Completed;
                break;
            case 6:
                thisLevel = this.level6Completed;
                break;
            case 7:
                thisLevel = this.level7Completed;
                break;
            case 8:
                thisLevel = this.level8Completed;
                break;
            case 9:
                thisLevel = this.level9Completed;
                break;
            default:
                System.out.println("[LevelSelectScene]: getLevelCompleted Invalid value: " + level + " ✖");
                break;
        }
        return thisLevel;
    }

    public boolean getLevelUnlocked(int level) {
        boolean thisLevel = false;

        switch (level) {
            case 1:
                thisLevel = this.level1Unlocked;
                break;
            case 2:
                thisLevel = this.level2Unlocked;
                break;
            case 3:
                thisLevel = this.level3Unlocked;
                break;
            case 4:
                thisLevel = this.level4Unlocked;
                break;
            case 5:
                thisLevel = this.level5Unlocked;
                break;
            case 6:
                thisLevel = this.level6Unlocked;
                break;
            case 7:
                thisLevel = this.level7Unlocked;
                break;
            case 8:
                thisLevel = this.level8Unlocked;
                break;
            case 9:
                thisLevel = this.level9Unlocked;
                break;
            default:
                System.out.println("[LevelSelectScene]: getLevelCompleted Invalid value: " + level + " ✖");
                break;
        }
        return thisLevel;
    }


    public void setLevelCompleted(int level, boolean completed) {
        if (completed) {
            System.out.println("[LevelSelectScene]: setLevelCompleted level: " + level + " Completed ✔");
        }
        switch (level) {
            case 1:
                this.level1Completed = completed;
                break;
            case 2:
                this.level2Completed = completed;
                break;
            case 3:
                this.level3Completed = completed;
                break;
            case 4:
                this.level4Completed = completed;
                break;
            case 5:
                this.level5Completed = completed;
                break;
            case 6:
                this.level6Completed = completed;
                break;
            case 7:
                this.level7Completed = completed;
                break;
            case 8:
                this.level8Completed = completed;
                break;
            case 9:
                this.level9Completed = completed;
                levelCompleted();
                break;
            default:
                System.out.println("[LevelSelectScene]: setLevelCompleted level: " + level + " does not exist ✖");
                break;
        }
    }

    public void setLevelUnlocked(int level, boolean unlocked) {
        if (unlocked) {
            System.out.println("[LevelSelectScene]: setLevelUnlocked level: " + level + " unlocked ✔");
        }
        switch (level) {
            case 1:
                this.level1Unlocked = unlocked;
                break;
            case 2:
                this.level2Unlocked = unlocked;
                break;
            case 3:
                this.level3Unlocked = unlocked;
                break;
            case 4:
                this.level4Unlocked = unlocked;
                break;
            case 5:
                this.level5Unlocked = unlocked;
                break;
            case 6:
                this.level6Unlocked = unlocked;
                break;
            case 7:
                this.level7Unlocked = unlocked;
                break;
            case 8:
                this.level8Unlocked = unlocked;
                break;
            case 9:
                this.level9Unlocked = unlocked;
                break;
            default:
                System.out.println("[LevelSelectScene]: setLevelUnlocked level: " + level + " does not exist ✖");
                break;
        }
    }

    // Getter
    public LevelSelect getLevelMenu() {
        return levelMenu;
    }

    public Scene getScene() {
        return this.levelSelectScene;
    }

    public int getMapSelected() {
        return this.mapSelected;
    }

    // Setter
    public void setMapSelected(int map) {
        this.mapSelected = map;
    }

    public void setBlur(int strange){
        pLevelBlur.setEffect(new GaussianBlur(strange));
    }
}