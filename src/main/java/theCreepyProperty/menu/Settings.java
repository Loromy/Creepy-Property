package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import theCreepyProperty.main.GUI;

public class Settings {
    private final GUI gui;
    private final Menu menu;

    private final Pane pMenuSettings = new Pane();
    private final VBox vBoxSettings = new VBox();
    private final VBox vBoxSettingsL = new VBox();
    private final VBox vBoxSettingsR = new VBox();
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;

    private Button backButton;

    private boolean settings_on = false;

    public Settings(GUI gui, Menu menu) {
        this.gui = gui;
        this.menu = menu;

        this.button1 = new Button();
        this.button2 = new Button();
        this.button3 = new Button();
        this.button4 = new Button();
        this.button5 = new Button();
        this.button6 = new Button();
        this.backButton = new Button();

        pMenuSettings.setVisible(false);

        // Button text
        this.backButton.setText("Back");

        // Button Font
        this.backButton.setFont(new Font("Arial", 20));

        //TODO Test
        this.vBoxSettings.setStyle("-fx-background-color: rgba(30, 0, 0, 0.5);");
        this.vBoxSettingsL.setStyle("-fx-background-color: rgba(0, 100, 0, 0.5);");
        this.vBoxSettingsR.setStyle("-fx-background-color: rgba(0, 0, 500, 0.5);");

        System.out.println("[TEST]: Settings");

        // getChildren
        this.vBoxSettingsL.getChildren().addAll(button1, button3, button5);
        this.vBoxSettingsR.getChildren().addAll(button2, button4, button6);
        this.vBoxSettings.getChildren().add(backButton);
        this.vBoxSettings.getChildren().addAll(vBoxSettingsL, vBoxSettingsR);
        this.pMenuSettings.getChildren().add(vBoxSettings);

        // Set size and position
        this.pMenuSettings.setPrefSize(this.gui.getWidth(), this.gui.getHeight()); // Set width and height for the overlay menu
        setMenuSettingsPosition(vBoxSettings, 630, 500, 10);
        setMenuSettingsPositionLR(vBoxSettingsL, 300, 300, 10);
        setMenuSettingsPositionLR(vBoxSettingsR, 300, 300, 10);



        setMenuSettingsLayout(backButton, 300,50);
        setMenuSettingsLayout(button1, 300,50);
        setMenuSettingsLayout(button2, 300,50);
        setMenuSettingsLayout(button3, 300,50);
        setMenuSettingsLayout(button4, 300,50);
        setMenuSettingsLayout(button5, 300,50);
        setMenuSettingsLayout(button6, 300,50);

        // Button action
        backButton.setOnAction(e -> onBack());
    }

    public void triggerSettings(){
        if (!settings_on) {
            this.pMenuSettings.setVisible(true);
            this.settings_on = true;
        } else {
            this.pMenuSettings.setVisible(false);
            this.settings_on = false;
        }
    }

    public Pane getMenuSettings() {
        return pMenuSettings;
    }

    public boolean getSettingOn() {
        return settings_on;
    }

    private void setMenuSettingsPosition(VBox vBoxSettings, double width, double height, int spacing) {
        vBoxSettings.setPrefSize(width,height);
        vBoxSettings.setLayoutX((gui.getWidth() - width) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - height) / 2);
        vBoxSettings.setSpacing(spacing);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    private void setMenuSettingsPositionLR(VBox vBoxSettings, double width, double height, int spacing) {
        vBoxSettings.setPrefSize(width,height);
        vBoxSettings.setLayoutX((gui.getWidth() - width) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - height) / 2);
        vBoxSettings.setSpacing(spacing);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    private void setMenuSettingsLayout(Button button, double width, double height) {
        button.setPrefSize(width, height);  // Breite: 500px, Höhe: 50px
    }

    private void onBack() {
        System.out.println("[Settings]: Back");
        triggerSettings(); // Settings
        this.menu.getpMenu().setVisible(true); //Start Menu
    }
}
