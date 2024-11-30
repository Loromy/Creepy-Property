package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.GamePanel;

public class Menu extends VBox {
    private GUI gui;
    private final Pane backgroundMenu = new Pane(); // Background
    private final Pane pMenu = new Pane(); // Menu Items
    private final VBox vBoxMenu = new VBox();
    private final Settings settings;
    private boolean menu_on = false;

    private final Label name;
    private final Button resumeButton;
    private final Button settingsButton;
    private final Button quitButton;

    public Menu(GUI gui) {
        this.gui = gui;
        settings = new Settings(this.gui, this);

        // Styling for the overlay
        this.backgroundMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        //this.backgroundMenu.setOpacity(1); // Set 50% transparency
        this.backgroundMenu.setOpacity(0.8); // 80% Deckkraft
        this.backgroundMenu.setEffect(new GaussianBlur(15)); // Unschärfe-Effekt hinzufügen
        this.backgroundMenu.setVisible(false);
        this.pMenu.setVisible(false);


        //this.vBoxMenu.setStyle("-fx-background-color: rgba(0, 2, 0, 0.5);");//TODO TEST


        // Add menu items
        name = new Label("The Creepy Property");
        resumeButton = new Button("Back to Game");
        settingsButton = new Button("Settings");
        quitButton = new Button("Quit Game");

        name.setFont(new Font("Arial", 20));
        name.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        name.setAlignment(Pos.CENTER);
        name.setTextFill(Color.DARKRED);
        resumeButton.setFont(new Font("Arial", 20)); // Schriftgröße auf 20 setzen
        settingsButton.setFont(new Font("Arial", 20));
        quitButton.setFont(new Font("Arial", 20));

        // Add buttons to the VBox
        this.vBoxMenu.getChildren().addAll(name, resumeButton, settingsButton, quitButton);
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
            this.menu_on = true;
        } else {
            this.backgroundMenu.setVisible(false);
            this.pMenu.setVisible(false);
            this.menu_on = false;
        }
    }

    private void setMenuPosition(double sizeX, double sizeY, int spacing) {
        this.vBoxMenu.setPrefSize(sizeX,sizeY);
        this.vBoxMenu.setLayoutX((gui.getWidth() - sizeX) / 2);
        this.vBoxMenu.setLayoutY((gui.getHeight() - sizeY) / 2);
        this.vBoxMenu.setSpacing(spacing);
        this.vBoxMenu.setAlignment(Pos.CENTER);
    }

    private void setMenuLayout(double sizeX, double sizeY) {
        name.setPrefSize(sizeX * 2 - 200, sizeY);
        resumeButton.setPrefSize(sizeX, sizeY);  // Breite: 500px, Höhe: 50px
        settingsButton.setPrefSize(sizeX, sizeY);
        quitButton.setPrefSize(sizeX, sizeY);
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
