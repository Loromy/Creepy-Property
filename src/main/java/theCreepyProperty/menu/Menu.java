package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import theCreepyProperty.main.GUI;

import java.util.Objects;

public class Menu extends VBox {
    private GUI gui;
    private final Pane backgroundMenu = new Pane(); // Background
    private final Pane pMenu = new Pane(); // Menu Items
    private final VBox vBoxMenu = new VBox();
    private final Settings settings;
    private boolean menu_on = false;

    //private final Label name;
    private final Button resumeButton;
    private final Button settingsButton;
    private final Button quitButton;
    private final ImageView logoView;

    public Menu(GUI gui) {
        this.gui = gui;
        settings = new Settings(this.gui, this);

        //overlay
        backgroundMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        this.backgroundMenu.setVisible(false);
        this.pMenu.setVisible(false);

        // Add menu items
        //name = new Label("The Creepy Property");
        resumeButton = new Button("Back to Game");
        //resumeButton.setStyle("-fx-background-color: linear-gradient(#ff7f50, #ff4500);");// TODO Test
        settingsButton = new Button("Settings");
        quitButton = new Button("Quit Game");
        quitButton.setId("quit-button"); // Spezifische ID für den Quit-Button
        logoView = new ImageView(new Image("file:src/resources/logos/LogoGreen.png")); //Logo

        // Set styles
        //name.setFont(new Font("Arial", 20));
        //name.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;-fx-text-fill: rgb(143, 100, 0);");
        //name.setAlignment(Pos.CENTER);
//        resumeButton.setFont(new Font("Arial", 20)); // Schriftgröße auf 20 setzen
//        settingsButton.setFont(new Font("Arial", 20));
//        quitButton.setFont(new Font("Arial", 20));

        // Add buttons to the VBox
        this.vBoxMenu.getChildren().addAll(logoView, /*name,*/ resumeButton, settingsButton, quitButton);
        this.pMenu.getChildren().add(vBoxMenu);

        // Set size and position
        this.pMenu.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setMenuPosition(500, 500, 10);
        this.setMenuLayout(300,50);

        // Button actions
        resumeButton.setOnAction(e -> onResume());
        settingsButton.setOnAction(e -> onSettings());
        quitButton.setOnAction(e -> onQuit());
    }

    public void triggerMenu(){
        if (!menu_on) {
            this.backgroundMenu.setVisible(true);
            this.pMenu.setVisible(true);
            this.gui.setBlur(15); //Menu blur
            this.menu_on = true;
        } else {
            this.backgroundMenu.setVisible(false);
            this.pMenu.setVisible(false);
            this.gui.setBlur(0); //Menu blur
            this.menu_on = false;
        }
    }

    private void setMenuPosition(double width, double height, int spacing) {
        this.vBoxMenu.setPrefSize(width,height);
        this.vBoxMenu.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxMenu.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxMenu.setSpacing(spacing);
        this.vBoxMenu.setAlignment(Pos.CENTER);
    }

    private void setMenuLayout(double width, double heigth) {
        //name.setPrefSize(width * 2 - 200, heigth);
        resumeButton.setPrefSize(width, heigth);
        settingsButton.setPrefSize(width, heigth);
        quitButton.setPrefSize(width, heigth);
    }

    private void onResume() {
        System.out.println("[Menu]: Back to Game");
        this.triggerMenu();
    }

    private void onSettings() {
        System.out.println("[Menu]: Open settings menu!");
        pMenu.setVisible(false);
        this.settings.triggerSettings();
    }

    private void onQuit() {
        System.out.println("[Menu]: Quit game!");
        System.exit(0);
    }

    public boolean getMenu_on(){
        return this.menu_on;
    }

    public Pane getpMenu() {
        return pMenu;
    }

    public Pane getBackgroundMenu() {
        return backgroundMenu;
    }

    public Settings getSettings() {
        return this.settings;
    }
}
