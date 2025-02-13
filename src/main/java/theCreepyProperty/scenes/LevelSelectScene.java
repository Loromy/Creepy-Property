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
    private final GameScene gameScene;
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

    public LevelSelectScene(Stage stage, GUI gui, GameScene gameScene) {
        this.gui = gui;
        this.gameScene = gameScene;

        this.readWriteSpielstand = new ReadWriteSpielstand(this.gameScene.getGuiComponents());

        this.levelMenu = new LevelSelect(this.gui,this);
        setMap = new SetMap(this.gui);
        createScene();

        this.readWriteSpielstand.spielstandRead("src/resources/csv/Einstellungen/spielstand.csv",this,this.gameScene.getGuiComponents());
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

        });

        levelMenu.getbL5().setOnAction(e ->{

        });

        levelMenu.getbL6().setOnAction(e ->{

        });

        levelMenu.getbL7().setOnAction(e ->{

        });

        levelMenu.getbL8().setOnAction(e ->{

        });

        levelMenu.getbL9().setOnAction(e ->{

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
            default:
                System.out.println("[LevelSelectScene]: Invalid map selection ✖");
                break;
        }
    }

    public void unlockLevel() {
        if (level1Completed) {
            levelMenu.getbL2().setDisable(false);
        }
        if (level2Completed) {
            levelMenu.getbL3().setDisable(false);
        }
        if (level3Completed) {
            levelMenu.getbL4().setDisable(false);
        }
        if (level4Completed) {
            levelMenu.getbL5().setDisable(false);
        }
        if (level5Completed) {
            levelMenu.getbL6().setDisable(false);
        }
        if (level6Completed) {
            levelMenu.getbL7().setDisable(false);
        }
        if (level7Completed) {
            levelMenu.getbL8().setDisable(false);
        }
        if (level8Completed) {
            levelMenu.getbL9().setDisable(false);
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
            default:
                System.out.println("[LevelSelectScene]: get Invalid level ✖");
                break;
        }
        return thisLevel;
    }

    // Setter
    public void setMapSelected(int map) {
        this.mapSelected = map;
    }

    public void setBlur(int strange){
        pLevelBlur.setEffect(new GaussianBlur(strange));
    }

    public void setLevelCompleted(int map, boolean level) {
        System.out.println("[LevelSelectScene]: Map " + map + " selected ✔");
        switch (map) {
            case 1:
                this.level1Completed = level;
                break;
            case 2:
                this.level2Completed = level;
                break;
            case 3:
                this.level3Completed = level;
                break;
            case 4:
                this.level4Completed = level;
                break;
            case 5:
                this.level5Completed = level;
                break;
            case 6:
                this.level6Completed = level;
                break;
            case 7:
                this.level7Completed = level;
                break;
            case 8:
                this.level8Completed = level;
                break;
            default:
                System.out.println("[LevelSelectScene]: set Invalid level ✖");
                break;
        }
    }
}