package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.main.GUI;
import theCreepyProperty.scenes.GameScene;

public class Settings {
    private final GUI gui;
    private final GameScene scene;
    private final Menu menu;

    private final Pane pMenuSettings = new Pane();
    private final VBox vBoxSettings = new VBox();
    private final HBox hBoxSettingsLR = new HBox();
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

    public Settings(GUI gui, GameScene scene, Menu menu) {
        this.gui = gui;
        this.scene = scene;
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
        this.button1.setText("Game Over Screen");
        this.button2.setText("button2");
        this.button3.setText("button3");
        this.button4.setText("button4");
        this.button5.setText("button5");
        this.button6.setText("button6");

        // Button Font
//        this.backButton.setFont(new Font("Arial", 20));
//        this.button1.setFont(new Font("Arial", 20));
//        this.button2.setFont(new Font("Arial", 20));
//        this.button3.setFont(new Font("Arial", 20));
//        this.button4.setFont(new Font("Arial", 20));
//        this.button5.setFont(new Font("Arial", 20));
//        this.button6.setFont(new Font("Arial", 20));

        // getChildren
        this.vBoxSettingsL.getChildren().addAll(button1, button3, button5); // Buttons Left
        this.vBoxSettingsR.getChildren().addAll(button2, button4, button6); // Buttons Right
        this.hBoxSettingsLR.getChildren().addAll(vBoxSettingsL, vBoxSettingsR); // hBox für buttons Left/Right
        this.vBoxSettings.getChildren().add(hBoxSettingsLR);
        this.vBoxSettings.getChildren().add(backButton); // Button back
        this.vBoxSettings.setId("background");
        this.pMenuSettings.getChildren().add(vBoxSettings);

        // Set size and position
        this.pMenuSettings.setPrefSize(this.gui.getWidth(), this.gui.getHeight()); // Set width and height for the overlay menu
        this.hBoxSettingsLR.setSpacing(20);
        this.hBoxSettingsLR.setAlignment(Pos.CENTER);
        setMenuSettingsPosition(vBoxSettings); //630
        setMenuSettingsPositionLR(vBoxSettingsL);
        setMenuSettingsPositionLR(vBoxSettingsR);

//        setMenuSettingsLayout(backButton, 300,50);
//        setMenuSettingsLayout(button1, 300,50);
//        setMenuSettingsLayout(button2, 300,50);
//        setMenuSettingsLayout(button3, 300,50);
//        setMenuSettingsLayout(button4, 300,50);
//        setMenuSettingsLayout(button5, 300,50);
//        setMenuSettingsLayout(button6, 300,50);

        // Button action
        backButton.setOnAction(e -> onBack());
        button1.setOnAction(e -> onButton1());
        button2.setOnAction(e -> onButton2());
        button3.setOnAction(e -> onButton3());
        button4.setOnAction(e -> onButton4());
        button5.setOnAction(e -> onButton5());
        button6.setOnAction(e -> onButton6());
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

    private void setMenuSettingsPosition(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(700, 500);
        vBoxSettings.setLayoutX((gui.getWidth() - (double) 700) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - (double) 500) / 2);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    private void setMenuSettingsPositionLR(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(300, 300);
        vBoxSettings.setLayoutX((gui.getWidth() - (double) 300) / 2);
        vBoxSettings.setLayoutY((gui.getHeight() - (double) 300) / 2);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

//    private void setMenuSettingsLayout(Button button, double width, double height) {
//        button.setPrefSize(width, height);
//    }

    private void onBack() {
        System.out.println("[Settings]: Back ✔");
        triggerSettings(); // Settings
        this.menu.getpMenu().setVisible(true); //Start Menu
        this.menu.triggerFocus();
    }

    private void onButton1() {
        this.scene.getGameOver().triggerGameOver();
        this.backButton.requestFocus();
        System.out.println("[Settings]: button1 (Game Over Screen) ✔");
    }

    private void onButton2() {
        System.out.println("[Settings]: button2 ✔");
    }

    private void onButton3() {
        System.out.println("[Settings]: button3 ✔");
    }

    private void onButton4() {
        System.out.println("[Settings]: button4 ✔");
    }

    private void onButton5() {
        System.out.println("[Settings]: button5 ✔");
    }

    private void onButton6() {
        System.out.println("[Settings]: button6 ✔");
    }
}
