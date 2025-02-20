package theCreepyProperty.scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import theCreepyProperty.Map.LevelData;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.Map.MapReader;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Player;
import theCreepyProperty.main.*;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.screens.GameWin;

public class GameScene {

    private final Stage stage;
    private final GUI gui;
    private Scene gameScene;
    private SoundPlayer soundPlayer;

    // Game Scene Pane
    private final Pane root = new Pane();
    private final Pane pMenu = new Pane();
    private final Pane pGameOver = new Pane();
    private final Pane pGameWin = new Pane();
    private final Pane pGame = new Pane();
    private final Pane pWallsItems = new Pane();

    // Game Scene Classes
    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private final MapReader mapReader = new MapReader();
    private final MapCreate mapCreate = new MapCreate();
    private ReadWriteSettings readWriteSettings;
    private ReadWriteSpielstand readWriteSpielstand;
    private Wall wall;
    private Item item;
    private Door door;
    private final Player player;
    private final GameOver gameOver;
    private final GameWin gameWin;
    private final Menu menu;
    private GuiComponents guiComponents;
    private final CollisionChecker checker;

    private Timeline timer;
    private double time_seconds = 0.0;

    public GameScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.player = new Player(this.gui);
        this.gameOver = new GameOver(this.gui, this);
        this.gameWin = new GameWin(this.gui, this);
        this.menu = new Menu(this.gui, this);
        this.checker = new CollisionChecker(this);
        this.readWriteSettings = new ReadWriteSettings(this.guiComponents);

        createScene();

        // Settings Set
        this.readWriteSettings.settingsRead("src/resources/csv/Einstellungen/settings.csv",this,this.guiComponents);

    }

    private void createScene() {
        // CSV-Datei lesen
        this.levelData = mapReader.readCsvFile(this.gui.getFilePath(), levelData);

        // Wände erstellen
        this.mapCreate.createMap(this, levelData);

        // GUI components
        this.guiComponents = new GuiComponents(this.gui, this.player,this);

        root.getChildren().addAll(pGame, pGameOver, pGameWin, pMenu);
        gameScene = new Scene(root, gui.getWidth(), gui.getHeight());

        //Styles //todo überprüfen ob style.css richtig geladen wurde
        gameScene.getStylesheets().add(("file:src/resources/style/style.css"));
        pGame.getStylesheets().add(("file:src/resources/style/style.css"));

        pGame.getChildren().add(new ImageView(new Image("file:src/resources/textures/flor/Flor.png")));
        pGame.getChildren().add(this.pWallsItems);
        pGame.getChildren().add(this.player.getSolidPlayerAria());
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.player.loadOverlay());
        pGame.getChildren().add(this.guiComponents.gethBox_Level());
        pGame.getChildren().add(this.guiComponents.getvBox_anzeige());
        pGame.getChildren().add(this.guiComponents.gethBox_keys());
        pGame.getChildren().add(this.guiComponents.getvBox_Time());

        // Game Over / Win Menüs hinzufügen
        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());

        pGameWin.getChildren().add(this.gameWin.getBackgroundGameWin());
        pGameWin.getChildren().add(this.gameWin.getPGameWin());

        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());
        pMenu.getChildren().add(this.menu.getSettings().getAudio().getMenuAudio());

        // KeyHandler hinzufügen
        keyHandler = new KeyHandler(this.player, this, this.menu, this.gameOver, this.gameWin);
        keyHandler.addKeyListener(gameScene, this);

        this.pMenu.setVisible(false);

        //timer Start
        startTimer();

        // play sound
        this.soundPlayer = new SoundPlayer("src/resources/sounds/background/background-atmosphere.wav");
        this.soundPlayer.setVolume(this.getMenu().getSettings().getAudio().getMaster()); // Standard 2
        this.soundPlayer.play();
    }

    private void startTimer() {
        time_seconds = 0.0; // Timer zurücksetzen
        timer = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            time_seconds += 0.01;

            // Berechnung der Zeitkomponenten
            int hours = (int) (time_seconds / 3600);
            int minutes = (int) ((time_seconds % 3600) / 60);
            int seconds = (int) (time_seconds % 60);
            int milliseconds = (int) ((time_seconds * 100) % 100); // Millisekunden berechnen

            // Formatierte Zeit als HH:MM:SS.mmm anzeigen
            String formattedTime = String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds);
            this.guiComponents.getL_time().setText("Time: " + formattedTime);
        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    public double getTime_seconds() {
        return time_seconds;
    }

    public void pGameChildren(Rectangle rectangle) {
        this.pWallsItems.getChildren().add(rectangle);
    }

    public void pGameChildren(ImageView image) {
        this.pWallsItems.getChildren().add(image);
    }

    public void pGameChildrenRemove(ImageView image) {
        this.pWallsItems.getChildren().remove(image);
    }

    // Getter Methoden
    public GuiComponents getGuiComponents() {
        return guiComponents;
    }

    public Menu getMenu() {
        return menu;
    }

    public Pane getpMenu() {
        return pMenu;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public GameWin getGameWin() {
        return gameWin;
    }

    public Wall getWall() {
        return this.wall;
    }

    public Item getItem() {
        return this.item;
    }

    public Door getDoor() {
        return this.door;
    }

    public CollisionChecker getChecker() {
        return checker;
    }

    public LevelData getLevelData() {
        return levelData;
    }

    public MapCreate getMapCreate() {
        return mapCreate;
    }

    public Scene getScene() {
        return this.gameScene;
    }

    public Player getPlayer() {
        return this.player;
    }

    // Setter Methoden
    public void setBlur(int strange) {
        pGame.setEffect(new GaussianBlur(strange));
    }
}
