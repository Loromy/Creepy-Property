package theCreepyProperty.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import theCreepyProperty.entity.Player;

public class GUI extends Application {
    private final int width = 1000;
    private final int height = 600;

    private final Pane root = new Pane();
    private final Player player = new Player(this);
    private final Menu menu = new Menu(this);
    private final GuiComponents guiComponents = new GuiComponents(this.player, this.menu);


    public void start(Stage primaryStage) {
        // Create the scene with the specified width and height values
        Scene scene = new Scene(root, width, height);

        // Additional GUI components could be added here
//        root.getChildren().add(this.player.getPlayer());
        root.getChildren().add(this.player.draw());
        root.getChildren().add(this.guiComponents.getL_speed());
        root.getChildren().add(this.menu.getBackgroundMenu());
        root.getChildren().add(this.menu.getpMenu());

        // Add the KeyHandler for keyboard input
        KeyHandler keyHandler = new KeyHandler(player,this, this.menu);
        keyHandler.addKeyListener(scene);

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);

        // Close the application when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.show();

        //gameLoop.startGameLoop();
    }

    public static void startGui(){
        launch();
    }

    // Getter methods
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public GuiComponents getGuiComponents() {return guiComponents;}
}
