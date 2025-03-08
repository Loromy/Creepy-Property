package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

public class Settings{
    private final GUI gui;
    private final GameScene gameScene;
    private final Menu menu;
    private final Audio audio;
    private final ReadWriteSettings readWriteSettings;
    private SoundPlayer soundPlayer;

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

    private final String soundButtonClick = "src/resources/sounds/button click.wav";

    private final boolean DEVMODE = true; // Here you can activate developer options

    public Settings(GUI gui, GameScene gameScene, Menu menu)  {
        this.gui = gui;
        this.gameScene = gameScene;
        this.menu = menu;

        this.readWriteSettings = new ReadWriteSettings(this.gameScene.getGuiComponents());

        this.audio = new Audio(this.gui,this.gameScene,this);

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
        this.button1.setText("Audio Menu");
        this.button2.setText("----------");
        this.button3.setText("Overlay [ON]");
        this.button4.setText("Anzeige [OFF]");
        this.button5.setText("Collision [ON]");
        this.button6.setText("End-Scene");

        //this.button1.setStyle("-fx-text-fill: darkRed;");
        this.button2.setDisable(true);
        this.button3.setDisable(true);
        this.button5.setDisable(true);

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

        // Button action
        backButton.setOnAction(e -> onBack());
        button1.setOnAction(e -> onButton1());
        button2.setOnAction(e -> onButton2());
        button3.setOnAction(e -> onButton3());
        button4.setOnAction(e -> onButton4());
        button5.setOnAction(e -> onButton5());
        button6.setOnAction(e -> onButton6());

        System.out.println("gamescene: " + this.gameScene.getPlayer());
        devMode();
    }

    public void devMode() {
        if (DEVMODE) {
            this.button3.setDisable(false);
            this.button5.setDisable(false);
        } else {
            this.button3.setText("Disabled");
            this.button5.setText("Disabled");
            this.gameScene.getPlayer().setOverlay_on(false);
            this.gameScene.getPlayer().triggerOverlay(this.readWriteSettings,this.gameScene);
            this.gameScene.getMapCreate().setCollision_on(false);
            this.gameScene.getMapCreate().triggerCollision(this.readWriteSettings,this.gameScene);
        }
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

    private void onBack() {
        System.out.println("✔ [Settings]: Back");
        triggerSettings(); // Settings
        this.menu.getpMenu().setVisible(true); //Start Menu

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster()); // Standard 2
        this.soundPlayer.play();

        this.menu.triggerFocus();
    }

    private void onButton1() {
        this.pMenuSettings.setVisible(false);
        this.audio.triggerAudio();
        System.out.println("✔ [Settings]: button1 Audio");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton2() {
        System.out.println("✔ [Settings]: button2");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton3() {
        this.gameScene.getPlayer().triggerOverlay(this.readWriteSettings, this.gameScene);
        System.out.println("✔ [Settings]: button3 Overlay");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton4() {
        this.gameScene.getGuiComponents().triggerAnzeige(this.readWriteSettings);
        System.out.println("✔ [Settings]: button4 Anzeige");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton5() {
        this.gameScene.getMapCreate().triggerCollision(this.readWriteSettings,this.gameScene);
        System.out.println("✔ [Settings]: button5 Collision");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton6() {
        this.gui.switchToFinishScene();
        System.out.println("✔ [Settings]: button6 FinishScene");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    // Getter Methoden
    public Pane getMenuSettings() {
        return pMenuSettings;
    }

    public boolean getSettingOn() {
        return settings_on;
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }

    public Button getButton6() {
        return button6;
    }

    public Audio getAudio() {
        return this.audio;
    }

    // Setter Methoden
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
}
