package theCreepyProperty.main;

import theCreepyProperty.entity.Player;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import theCreepyProperty.menu.Menu;

public class GamePanel extends Application {

    // SCREEN SETTINGS
//    final int original_tile_size = 32; // 32x32 tile
//    final int scale = 3; // zoom tile
//
//    public final int tile_size = original_tile_size * scale / 2; // 48x48 tile
//    public final int max_screen_col = 25; // width
//    public final int max_screen_row = 15; // height
//    public final int screen_width = tile_size * max_screen_col; // 1200 pixels
//    public final int screen_height = tile_size * max_screen_row; // 720 pixels


    // WORLD SETTINGS
    private final int screen_width = 1000;
    private final int screen_height = 600;
//    public final int max_world_col = 100; // set Level width
//    public final int max_world_row = 100; // set Level height
//    public final int world_width = tile_size * max_world_col;
//    public final int world_height = tile_size * max_world_row;

//    public GUI gui;
//    public Menu menu;
//    public KeyHandler keyHandler;
//    public Player player; // add Player

    //private final Menu menu = new Menu(this);
    private final KeyHandler keyHandler = new KeyHandler();
    private  final GUI gui = new GUI(this, this.keyHandler);
    private final Player player = new Player(this, this.gui, this.keyHandler, this.gui.getMenu());

    Thread game_thread;


    // FPS
    int FPS = 60;

    private Canvas canvas;
    private GraphicsContext context;

    private long last_time = System.nanoTime();
    private double delta = 0;
    private final double draw_interval = 1000000000.0 / FPS;
//
//    public CollisionChecker checker = new CollisionChecker(this);
//    public AssetSetter aSetter = new AssetSetter(this);
//    public SuperObject[] obj = new SuperObject[20];

    public GamePanel(){
        startGameLoop();
    }

    public void start(Stage primaryStage) {
        gui.start(primaryStage);
        //startGameLoop();
    }

    public void update() {
        player.update();
    }

    public void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                delta += (now - last_time) / draw_interval;
                last_time = now;

                if (delta >= 1) {
                    //UPDATE:
                    update();

                    draw();
                    delta--;
                }
            }
        };
        gameLoop.start();
    }

    public void draw() {
        // TODO: Add overlays or additional drawing here
        System.out.println("[GameLoop]: running");
    }

//    private void handleKeyPressed(KeyEvent event) {
//        keyHandler.handleKeyPressed(event);
//    }
//
//    private void handleKeyReleased(KeyEvent event) {
//        keyHandler.handleKeyReleased(event);
//    }

    public static void startGamePanel(){
        launch();
    }

    public int getScreen_height() {
        return screen_height;
    }

    public int getScreen_width() {
        return screen_width;
    }
}
