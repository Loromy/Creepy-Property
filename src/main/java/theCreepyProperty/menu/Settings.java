package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.save.ReadWriteSettings;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

public class Settings{
    private GUI gui;
    private GameScene gameScene;
    private Menu menu;
    private Audio audio;
    private ReadWriteSettings readWriteSettings;
    private SoundPlayer soundPlayer;

    private Pane pMenuSettings = new Pane();
    private VBox vBoxSettings = new VBox();
    private VBox vBoxSettingsBox = new VBox();
    private VBox vBoxSettingsButtons = new VBox();

    private Button b_Audio;
    private Button b_Anzeige;
    private Button b_Credits;
    private Button b_Back;
    private Label l_Settings;

    private boolean settings_on = false;

    public Settings(GUI gui, GameScene gameScene, Menu menu) {
        System.out.println(".............................Settings..............................");
        this.gui = gui;
        this.gameScene = gameScene;
        this.menu = menu;
        this.readWriteSettings = new ReadWriteSettings(this.gameScene.getGuiComponents());
        this.audio = new Audio(this.gui, this.gameScene, this);

        // UI elements
        this.l_Settings = new Label("Settings");
        this.l_Settings.setId("name");

        this.b_Audio = new Button("Audio Menu");
        this.b_Anzeige = new Button("Anzeige [OFF]");
        this.b_Credits = new Button("Credits");
        this.b_Back = new Button("Back");

        // Add buttons to layout
        this.vBoxSettingsButtons.getChildren().addAll(l_Settings, b_Audio, b_Anzeige, b_Credits);
        this.vBoxSettingsBox.getChildren().addAll(vBoxSettingsButtons);
        this.vBoxSettings.getChildren().addAll(vBoxSettingsBox, b_Back);
        this.vBoxSettings.setId("background");
        this.pMenuSettings.getChildren().add(vBoxSettings);
        this.pMenuSettings.setVisible(false);

        // Set layout properties
        this.pMenuSettings.setPrefSize(this.gui.getWidth(), this.gui.getHeight());
        this.vBoxSettingsBox.setSpacing(20);
        this.vBoxSettingsBox.setAlignment(Pos.CENTER);
        setMenuSettingsPosition(vBoxSettings);
        setMenuSettingsPositionLR(vBoxSettingsButtons);

        // Button actions
        b_Back.setOnAction(_ -> onBack());
        b_Audio.setOnAction(_ -> onButton1());
        b_Anzeige.setOnAction(_ -> onButton2());
        b_Credits.setOnAction(_ -> onButton3());
    }

    // Toggle settings menu visibility
    public void triggerSettings() {
        settings_on = !settings_on;
        pMenuSettings.setVisible(settings_on);
    }

    // Back
    private void onBack() {
        System.out.println("✔ [Settings]: Back");
        triggerSettings(); // Close settings menu
        menu.get_pMenu().setVisible(true); // Show main menu
        playButtonSound();
        menu.triggerFocus();
    }

    // Open audio Menu
    private void onButton1() {
        pMenuSettings.setVisible(false);
        audio.triggerAudio();
        System.out.println("✔ [Settings]: Open Audio Menu");
        playButtonSound();
    }

    //Toggle Anzeige
    private void onButton2() {
        gameScene.getGuiComponents().triggerAnzeige(readWriteSettings);
        System.out.println("✔ [Settings]: Toggle Anzeige");
        playButtonSound();
    }

    // Show Credits
    private void onButton3() {
        System.out.println("✔ [Settings]: Switch to Credits");
        playButtonSound();
        gui.switchToFinishScene();
    }

    // button click sound
    private void playButtonSound() {
        String soundButtonClick = "src/resources/sounds/button click.wav";
        soundPlayer = new SoundPlayer(soundButtonClick);
        soundPlayer.setVolume(gameScene.getMenu().getSettings().getAudio().getMaster());
        soundPlayer.play();
    }

    // Set menu-settings layout properties
    private void setMenuSettingsPosition(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(600, 500);
        vBoxSettings.setLayoutX((gui.getWidth() - 600) / 2.0);
        vBoxSettings.setLayoutY((gui.getHeight() - 500) / 2.0);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    private void setMenuSettingsPositionLR(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(300, 300);
        vBoxSettings.setLayoutX((gui.getWidth() - 300) / 2.0);
        vBoxSettings.setLayoutY((gui.getHeight() - 300) / 2.0);
        vBoxSettings.setSpacing(10);
        vBoxSettings.setAlignment(Pos.CENTER);
    }

    // Getters
    public Pane getMenuSettings() {
        return pMenuSettings;
    }

    public boolean getSettingOn() {
        return settings_on;
    }

    public Button getB_Anzeige() {
        return b_Anzeige;
    }

    public Audio getAudio() {
        return this.audio;
    }

    // Delete Variables
    public void deleteSettings() {
        System.out.println("⚠ [Settings]: Alle Referenzen werden gelöscht...");

        if (this.gui != null) {
            this.gui = null;
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        if (this.menu != null) {
            this.menu = null;
        }

        if (this.soundPlayer != null) {
            this.soundPlayer = null;
        }

        if (this.audio != null) {
            this.audio.deleteAudio();
            this.audio = null;
        }

        if (this.readWriteSettings != null) {
            this.readWriteSettings = null;
        }

        if (this.l_Settings != null) {
            this.l_Settings = null;
        }

        if (this.b_Audio != null) {
            this.b_Audio = null;
        }

        if (this.b_Anzeige != null) {
            this.b_Anzeige = null;
        }

        if (this.b_Credits != null) {
            this.b_Credits = null;
        }

        if (this.b_Back != null) {
            this.b_Back = null;
        }

        if (this.pMenuSettings != null) {
            this.pMenuSettings = null;
        }

        if (this.vBoxSettings != null) {
            this.vBoxSettings = null;
        }

        if (this.vBoxSettingsBox != null) {
            this.vBoxSettingsBox = null;
        }

        if (this.vBoxSettingsButtons != null) {
            this.vBoxSettingsButtons = null;
        }

        this.settings_on = false;

        System.gc();
        System.out.println("✔ [Settings]: Speicherbereinigung durchgeführt.");
    }
}