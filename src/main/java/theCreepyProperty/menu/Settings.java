package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.Save.ReadWriteSettings;
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
    private Button b_EndScene;
    private Label l_Settings;

    private Button b_Back;
    private boolean settings_on = false;

    private final String soundButtonClick = "src/resources/sounds/button click.wav";

    private final boolean DEVMODE = true; // Here you can activate developer options

    public Settings(GUI gui, GameScene gameScene, Menu menu)  {
        System.out.println(".............................Settings..............................");
        this.gui = gui;
        this.gameScene = gameScene;
        this.menu = menu;

        this.readWriteSettings = new ReadWriteSettings(this.gameScene.getGuiComponents());

        this.audio = new Audio(this.gui,this.gameScene,this);

        this.l_Settings = new Label();

        this.b_Audio = new Button();
        this.b_Anzeige = new Button();
        this.b_EndScene = new Button();
        this.b_Back = new Button();

        pMenuSettings.setVisible(false);

        // Label text
        this.l_Settings.setText("Settings");
        this.l_Settings.setId("name");

        // Button text
        this.b_Back.setText("Back");
        this.b_Audio.setText("Audio Menu");
        this.b_Anzeige.setText("Anzeige [OFF]");
        this.b_EndScene.setText("End-Scene");

        // getChildren
        this.vBoxSettingsButtons.getChildren().addAll(l_Settings, b_Audio, b_Anzeige, b_EndScene); // Buttons Left
        this.vBoxSettingsBox.getChildren().addAll(vBoxSettingsButtons /*, vBoxSettingsR*/); // hBox für buttons Left/Right
        this.vBoxSettings.getChildren().add(vBoxSettingsBox);
        this.vBoxSettings.getChildren().add(b_Back); // Button back
        this.vBoxSettings.setId("background");
        this.pMenuSettings.getChildren().add(vBoxSettings);

        // Set size and position
        this.pMenuSettings.setPrefSize(this.gui.getWidth(), this.gui.getHeight()); // Set width and height for the overlay menu
        this.vBoxSettingsBox.setSpacing(20);
        this.vBoxSettingsBox.setAlignment(Pos.CENTER);
        setMenuSettingsPosition(vBoxSettings); //630
        setMenuSettingsPositionLR(vBoxSettingsButtons);

        // Button action
        b_Back.setOnAction(e -> onBack());
        b_Audio.setOnAction(e -> onButton1());
        b_Anzeige.setOnAction(e -> onButton2());
        b_EndScene.setOnAction(e -> onButton3());
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
        this.gameScene.getGuiComponents().triggerAnzeige(this.readWriteSettings);
        System.out.println("✔ [Settings]: button2 Anzeige");

        soundPlayer = new SoundPlayer(soundButtonClick);
        this.soundPlayer.setVolume(this.gameScene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();
    }

    private void onButton3() {
        this.gui.switchToFinishScene();
        System.out.println("✔ [Settings]: button3 FinishScene");

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

    public Button getB_Anzeige() {
        return b_Anzeige;
    }

    public Audio getAudio() {
        return this.audio;
    }

    // Setter Methoden
    private void setMenuSettingsPosition(VBox vBoxSettings) {
        vBoxSettings.setPrefSize(600, 500);
        vBoxSettings.setLayoutX((gui.getWidth() - (double) 600) / 2);
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

    public void deleteSettings() {
        System.out.println("⚠ [Settings]: Alle Referenzen werden gelöscht...");

        // GUI Referenzen löschen (Wird extern verwaltet)
        if (this.gui != null) {
            this.gui = null;
        }

        // GameScene löschen
        if (this.gameScene != null) {
            this.gameScene = null;
        }

        // Menu löschen
        if (this.menu != null) {
            this.menu = null;
        }

        // SoundPlayer löschen
        if (this.soundPlayer != null) {
            this.soundPlayer = null;
        }

        // Audio löschen
        if (this.audio != null) {
            this.audio = null;
        }

        // ReadWriteSettings löschen
        if (this.readWriteSettings != null) {
            this.readWriteSettings = null;
        }

        // UI-Elemente löschen
        if (this.l_Settings != null) {
            this.l_Settings = null;
        }

        if (this.b_Audio != null) {
            this.b_Audio = null;
        }

        if (this.b_Anzeige != null) {
            this.b_Anzeige = null;
        }

        if (this.b_EndScene != null) {
            this.b_EndScene = null;
        }

        if (this.b_Back != null) {
            this.b_Back = null;
        }

        // Pane-Elemente löschen
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

        // Statusvariablen zurücksetzen
        this.settings_on = false;

        // Garbage Collector anstoßen
        System.gc();
        System.out.println("✔ [Settings]: Speicherbereinigung durchgeführt.");
    }

}
