package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
    private final int width = 1000;
    private final int height = 600;

    private final Pane root = new Pane();
    private final Player player = new Player();
    private final Menu menu = new Menu(this); // Create an instance of the GameOverlayMenu
    private final GuiComponents guiComponents = new GuiComponents(this.player, this.menu);


    public void start(Stage primaryStage) {
        // Create the scene with the specified width and height values
        Scene scene = new Scene(root, width, height);

        // Additional GUI components could be added here
        root.getChildren().add(player.getPlayer());
        root.getChildren().add(guiComponents.getL_speed());
        root.getChildren().add(guiComponents.getB_menu());
        root.getChildren().addAll(menu.getMenu());


        // Add the KeyHandler for keyboard input
        KeyHandler keyHandler = new KeyHandler(player,this, menu);
        keyHandler.addKeyListener(scene);

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
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
