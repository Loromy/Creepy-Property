package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import theCreepyProperty.Map.SetMap;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.menu.LevelSelect;

public class LevelSelectScene {
    private final GUI gui;
    private final LevelSelect levelMenu;
    private final ReadWriteSpielstand readWriteSpielstand;
    private final SoundPlayer soundPlayer;

    private Scene levelSelectScene;
    private final Pane pLevelBlur = new Pane();
    private final Pane pLevelComponents = new Pane();
    private final SetMap setMap;

    private int mapSelected = 0;
    private double timePlaying = 0; // zwischen speichern von time beim sterben.

    // Level Completed
    private boolean levelTutorialCompleted = false;
    private boolean level1Completed = false;
    private boolean level2Completed = false;
    private boolean level3Completed = false;
    private boolean level4Completed = false;
    private boolean level5Completed = false;
    private boolean level6Completed = false;
    private boolean level7Completed = false;
    private boolean level8Completed = false;
    private boolean level9Completed = false;

    // Level unlocked
    private boolean levelTutorialUnlocked = false;
    private boolean level1Unlocked = false;
    private boolean level2Unlocked = false;
    private boolean level3Unlocked = false;
    private boolean level4Unlocked = false;
    private boolean level5Unlocked = false;
    private boolean level6Unlocked = false;
    private boolean level7Unlocked = false;
    private boolean level8Unlocked = false;
    private boolean level9Unlocked = false;

    // Level Time
    private double thisLevelTime = 0.0;
    private double level0Time = 0.0;
    private double level1Time = 0.0;
    private double level2Time = 0.0;
    private double level3Time = 0.0;
    private double level4Time = 0.0;
    private double level5Time = 0.0;
    private double level6Time = 0.0;
    private double level7Time = 0.0;
    private double level8Time = 0.0;
    private double level9Time = 0.0;

    // Level Deaths
    private int thisLevelDeaths = 0;
    private int level0Deaths = 0;
    private int level1Deaths = 0;
    private int level2Deaths = 0;
    private int level3Deaths = 0;
    private int level4Deaths = 0;
    private int level5Deaths = 0;
    private int level6Deaths = 0;
    private int level7Deaths = 0;
    private int level8Deaths = 0;
    private int level9Deaths = 0;

    // Button vault Volumen
    private int volume = 50; //todo aus datei lesen

    public LevelSelectScene(GUI gui) {
        System.out.println(".............................LevelSelectScene..............................");
        this.gui = gui;

        soundPlayer = new SoundPlayer("src/resources/sounds/button click.wav");

        this.readWriteSpielstand = new ReadWriteSpielstand();

        this.levelMenu = new LevelSelect(this.gui,this);
        setMap = new SetMap(this.gui);
        createScene();

        // Audio set
        if (this.gui.getGameScene() != null) {
            this.volume = this.gui.getGameScene().getMenu().getSettings().getAudio().getMaster();
        }

        this.readWriteSpielstand.spielstandRead("src/resources/csv/Save/spielstand.csv",this);
    }

