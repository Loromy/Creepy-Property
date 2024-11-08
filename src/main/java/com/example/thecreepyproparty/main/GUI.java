package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
    private final int width = 1000;
    private final int height = 600;

    private Pane root = new Pane();
    private Player player = new Player();

    public void start(Stage primaryStage) {
        // Create the scene with the specified width and height values
        Scene scene = new Scene(root, width, height);

        // Additional GUI components could be added here
        root.getChildren().add(player.getPlayer());

        // Add the KeyHandler for keyboard input
        KeyHandler keyHandler = new KeyHandler(player);
        keyHandler.addKeyListener(scene);

        // Set the settings for the stage
        primaryStage.setTitle("The Creepy Proparty");
        primaryStage.setScene(scene);

        // Close the application when the window is closed
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        primaryStage.show();
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
}
