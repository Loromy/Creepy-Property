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

    private Pane pLevelBlur = new Pane();
    private Pane pLevelComponents = new Pane();

    // Start Scene Classes
    private final LevelSelect levelMenue;

    public LevelSelectScene(Stage stage, GUI gui, GameScene gameScene) {
        //this.stage = stage;
        this.gui = gui;
        this.gameScene = gameScene;

        this.levelMenue = new LevelSelect(this.gui,this);
        createScene();
    }

    private void createScene() {
        // Start Screen
        StackPane levelSelectScreen = new StackPane();

        levelSelectScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        pLevelBlur.getChildren().add(this.levelMenue.getBackgroundLevelSelect());
        pLevelComponents.getChildren().add(this.levelMenue.getpLevelSelect());

        levelSelectScreen.getChildren().addAll(pLevelBlur,pLevelComponents);
        this.levelSelectScene = new Scene(levelSelectScreen, gui.getWidth(), gui.getHeight());
        this.levelMenue.getStartButton().requestFocus();

        levelMenue.getbL1().setOnAction(e ->{
            this.gui.setFilePath("src/resources/csv/maps/map1.csv");
            System.out.println("[Level]: 1 selected");
            levelMenue.getStartButton().setDisable(false);
        });

        levelMenue.getbL2().setOnAction(e ->{
            this.gui.setFilePath("src/resources/csv/maps/map2.csv");
            System.out.println("[Level]: 2 selected");
            levelMenue.getStartButton().setDisable(false);
        });

        levelMenue.getbL3().setOnAction(e ->{

        });

        levelMenue.getbL4().setOnAction(e ->{

        });

        levelMenue.getbL5().setOnAction(e ->{

        });

        levelMenue.getbL6().setOnAction(e ->{

        });

        levelMenue.getbL7().setOnAction(e ->{

        });

        levelMenue.getbL8().setOnAction(e ->{

        });

        levelMenue.getbL9().setOnAction(e ->{

        });

        levelMenue.getStartButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Spiel wird gestartet...");
            gui.switchToGameScene();
        });
        levelMenue.getBackButton().setOnAction(e -> {
            System.out.println("[Level Select Menu]: Start Menu");
            gui.switchToStartScene();
        });
    }

    public Scene getScene() {
        return this.levelSelectScene;
    }

    public void setBlur(int strange){
        pLevelBlur.setEffect(new GaussianBlur(strange));
    }
}
