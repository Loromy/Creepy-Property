package theCreepyProperty.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Menu extends VBox {
    private GUI gui;
    private final Pane backgroundMenu = new Pane();
    private final Pane pMenu = new Pane();
    private final VBox vBoxMenu = new VBox();
    private boolean menu_on = false;

    public Menu(GUI gui) {
        this.gui = gui;

        // Styling for the overlay
        this.backgroundMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        //this.backgroundMenu.setOpacity(1); // Set 50% transparency
        this.backgroundMenu.setOpacity(0.8); // 80% Deckkraft
        this.backgroundMenu.setEffect(new GaussianBlur(15)); // Unschärfe-Effekt hinzufügen
        this.backgroundMenu.setVisible(false);
        this.pMenu.setVisible(false);

        //this.vBoxMenu.setStyle("-fx-background-color: rgba(0, 2, 0, 0.5);");


        // Add menu items
        Button resumeButton = new Button("Back to Game");
        Button settingsButton = new Button("Settings");
        Button quitButton = new Button("Quit Game");

        resumeButton.setPrefSize(500, 50);  // Breite: 500px, Höhe: 50px
        settingsButton.setPrefSize(500, 50);
        quitButton.setPrefSize(500, 50);

        resumeButton.setFont(new Font("Arial", 20)); // Schriftgröße auf 20 setzen
        settingsButton.setFont(new Font("Arial", 20));
        quitButton.setFont(new Font("Arial", 20));


        // Add buttons to the VBox
        this.vBoxMenu.getChildren().addAll(resumeButton, settingsButton, quitButton);
        this.pMenu.getChildren().add(vBoxMenu);


        // Set size and position
        this.pMenu.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu
        this.backgroundMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setMenuPosition(300, 500, 10);


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

    private void onResume() {
        System.out.println("[Menu]: Back to Game");
        // Call a method from the GUI class to resume the game
        this.triggerMenu();
    }

    private void onSettings() {
        System.out.println("[Menu]: Open settings menu!");
    }

    private void onQuit() {
        System.out.println("[Menu]: Quit game!");
        System.exit(0);
    }

    public void setMenu_on(boolean menu) {
        this.menu_on = menu;
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
}
