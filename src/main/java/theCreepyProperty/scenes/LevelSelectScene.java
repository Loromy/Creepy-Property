package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import theCreepyProperty.main.GUI;
import theCreepyProperty.menu.LevelSelect;

public class LevelSelectScene {

    //private final Stage stage;
    private final GUI gui;
    private final GameScene gameScene;
    private Scene levelSelectScene;
    private final LevelSelect levelMenu;

    private final Pane pLevelBlur = new Pane();
    private final Pane pLevelComponents = new Pane();

    private int mapSelected = 0;

    private boolean level1Completed = false;
    private boolean level2Completed = false;
    private boolean level3Completed = false;
    private boolean level4Completed = false;
    private boolean level5Completed = false;
    private boolean level6Completed = false;
    private boolean level7Completed = false;
    private boolean level8Completed = false;
    //private boolean level9Completed = false;

    public LevelSelectScene(Stage stage, GUI gui, GameScene gameScene) {
        //this.stage = stage;
        this.gui = gui;
        this.gameScene = gameScene;

        this.levelMenu = new LevelSelect(this.gui,this);
        createScene();
    }

    private void createScene() {
        // Start Screen
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
        });

        levelMenu.getbL2().setOnAction(e ->{
            setMap(2);
            levelMenu.getStartButton().setDisable(false);
        });

        levelMenu.getbL3().setOnAction(e ->{
            setMap(3);
            levelMenu.getStartButton().setDisable(false);
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
                this.gui.setFilePath("src/resources/csv/maps/map1.csv");
                System.out.println("[LevelSelectScene]: 1 selected ✔");
                this.mapSelected = 1;
                break;
            case 2:
                this.gui.setFilePath("src/resources/csv/maps/map2.csv");
                System.out.println("[LevelSelectScene]: 2 selected ✔");
                this.mapSelected = 2;
                break;
            case 3:
                this.gui.setFilePath("src/resources/csv/maps/map3.csv");
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
//            case 9:
//                thisLevel = this.level9Completed;
//                break;

            default:
                System.out.println("[LevelSelectScene]: get Invalid level ✖");
                break;
        }
        return thisLevel;
    }

    public void setLevelCompleted(int map, boolean level) {
        System.out.println("test___________________________map: " + map);
        switch (map) {
            case 1:
                this.level1Completed = level;
                System.out.println("level1: " + this.level1Completed);
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
//            case 9:
//                this.level9Completed = level;
//                break;

            default:
                System.out.println("[LevelSelectScene]: set Invalid level ✖");
                break;
        }
    }
}