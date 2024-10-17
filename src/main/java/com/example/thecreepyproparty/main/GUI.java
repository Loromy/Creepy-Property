package com.example.thecreepyproparty.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI extends Application {
    public void start(Stage primaryStage){
        Pane root = new Pane();
        Scene scene = new Scene(root, 1000, 600);

        GuiComponents.components();

        primaryStage.setOnCloseRequest(e -> System.exit(0));
        primaryStage.setTitle("The Creapy Propaty");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void startGui(){
        launch();
    }
}
