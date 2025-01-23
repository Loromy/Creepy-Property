package theCreepyProperty.main;

import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.Map.LevelData;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.Map.MapReader;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;
import javafx.scene.layout.StackPane;
import theCreepyProperty.screens.StartMenue;

public class GUI {

    // Window size
    private final int width = 1000 ; //1000
    private final int height = 600; //600

    // Start Scene Classes
    private final StartMenue startMenue = new StartMenue(this);

    // Game Scene Pane
    private final Pane root = new Pane(); //main
    private final Pane pMenu = new Pane(); //Menu
    private final Pane pGameOver = new Pane();
    private final Pane pGame = new Pane(); //game stuff

    // Game Scene Classes
    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private final MapReader mapReader = new MapReader();
    private final MapCreate mapCreate = new MapCreate();
    private Wall wall; //todo entfernen?
    private final Player player = new Player(this);
    private final GameOver gameOver = new GameOver(this);
    private final Menu menu = new Menu(this);
    private final GuiComponents guiComponents = new GuiComponents(this.player, this.menu);
    private final CollisionChecker checker = new CollisionChecker(this);

    public void start(Stage primaryStage) {
        // Start Screen
        StackPane startScreen = new StackPane();


//        StackPane.setAlignment(this.startMenue.getTitle(), javafx.geometry.Pos.TOP_CENTER);
//        StackPane.setAlignment(this.startMenue.getStartButton(), javafx.geometry.Pos.CENTER);

        //startScreen.getChildren().addAll(this.startMenue.getTitle(),this.startMenue.getStartButton());
        startScreen.getChildren().add(this.startMenue.getBackgroundStartMenu());
        startScreen.getChildren().add(this.startMenue.getpStartMenu());
        Scene startScene = new Scene(startScreen, width, height);

        // Game Screen
        root.getChildren().addAll(pGame,pGameOver,pMenu);
        Scene gameScene = new Scene(root, width, height);

        //Styles
        startScene.getStylesheets().add(("file:src/resources/style/style.css"));
        gameScene.getStylesheets().add(("file:src/resources/style/style.css"));

        // Additional GUI components could be added here
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.guiComponents.getL_speed());

        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());

        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());

        // Add the KeyHandler for keyboard input
        keyHandler = new KeyHandler(this.player, this, this.menu, this.gameOver, this.levelData);
        keyHandler.addKeyListener(gameScene);

        // CSV-Datei lesen
        String filePath = "src/resources/csv/maps/map1.csv"; // Pfad zur CSV-Datei
        this.levelData = mapReader.readCsvFile(filePath, levelData);

        // Wände erstellen
        mapCreate.createMap(this, levelData);


//        //TODO Items ausgeben
//        System.out.println("\nItems:");
//        for (LevelData.Item item : levelData.getItems()) {
//            System.out.printf("Item: type=%s, x=%d, y=%d%n", item.getType(), item.getX(), item.getY());
//        }

        startMenue.getStartButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Spiel wird gestartet...");
            primaryStage.setScene(gameScene);
        });
        startMenue.getQuitButton().setOnAction(e -> {
            System.out.println("[Start Menu]: Quit");
            System.exit(0);
        });

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
        primaryStage.getIcons().add(new Image("file:src/resources/player/down_1.png"));
        primaryStage.setResizable(false); //false
        primaryStage.setScene(startScene);

        // Menu
        this.pMenu.setVisible(false);

        // Close the application when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        // Show primaryStage
        primaryStage.show();
    }

    public void pGameChildren(Rectangle rectangle) {
        this.pGame.getChildren().add(rectangle);
    }

    // Getter methods
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GuiComponents getGuiComponents() {
        return guiComponents;
    }

    public Menu getMenu() {
        return menu;
    }

    public Pane getpMenu() {
        return pMenu;
    }

    public Pane getpGame() {
        return pGame;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public Wall getWall() {
        return this.wall;
    }

    public Player getPlayer() {
        return player;
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

    // Setter methods
    public void setBlur(int strange){
        pGame.setEffect(new GaussianBlur(strange));
    }
}
