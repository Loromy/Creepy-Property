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
import theCreepyProperty.main.Map.LevelData;
import theCreepyProperty.main.Map.MapCreate;
import theCreepyProperty.main.Map.MapReader;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.entity.Player;
import theCreepyProperty.menu.Menu;

public class GUI {
    private final int width = 1000;
    private final int height = 600;

    private final Pane root = new Pane(); //main
    private final Pane pMenu = new Pane(); //Menu
    private final Pane pGameOver = new Pane();
    private final Pane pGame = new Pane(); //game stuff

    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private final MapReader mapReader = new MapReader();
    private final MapCreate mapCreate = new MapCreate();
    private Wall wall; //todo entfernen
    private final Player player = new Player(this);
    private final GameOver gameOver = new GameOver(this);
    private final Menu menu = new Menu(this);
    private final GuiComponents guiComponents = new GuiComponents(this.player, this.menu);
    private final CollisionChecker checker = new CollisionChecker(this);

    public void start(Stage primaryStage) {
        // Create the scene with the specified width and height values
        root.getChildren().addAll(pGame,pGameOver,pMenu);
        Scene scene = new Scene(root, width, height);

        //Styles
        root.getStylesheets().add(("file:src/resources/style/style.css"));

        // Additional GUI components could be added here
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, null)));
        pGame.getChildren().add(this.player.draw());
        //pGame.getChildren().add(this.Wall1.getWall());
        //creatAllWall();
        pGame.getChildren().add(this.guiComponents.getL_speed());

        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());

        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());

        // Add the KeyHandler for keyboard input
        keyHandler = new KeyHandler(this.player, this, this.menu, this.gameOver);
        keyHandler.addKeyListener(scene);

        // CSV-Datei lesen
        String filePath = "src/resources/csv/maps/map1.csv"; // Pfad zur CSV-Datei
        this.levelData = mapReader.readCsvFile(filePath, levelData);

        //TODO Wände erstellen
        System.out.println("TEst ------------------------------------------");
        mapCreate.createMap(this, levelData);
        System.out.println("TEst2 ------------------------------------------");


//        //TODO Items ausgeben
//        System.out.println("\nItems:");
//        for (LevelData.Item item : levelData.getItems()) {
//            System.out.printf("Item: type=%s, x=%d, y=%d%n", item.getType(), item.getX(), item.getY());
//        }

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
        primaryStage.getIcons().add(new Image("file:src/resources/player/down_1.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        // Menu
        this.pMenu.setVisible(false);

        // Close the application when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        // Show primaryStage
        primaryStage.show();
    }

    public void pGameChildren(Rectangle rectangle) {
        this.pGame.getChildren().add(rectangle);//todo ractangel übergeben lassen von MapCreator und hier pG
        // ame.getChildren
    }

//    private void creatAllWall() {
//        //todo Test
//        LevelDataWall wall2 = new LevelDataWall(50, 50, 50, 50, "blue");
//        LevelDataWall wall3 = new LevelDataWall(200, 50, 500, 50, "red");
//
//        System.out.println(LevelDataWall.getWallList());
//
//        for (int i = 0; i < LevelDataWall.getWallList().size(); i++) {
//            System.out.println("i: " + i);
//            System.out.println(LevelDataWall.getWallList().get(i));
//
//            this.pGame.getChildren().add(LevelDataWall.getWallList().get(i).getWall());
//        }
//    }


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
        return wall;
    }

    public Player getPlayer() {
        return player;
    }

    public CollisionChecker getChecker() {
        return checker;
    }

    // Setter methods
    public void setBlur(int strange){
        pGame.setEffect(new GaussianBlur(strange));
    }
}
