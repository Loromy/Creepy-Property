package com.example.thecreepyproparty.main;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
    private GUI gui;

    private VBox menu = new VBox();

    private boolean menu_on = false;

    public Menu(GUI gui) {
        this.gui = gui;

        //super(10); // 10px spacing between menu items

        // Styling for the overlay
        this.menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
        this.menu.setOpacity(1); // Set 50% transparency
        this.menu.setVisible(false);


        // Add menu items
        Button resumeButton = new Button("Resume");
        Button settingsButton = new Button("Settings");
        Button quitButton = new Button("Quit");

        // Add buttons to the VBox
        this.menu.getChildren().addAll(resumeButton, settingsButton, quitButton);

        // Set size and position
        this.menu.setPrefSize(gui.getWidth(), gui.getHeight()); // Set width and height for the overlay menu

        // Button actions
        resumeButton.setOnAction(e -> onResume());
        settingsButton.setOnAction(e -> onSettings());
        quitButton.setOnAction(e -> onQuit());
    }

    // Example methods for button actions
    private void onResume() {
        System.out.println("Game resumed!");
        // Call a method from the GUI class to resume the game
        //gui.resumeGame();  // Example, assuming GUI has a method to resume
    }

    private void onSettings() {
        System.out.println("Open settings menu!");
        // Call a method from the GUI class to open settings
        //gui.openSettings();  // Example, assuming GUI has a method for settings
    }

    private void onQuit() {
        System.out.println("Quit game!");
        // Call a method from the GUI class to quit the game
        //gui.quitGame();  // Example, assuming GUI has a method to quit the game
    }

    public void setMenu_on(boolean menu) {
        this.menu_on = menu;
    }

    public boolean getMenu_on(){
        return this.menu_on;
    }

    public VBox getMenu() {
        return menu;
    }
}
