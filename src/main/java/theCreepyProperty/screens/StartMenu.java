package theCreepyProperty.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.main.GUI;

public class StartMenu {
    private final GUI gui;
    private final Pane backgroundStartMenu = new Pane();
    private final Pane pStartMenu = new Pane();
    private final VBox menuContainer = new VBox();

    private Button startButton;
    private Button quitButton;

    public StartMenu(GUI gui) {
        System.out.println(".............................StartMenu..............................");
        this.gui = gui;

        setupUI();
        setupEventHandlers();

        backgroundStartMenu.setBackground(new Background(getBackgroundImage()));

        backgroundStartMenu.setVisible(true);
        pStartMenu.setVisible(true);

        pStartMenu.getChildren().add(menuContainer);
        pStartMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        backgroundStartMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        setMenuPosition();
    }

    // Initializes UI elements
    private void setupUI() {
        Label title = new Label("Creepy Property");
        title.setId("name");

        startButton = new Button("Play");
        quitButton = new Button("Quit");
        quitButton.setId("quit-button");

        menuContainer.getChildren().addAll(title, startButton, quitButton);
        menuContainer.setId("background");
    }

    // Loads the background image
    private BackgroundImage getBackgroundImage() {
        Image image = new Image(new FileCheck().checkImage("StartMenu", "file:src/resources/textures/background/Creepy Property background.png"));

        return new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
    }

    // Sets up button click actions
    private void setupEventHandlers() {
        // go to Level Select Scene Button
        startButton.setOnAction(_ -> {
            System.out.println("✔ [StartMenu]: Game starting...");
            gui.switchToGameScene();
        });

        // Quit Game Button
        quitButton.setOnAction(_ -> {
            System.out.println("✔ [StartMenu]: Quitting game...");
            System.exit(0);
        });
    }

    // Centers the menu on the screen
    private void setMenuPosition() {
        menuContainer.setPrefSize(500, 300);
        menuContainer.setLayoutX((gui.getWidth() - (double) 500) / 2);
        menuContainer.setLayoutY((gui.getHeight() - (double) 300) / 2);
        menuContainer.setSpacing(10);
        menuContainer.setAlignment(Pos.CENTER);
    }

    // Getter Methoden
    public Button getStartButton() { return startButton; }
    public Button getQuitButton() { return quitButton; }
    public Pane get_pStartMenu() { return pStartMenu; }
    public Pane getBackgroundStartMenu() { return backgroundStartMenu; }
}