package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

public class Menu extends VBox {
    private GUI gui;
    private GameScene scene;
    private final Pane backgroundMenu = new Pane(); // Background
    private final Pane pMenu = new Pane(); // Menu Items
    private final VBox vBoxMenu = new VBox();
    private final Settings settings;
    private boolean menu_on = false;
    private SoundPlayer soundPlayer;

    private final Label name;
    private final Button resumeButton;
    private final Button settingsButton;
    private final Button backButton;

    public Menu(GUI gui, GameScene scene) {
        this.gui = gui;
        this.scene = scene;
        settings = new Settings(this.gui, this.scene,this);

        //overlay
        backgroundMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        this.backgroundMenu.setVisible(true);
        this.pMenu.setVisible(true);

        // Add menu items
        this.name = new Label("The Creepy Property");
        this.name.setId("name");
        this.resumeButton = new Button("Back to Game");
        this.settingsButton = new Button("Settings");
        this.backButton = new Button("Level Auswahl");

        // Add buttons to the VBox
        this.vBoxMenu.getChildren().addAll(/*logoView,*/ name, resumeButton, settingsButton, backButton);
        this.vBoxMenu.setId("background");
        this.pMenu.getChildren().add(vBoxMenu);

        // Set size and position
        this.pMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        this.backgroundMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        this.setMenuPosition(600, 500, 10);

        //Soundplayer
        soundPlayer = new SoundPlayer("src/resources/sounds/button click.wav");

        // Button actions
        this.resumeButton.setOnAction(e -> onResume());
        this.settingsButton.setOnAction(e -> onSettings());
        this.backButton.setOnAction(e -> onBack());
    }

    public void triggerMenu(){
        if (!menu_on) {
            this.scene.getpMenu().setVisible(true);
            this.backgroundMenu.setVisible(true);
            this.pMenu.setVisible(true);
            this.scene.setBlur(15); //Menu blur
            this.menu_on = true;

            this.resumeButton.requestFocus();
        } else {
            this.scene.getpMenu().setVisible(false);
            this.backgroundMenu.setVisible(false);
            this.pMenu.setVisible(false);
            this.scene.setBlur(0); //Menu blur
            this.menu_on = false;
        }
    }

    private void setMenuPosition(double width, double height, int spacing) {
        this.vBoxMenu.setPrefSize(width,height);
        this.vBoxMenu.setMinSize(width,height);
        this.vBoxMenu.setLayoutX((gui.getWidth() - width) / 2);
        this.vBoxMenu.setLayoutY((gui.getHeight() - height) / 2);
        this.vBoxMenu.setSpacing(spacing);
        this.vBoxMenu.setAlignment(Pos.CENTER);
    }

    private void onResume() {
        System.out.println("[Menu]: Back to Game ✔");
        this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();

        this.triggerMenu();
    }

    private void onSettings() {
        System.out.println("[Menu]: Open settings menu ✔");
        this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();

        this.pMenu.setVisible(false);
        this.settings.triggerSettings();
    }

    private void onBack() {
        System.out.println("[Menu]: Start Menu ✔");
        this.soundPlayer.setVolume(this.scene.getMenu().getSettings().getAudio().getMaster());
        this.soundPlayer.play();

        this.gui.getSelectScene().setVolume(this.settings.getAudio().getMaster()); //selectScene audio volume update
        this.gui.getStartScene().setVolume(this.settings.getAudio().getMaster()); //selectScene audio volume update
        this.gui.getGameScene().stopBackgroundMusic();

        this.gui.switchToLevelSelectScene();
    }

    public void triggerFocus() {
        this.resumeButton.requestFocus();
    }

    // Getter Methoden
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
