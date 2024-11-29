package theCreepyProperty.main;

import theCreepyProperty.entity.Player;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GamePanel extends Application {

//    // SCREEN SETTINGS
//    final int original_tile_size = 32; // 32x32 tile
//    final int scale = 3; // zoom tile
//
//    public final int tile_size = original_tile_size * scale / 2; // 48x48 tile
//    public final int max_screen_col = 25; // width
//    public final int max_screen_row = 15; // height
//    public final int screen_width = tile_size * max_screen_col; // 1200 pixels
//    public final int screen_height = tile_size * max_screen_row; // 720 pixels
//
//    // WORLD SETTINGS
//    public final int max_world_col = 100; // set Level width
//    public final int max_world_row = 100; // set Level height
//    public final int world_width = tile_size * max_world_col;
//    public final int world_height = tile_size * max_world_row;

    public GUI gui;
    public Menu menu;
    public KeyHandler keyHandler;
    public Player player; // add Player

    // FPS
    int FPS = 60;

    private Canvas canvas;
    private GraphicsContext context;

    private long last_time = System.nanoTime();
    private double delta = 0;
    private final double draw_interval = 1000000000.0 / FPS;

    //public CollisionChecker checker = new CollisionChecker(this);
    //public AssetSetter aSetter = new AssetSetter(this);
    //public SuperObject[] obj = new SuperObject[20];

    public GamePanel(){
        gui = new GUI();
        menu = new Menu(this.gui);
        keyHandler = new KeyHandler(this.player, this.gui, this.menu);
        player = new Player(this.gui, this.keyHandler); // add Player
        System.out.println("[TEST]: 2"); //TODO löschen
    }

    public void start(Stage primaryStage) {
        gui.start(primaryStage);
        startGameLoop();
    }



        public void startGameLoop() {
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                delta += (now - last_time) / draw_interval;
                last_time = now;

                if (delta >= 1) {
                    draw();
                    delta--;
                }
            }
        };
        gameLoop.start();
    }


    public void draw() {
        // TODO: Add overlays or additional drawing here
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
}