    private void createScene() {
        StackPane levelSelectScreen = new StackPane();

        levelSelectScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        pLevelBlur.getChildren().add(this.levelMenu.getBackgroundLevelSelect());
        pLevelComponents.getChildren().add(this.levelMenu.getpLevelSelect());

        levelSelectScreen.getChildren().addAll(pLevelBlur,pLevelComponents);
        this.levelSelectScene = new Scene(levelSelectScreen, gui.getWidth(), gui.getHeight());
        // Button OnAction
        levelMenu.getbL1().setOnAction(e ->{
            setMap(1);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL1());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(1,level1Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL2().setOnAction(e ->{
            setMap(2);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL2());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(2,level2Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL3().setOnAction(e ->{
            setMap(3);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL3());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(3,level3Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL4().setOnAction(e ->{
            setMap(4);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL4());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(4,level4Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL5().setOnAction(e ->{
            setMap(5);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL5());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(5,level5Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL6().setOnAction(e ->{
            setMap(6);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL6());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(6,level6Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL7().setOnAction(e ->{
            setMap(7);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL7());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(7,level7Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL8().setOnAction(e ->{
            setMap(8);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL8());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(8,level8Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbL9().setOnAction(e ->{
            setMap(9);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbL9());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(9,level9Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });

        levelMenu.getbTutorial().setOnAction(e ->{
            setMap(0);
            this.timePlaying = 0;
            this.thisLevelDeaths = 0;

            this.levelMenu.selectButton(this.levelMenu.getbTutorial());

            levelMenu.getStartButton().setDisable(false);
            this.levelMenu.getStartButton().requestFocus();

            formatTime(0, level0Time);

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();
        });



        levelMenu.getStartButton().setOnAction(e -> {
            System.out.println("✔ [LevelSelectScene]: Spiel wird gestartet...");

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();

            this.levelMenu.getStartButton().setDisable(true);
            this.levelMenu.getLevelTime().setText("Highscore: --:--:--:--");
            this.levelMenu.getLevel().setText("Level: -");

            this.levelMenu.selectButton(null);

            this.gui.switchToGameScene();
        });
        levelMenu.getBackButton().setOnAction(e -> {
            System.out.println("✔ [LevelSelectScene]: Start Menu");

            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();

            this.gui.switchToStartScene();
        });
    }

    // format Time
    private void formatTime(int level,double time_seconds) {
        // Berechnung der Zeitkomponenten
        int hours = (int) (time_seconds / 3600);
        int minutes = (int) ((time_seconds % 3600) / 60);
        int seconds = (int) (time_seconds % 60);
        int milliseconds = (int) ((time_seconds * 100) % 100); // Millisekunden berechnen

        // Formatierte Zeit als HH:MM:SS.mm anzeigen
        String formattedTime = String.format("%02dh : %02dm : %02ds : %02dms", hours, minutes, seconds, milliseconds);
        this.levelMenu.getLevelTime().setText("Highscore: " + formattedTime + " || " + this.getLevelDeaths(level) + " : Deaths");
        this.levelMenu.getLevel().setText("Level: " + level);
        if(level == 0) {
            this.levelMenu.getLevel().setText("Level: Tutorial");
        }
    }

    // Select Map
    public void setMap(int map) {
        this.mapSelected = map;

        switch (mapSelected) {
            case 0:
                System.out.println("✔ [LevelSelectScene]: 0 selected");
                this.gui.setFilePath(this.setMap.getPath0());
                this.mapSelected = 0;
                break;
            case 1:
                System.out.println("✔ [LevelSelectScene]: 1 selected");
                this.gui.setFilePath(this.setMap.getPath1());
                this.mapSelected = 1;
                break;
            case 2:
                System.out.println("✔ [LevelSelectScene]: 2 selected");
                this.gui.setFilePath(this.setMap.getPath2());
                this.mapSelected = 2;
                break;
            case 3:
                System.out.println("✔ [LevelSelectScene]: 3 selected");
                this.gui.setFilePath(this.setMap.getPath3());
                this.mapSelected = 3;
                break;
            case 4:
                System.out.println("✔ [LevelSelectScene]: 4 selected");
                this.gui.setFilePath(this.setMap.getPath4());
                this.mapSelected = 4;
                break;
            case 5:
                System.out.println("✔ [LevelSelectScene]: 5 selected");
                this.gui.setFilePath(this.setMap.getPath5());
                this.mapSelected = 5;
                break;
            case 6:
                System.out.println("✔ [LevelSelectScene]: 6 selected");
                this.gui.setFilePath(this.setMap.getPath6());
                this.mapSelected = 6;
                break;
            case 7:
                System.out.println("✔ [LevelSelectScene]: 7 selected");
                this.gui.setFilePath(this.setMap.getPath7());
                this.mapSelected = 7;
                break;
            case 8:
                System.out.println("✔ [LevelSelectScene]: 8 selected");
                this.gui.setFilePath(this.setMap.getPath8());
                this.mapSelected = 8;
                break;
            case 9:
                System.out.println("✔ [LevelSelectScene]: 9 selected");
                this.gui.setFilePath(this.setMap.getPath9());
                this.mapSelected = 9;
                break;
            default:
                System.err.println("✖ [LevelSelectScene]: Invalid map selection: " + map);
                break;
        }
    }

    //update CSV Level Completed
    public void levelCompleted() {
        if (levelTutorialCompleted) {
            System.out.println("levelCompleted: " + this.levelTutorialCompleted + " deaths: " + this.level0Deaths);
            this.readWriteSpielstand.updateSpielstand(0, true, true, this.level0Time, this.level0Deaths);
            this.levelMenu.getbTutorial().setStyle("-fx-text-fill: #6b5727;");
            this.levelMenu.getbTutorial().setText("Tutorial");
        }
        if (level1Completed) {
            this.readWriteSpielstand.updateSpielstand(1, true, true, this.level1Time, this.level1Deaths);
            this.levelMenu.getbL1().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL1().setText("Level 1 ✔");
        }
        if (level2Completed) {
            this.readWriteSpielstand.updateSpielstand(2, true, true, this.level2Time, this.level2Deaths);
            this.levelMenu.getbL2().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL2().setText("Level 2 ✔");
        }
        if (level3Completed) {
            this.readWriteSpielstand.updateSpielstand(3, true, true, this.level3Time, this.level3Deaths);
            this.levelMenu.getbL3().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL3().setText("Level 3 ✔");
        }
        if (level4Completed) {
            this.readWriteSpielstand.updateSpielstand(4, true, true, this.level4Time, this.level4Deaths);
            this.levelMenu.getbL4().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL4().setText("Level 4 ✔");
        }
        if (level5Completed) {
            this.readWriteSpielstand.updateSpielstand(5, true, true, this.level5Time, this.level5Deaths);
            this.levelMenu.getbL5().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL5().setText("Level 5 ✔");
        }
        if (level6Completed) {
            this.readWriteSpielstand.updateSpielstand(6, true, true, this.level6Time, this.level6Deaths);
            this.levelMenu.getbL6().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL6().setText("Level 6 ✔");
        }
        if (level7Completed) {
            this.readWriteSpielstand.updateSpielstand(7, true, true, this.level7Time, this.level7Deaths);
            this.levelMenu.getbL7().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL7().setText("Level 7 ✔");
        }
        if (level8Completed) {
            this.readWriteSpielstand.updateSpielstand(8, true, true, this.level8Time, this.level8Deaths);
            this.levelMenu.getbL8().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL8().setText("Level 8 ✔");
        }
        if (level9Completed) {
            this.readWriteSpielstand.updateSpielstand(9, true, true, this.level9Time, this.level9Deaths);
            this.levelMenu.getbL9().setStyle("-fx-text-fill: #10540a;");
            this.levelMenu.getbL9().setText("Level 9 ✔");
        }
    }

    // unlock level
    public void unlockLevel() {
        if (levelTutorialUnlocked) {
            levelMenu.getbTutorial().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(0, true, false, this.level0Time, this.level0Deaths);
        }
        if (level1Unlocked) {
            levelMenu.getbL1().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(1, true, false, this.level1Time, this.level1Deaths);
        }
        if (level2Unlocked) {
            levelMenu.getbL2().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(2, true, false, this.level2Time, this.level2Deaths);
        }
        if (level3Unlocked) {
            levelMenu.getbL3().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(3, true, false, this.level3Time, this.level3Deaths);
        }
        if (level4Unlocked) {
            levelMenu.getbL4().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(4, true, false, this.level4Time, this.level4Deaths);
        }
        if (level5Unlocked) {
            levelMenu.getbL5().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(5, true, false, this.level5Time, this.level5Deaths);
        }
        if (level6Unlocked) {
            levelMenu.getbL6().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(6, true, false, this.level6Time, this.level6Deaths);
        }
        if (level7Unlocked) {
            levelMenu.getbL7().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(7, true, false, this.level7Time, this.level7Deaths);
        }
        if (level8Unlocked) {
            levelMenu.getbL8().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(8, true, false, this.level8Time, this.level8Deaths);
        }
        if (level9Unlocked) {
            levelMenu.getbL9().setDisable(false);
            this.readWriteSpielstand.updateSpielstand(9, true, false, this.level9Time, this.level9Deaths);
        }
        levelCompleted();
    }

    // set time as Highscore if it is better than the old one
    public void setThisLevelTime(double time) {
        int nextMap = this.mapSelected + 1;
        this.thisLevelTime = time;

        switch (this.mapSelected) {
            case 0:
                if (this.thisLevelTime < level0Time || level0Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 1:
                if (this.thisLevelTime < level1Time || level1Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 2:
                if (this.thisLevelTime < level2Time || level2Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 3:
                if (this.thisLevelTime < level3Time || level3Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 4:
                if (this.thisLevelTime < level4Time || level4Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 5:
                if (this.thisLevelTime < level5Time || level5Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 6:
                if (this.thisLevelTime < level6Time || level6Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 7:
                if (this.thisLevelTime < level7Time || level7Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 8:
                if (this.thisLevelTime < level8Time || level8Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
            case 9:
                if (this.thisLevelTime < level9Time || level9Time == 0.0) {
                    setTimeAndDeaths();
                }
                break;
        }

        setLevelCompleted(this.mapSelected, true);
        if (nextMap < 10) {
            setLevelUnlocked(nextMap, true);
        }
        //}
    }

    // update time und death
    private void setTimeAndDeaths() {
        setLevelTime(this.mapSelected, this.thisLevelTime);
        setLevelDeaths(this.mapSelected, this.thisLevelDeaths);
    }

    // mark level as Completed
    public void setLevelCompleted(int level, boolean completed) {
        if (completed) {
            System.out.println("✔ [LevelSelectScene]: setLevelCompleted level: " + level + " Completed");
        }
        switch (level) {
            case 0:
                this.levelTutorialCompleted = completed;
                break;
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
                break;
            default:
                System.err.println("✖ [LevelSelectScene]: setLevelCompleted level: " + level + " does not exist");
                break;
        }
        levelCompleted();
    }

    // mark level as Unlocked
    public void setLevelUnlocked(int level, boolean unlocked) {
        if (unlocked) {
            System.out.println("✔ [LevelSelectScene]: setLevelUnlocked level: " + level + " unlocked");
        }
        switch (level) {
            case 0:
                this.levelTutorialUnlocked = unlocked;
                break;
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
                System.err.println("✖ [LevelSelectScene]: setLevelUnlocked level: " + level + " does not exist");
                break;
        }
    }

    // set level Time
    public void setLevelTime(int level, double time) {
        switch (level) {
            case 0:
                this.level0Time = time;
                break;
            case 1:
                this.level1Time = time;
                break;
            case 2:
                this.level2Time = time;
                break;
            case 3:
                this.level3Time = time;
                break;
            case 4:
                this.level4Time = time;
                break;
            case 5:
                this.level5Time = time;
                break;
            case 6:
                this.level6Time = time;
                break;
            case 7:
                this.level7Time = time;
                break;
            case 8:
                this.level8Time = time;
                break;
            case 9:
                this.level9Time = time;
                break;
            default:
                System.err.println("✖ [LevelSelectScene]: setLevelTime level: " + level + " does not exist");
                break;
        }
    }

    // set level Death
    public void setLevelDeaths(int level, int deaths) {
        switch (level) {
            case 0:
                this.level0Deaths = deaths;
                break;
            case 1:
                this.level1Deaths = deaths;
                break;
            case 2:
                this.level2Deaths = deaths;
                break;
            case 3:
                this.level3Deaths = deaths;
                break;
            case 4:
                this.level4Deaths = deaths;
                break;
            case 5:
                this.level5Deaths = deaths;
                break;
            case 6:
                this.level6Deaths = deaths;
                break;
            case 7:
                this.level7Deaths = deaths;
                break;
            case 8:
                this.level8Deaths = deaths;
                break;
            case 9:
                this.level9Deaths = deaths;
                break;
            default:
                System.err.println("✖ [LevelSelectScene]: setLevelDeaths level: " + level + " does not exist");
                break;
        }
    }

    // Getter methoden
    public int getLevelDeaths(int level) {
        int thisLevelDeaths = 0;

        switch (level) {
            case 0:
                thisLevelDeaths = this.level0Deaths;
                break;
            case 1:
                thisLevelDeaths = this.level1Deaths;
                break;
            case 2:
                thisLevelDeaths = this.level2Deaths;
                break;
            case 3:
                thisLevelDeaths = this.level3Deaths;
                break;
            case 4:
                thisLevelDeaths = this.level4Deaths;
                break;
            case 5:
                thisLevelDeaths = this.level5Deaths;
                break;
            case 6:
                thisLevelDeaths = this.level6Deaths;
                break;
            case 7:
                thisLevelDeaths = this.level7Deaths;
                break;
            case 8:
                thisLevelDeaths = this.level8Deaths;
                break;
            case 9:
                thisLevelDeaths = this.level9Deaths;
                break;
            default:
                System.err.println("✖ [LevelSelectScene]: getLevelCompleted Invalid value: " + level);
                break;
        }
        return thisLevelDeaths;
    }

    public LevelSelect getLevelMenu() {
        return levelMenu;
    }

    public Scene getScene() {
        return this.levelSelectScene;
    }

    public int getMapSelected() {
        return this.mapSelected;
    }

    public double getTimePlaying() {
        return this.timePlaying;
    }

    public double getThisLevelTime() {
        return this.thisLevelTime;
    }

    public int getThisLevelDeaths() {
        return this.thisLevelDeaths;
    }

    // Setter methoden
    public void setMapSelected(int map) {
        this.mapSelected = map;
    }

    public void setBlur(int strange){
        pLevelBlur.setEffect(new GaussianBlur(strange));
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setTimePlaying(double time) {
        this.timePlaying = time;
    }

    public void setThisLevelDeaths(int deaths) {
        this.thisLevelDeaths = deaths;
    }

    public void setThisLevelDeathsPlusOne() {
        this.thisLevelDeaths++;
    }
}