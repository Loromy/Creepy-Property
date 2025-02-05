package theCreepyProperty.scenes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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

    private final Stage stage;
    private final GUI gui;
    private Scene gameScene;

    // Game Scene Pane
    private final Pane root = new Pane();
    private final Pane pMenu = new Pane();
    private final Pane pGameOver = new Pane();
    private final Pane pGameWin = new Pane();
    private final Pane pGame = new Pane();
    private final Pane pWallsItems = new Pane();

    // Darkness Overlay
    private final Canvas darknessCanvas = new Canvas();

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
        this.gameOver = new GameOver(this.gui, this);
        this.gameWin = new GameWin(this.gui, this);
        this.menu = new Menu(this.gui, this);
        this.guiComponents = new GuiComponents(this.gui, this.player);
        this.checker = new CollisionChecker(this);

        createScene();
    }

    private void createScene() {
        root.getChildren().addAll(pGame, pGameOver, pGameWin, pMenu);
        gameScene = new Scene(root, gui.getWidth(), gui.getHeight());

        //Styles //todo überprüfen ob style.css richtig geladen wurde
        gameScene.getStylesheets().add(("file:src/resources/style/style.css"));
        pGame.getStylesheets().add(("file:src/resources/style/style.css"));

        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));

        pGame.getChildren().add(this.pWallsItems);
        pGame.getChildren().add(this.player.getSolidPlayerAria());
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(darknessCanvas); // black Overlay
        pGame.getChildren().add(this.guiComponents.getL_level());
        pGame.getChildren().add(this.guiComponents.getL_speed());
        pGame.getChildren().add(this.guiComponents.getL_keys());
        pGame.getChildren().add(this.guiComponents.getL_fps());

        // Darkness Overlay todo black overlay
        darknessCanvas.setWidth(gui.getWidth());
        darknessCanvas.setHeight(gui.getHeight());
        drawDarknessOverlay();  // Erstes Zeichnen

        // Game Over / Win Menüs hinzufügen
        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());

        pGameWin.getChildren().add(this.gameWin.getBackgroundGameWin());
        pGameWin.getChildren().add(this.gameWin.getPGameWin());

        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());

        // KeyHandler hinzufügen
        keyHandler = new KeyHandler(this.player, this, this.menu, this.gameOver, this.gameWin);
        keyHandler.addKeyListener(gameScene, this);

        // CSV-Datei lesen
        this.levelData = mapReader.readCsvFile(this.gui.getFilePath(), levelData);

        // Wände erstellen
        mapCreate.createMap(this, levelData);

        this.pMenu.setVisible(false);
    }

    private void drawDarknessOverlay() {
        GraphicsContext gc = darknessCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, darknessCanvas.getWidth(), darknessCanvas.getHeight());

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, darknessCanvas.getWidth(), darknessCanvas.getHeight());

        double playerX = player.getPlayer_world_X();
        double playerY = player.getPlayer_world_Y();
        double visionRadius = 70;

        gc.clearRect(playerX - visionRadius, playerY - visionRadius, visionRadius * 2, visionRadius * 2);

//        // Erzeuge eine weiche Transparenz um den Spieler
//        int i = 1;
//        //for (int i = 0; i < 10; i++) {
//            double alpha = 0.1 * (10 - i);
//            gc.setFill(new Color(0, 0, 0, alpha));
//            gc.fillOval(playerX - visionRadius - i * 5, playerY - visionRadius - i * 5,
//                    (visionRadius + i * 5) * 2, (visionRadius + i * 5) * 2);
//        //}
    }


    public void updateGameScene() {
        drawDarknessOverlay();
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
