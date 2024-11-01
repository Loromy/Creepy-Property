package com.example.thecreepyproparty.main;

import com.example.thecreepyproparty.entity.Player;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
    private final int width = 1000;
    private final int height = 600;

    Pane root = new Pane();
    Player player = new Player();


    public void start(Stage primaryStage){

        Scene scene = new Scene(root, width, height);

        //GuiComponents.components(); // TODO player über guicomponenten erstellen und verwallten.
        root.getChildren().add(player.getPlayer());

        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle("The Creapy Propaty");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void startGui(){
        launch();
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public Player getPlayer(){
        return player;
    }
}
