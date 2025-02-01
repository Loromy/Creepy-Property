package theCreepyProperty.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import theCreepyProperty.Map.LevelData;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.Map.MapReader;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Player;
import theCreepyProperty.main.*;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.screens.GameWin;

public class GameScene {

    private final Stage stage;  // Referenz zur Haupt-Stage
    private final GUI gui;      // Referenz zur GUI-Klasse
    private Scene gameScene;        // Szene des Spiels

    // Game Scene Pane
    private final Pane root = new Pane(); //main
    private final Pane pMenu = new Pane(); //Menu
    private final Pane pGameOver = new Pane();
    private final Pane pGameWin = new Pane();
    private final Pane pGame = new Pane(); //game stuff
    private final Pane pWallsItems = new Pane(); //Walls und Items


    // Game Scene Classes
    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private final MapReader mapReader = new MapReader();
    private final MapCreate mapCreate = new MapCreate();
    private Wall wall;
    private Item item;
    private Door door;
    private final Player player;
    private final GameOver gameOver;
    private final GameWin gameWin;
    private final Menu menu;
    private final GuiComponents guiComponents;
    private final CollisionChecker checker;

    public GameScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.player = new Player(this.gui);
        this.gameOver = new GameOver(this.gui,this);
        this.gameWin = new GameWin(this.gui, this);
        this.menu = new Menu(this.gui, this);
        this.guiComponents = new GuiComponents(this.player, this.menu);
        this.checker = new CollisionChecker(this);

        createScene();
    }

    private void createScene() {
        root.getChildren().addAll(pGame,pGameOver,pGameWin,pMenu);
        gameScene = new Scene(root, gui.getWidth(), gui.getHeight());

        //Styles //todo überprüfen ob style.css richtig geladen wurde
        gameScene.getStylesheets().add(("file:src/resources/style/style.css"));

        // Additional GUI components could be added here
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        pGame.getChildren().add(this.pWallsItems);
        pGame.getChildren().add(this.player.getSolidPlayerAria());
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.guiComponents.getL_speed());
        pGame.getChildren().add(this.guiComponents.getL_keys());
        pGame.getChildren().add(this.guiComponents.getL_fps());

        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());

        pGameWin.getChildren().add(this.gameWin.getBackgroundGameWin());
        pGameWin.getChildren().add(this.gameWin.getPGameWin());

        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());

        // Add the KeyHandler for keyboard input
        keyHandler = new KeyHandler(this.player, this, this.menu, this.gameOver, this.gameWin);
        keyHandler.addKeyListener(gameScene, this);

        // CSV-Datei lesen
        this.levelData = mapReader.readCsvFile(this.gui.getFilePath(), levelData);

        // Wände erstellen
        mapCreate.createMap(this, levelData);

        // Menu
        this.pMenu.setVisible(false);
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

    // Getter methode
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
        return  gameWin;
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

    // Setter methods
    public void setBlur(int strange){
        pGame.setEffect(new GaussianBlur(strange));
    }
}
