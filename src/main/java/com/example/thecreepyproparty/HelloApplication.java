package com.example.thecreepyproparty;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root,500,500);

        //HBox hBox = new HBox();
        Button button1 = new Button("hallo");
        button1.setLayoutX(100);
        button1.setLayoutY(300);
        root.getChildren().add(button1);

        //hBox.getChildren().add(button1);

        stage.setScene(scene);
        stage.setTitle("test game");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}