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
    private final Pane backgroundStartMenu = new Pane(); // Background
    private final Pane pStartMenu = new Pane(); // Menu Items
    private final VBox vBoxGameOver = new VBox();

    private final Label title;
    private final Button startButton;
    private final Button quitButton;

    public StartMenu(GUI gui) {
        System.out.println(".............................StartMenu..............................");
        this.gui = gui;

        // Lade das Bild
        BackgroundImage backgroundImage = getBackgroundImage();
        backgroundStartMenu.setBackground(new Background(backgroundImage));

        this.backgroundStartMenu.setVisible(true);
        this.pStartMenu.setVisible(true);

        // Add menu items
        this.title = new Label("Creepy Property");
        this.title.setId("name");
        this.startButton = new Button("Play");
        this.quitButton = new Button("Quit");
        this.quitButton.setId("quit-button");

        // Add buttons to the VBox
        this.vBoxGameOver.getChildren().addAll(title, startButton, quitButton);
        this.vBoxGameOver.setId("background");
        this.pStartMenu.getChildren().add(vBoxGameOver);

        // Set size and position
        this.pStartMenu.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundStartMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setGameOverPosition(500, 300, 10);
    }

    private BackgroundImage getBackgroundImage() {
        Image image = new Image(new FileCheck().checkImage("StartMenu", "file:src/resources/textures/background/Creepy Property background.png"));

        // Setze das Hintergrundbild
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
                /*
                v: width
                Gibt die Breite des Hintergrundbildes an. Die Einheit ist entweder absolut (z. B. Pixel) oder relativ, wenn widthAsPercentage = true.

                v1: height
                Gibt die Höhe des Hintergrundbildes an. Die Einheit funktioniert wie bei width.

                b: widthAsPercentage
                Wenn true, wird die Breite als Prozentsatz der Breite des Panes interpretiert.

                b1: heightAsPercentage
                Wenn true, wird die Höhe als Prozentsatz der Höhe des Panes interpretiert.

                b2: contain
                Wenn true, wird das Bild so skaliert, dass es vollständig in den Bereich des Panes passt, ohne das Seitenverhältnis zu verzerren.

                b3: cover
                Wenn true, wird das Bild so skaliert, dass es den gesamten Bereich des Panes bedeckt.
                 */
        );
        return backgroundImage;
    }

    // Getter Methoden
    public Button getStartButton() {
        return startButton;
    }

    public Button getQuitButton() {
        return quitButton;
    }

    public Pane getpStartMenu() {
        return pStartMenu;
    }

    public Pane getBackgroundStartMenu() {
        return backgroundStartMenu;
    }

    // Setter Methoden
    private void setGameOverPosition(double width, double height, int spacing) {
        this.vBoxGameOver.setPrefSize(width,height);
        this.vBoxGameOver.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxGameOver.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxGameOver.setSpacing(spacing);
        this.vBoxGameOver.setAlignment(Pos.CENTER);
    }
}