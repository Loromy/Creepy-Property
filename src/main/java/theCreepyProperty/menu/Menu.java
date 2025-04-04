package theCreepyProperty.menu;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.scenes.GameScene;

public class Menu extends VBox {
    private GUI gui;
    private GameScene gameScene;
    private Pane backgroundMenu = new Pane(); // Background
    private Pane pMenu = new Pane(); // Menu Items
    private VBox vBoxMenu = new VBox();
    private Settings settings;
    private boolean menu_on = false;
    private SoundPlayer soundPlayer;

    private Label name;
    private Button resumeButton;
    private Button settingsButton;
    private Button backButton;

    private boolean ghostCanMoves = true;

    public Menu(GUI gui, GameScene gameScene) {
        this.gui = gui;
        this.gameScene = gameScene;
        settings = new Settings(this.gui, this.gameScene, this);

        // Background overlay
        backgroundMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.1);");
        backgroundMenu.setVisible(true);
        pMenu.setVisible(true);

        // UI elements
        name = new Label("The Creepy Property");
        name.setId("name");
        resumeButton = new Button("Back to Game");
        settingsButton = new Button("Settings");
        backButton = new Button("Level Auswahl");

        // Add items to menu layout
        vBoxMenu.getChildren().addAll(name, resumeButton, settingsButton, backButton);
        vBoxMenu.setId("background");
        pMenu.getChildren().add(vBoxMenu);

        // Set menu position
        pMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        backgroundMenu.setPrefSize(gui.getWidth(), gui.getHeight());
        setMenuPosition();

        // Sound effect
        soundPlayer = new SoundPlayer("src/resources/sounds/button click.wav");

        // Button actions
        resumeButton.setOnAction(_ -> onResume());
        settingsButton.setOnAction(_ -> onSettings());
        backButton.setOnAction(_ -> onBack());
    }

    // show Menu Toggle
    public void triggerMenu() {
        if (!menu_on) {
            gameScene.get_pMenu().setVisible(true);
            backgroundMenu.setVisible(true);
            pMenu.setVisible(true);
            gameScene.setBlur(15);
            menu_on = true;
            gameScene.getPlayer().stopGhostSound();
            gameScene.getTimer().stop();
            for (Ghost ghost : gameScene.getMapCreate().getGhostList()) {
                ghost.getTimeline().stop(); // Pause ghosts movement
            }
            resumeButton.requestFocus();
        } else {
            gameScene.get_pMenu().setVisible(false);
            backgroundMenu.setVisible(false);
            pMenu.setVisible(false);
            gameScene.setBlur(0);
            menu_on = false;
            gameScene.getTimer().play();
            if (ghostCanMoves) {
                for (Ghost ghost : gameScene.getMapCreate().getGhostList()) {
                    ghost.getTimeline().play(); // Resume ghosts
                }
            }
        }
    }

    // Set menu layout properties
    private void setMenuPosition() {
        vBoxMenu.setPrefSize(600, 500);
        vBoxMenu.setLayoutX((gui.getWidth() - (double) 600) / 2);
        vBoxMenu.setLayoutY((gui.getHeight() - (double) 500) / 2);
        vBoxMenu.setSpacing(10);
        vBoxMenu.setAlignment(Pos.CENTER);
    }

    // Resume game
    private void onResume() {
        soundPlayer.setVolume(gameScene.getMenu().getSettings().getAudio().getMaster());
        soundPlayer.play();
        triggerMenu();
    }

    // Open settings
    private void onSettings() {
        soundPlayer.setVolume(gameScene.getMenu().getSettings().getAudio().getMaster());
        soundPlayer.play();
        pMenu.setVisible(false);
        settings.triggerSettings();
    }

    // Return to level selection
    private void onBack() {
        soundPlayer.setVolume(gameScene.getMenu().getSettings().getAudio().getMaster());
        soundPlayer.play();
        gui.getSelectScene().setVolume(settings.getAudio().getMaster());
        gui.getStartScene().setVolume(settings.getAudio().getMaster());
        gui.getGameScene().stopBackgroundMusic();
        gui.switchToLevelSelectScene();
    }

    // Set initial button focus
    public void triggerFocus() {
        resumeButton.requestFocus();
    }

    // Getter Methoden
    public boolean getMenu_on(){
        return !this.menu_on;
    }

    public Pane get_pMenu() {
        return pMenu;
    }

    public Pane getBackgroundMenu() {
        return backgroundMenu;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setGhostCanMoves(boolean value) {
        this.ghostCanMoves = value;
    }

    // Delete Variables
    public void deleteMenu() {
        System.out.println("⚠ [Menu]: Alle Referenzen werden gelöscht...");

        if (this.gui != null) {
            this.gui = null;
        }

        if (this.gameScene != null) {
            this.gameScene = null;
        }

        if (this.soundPlayer != null) {
            this.soundPlayer = null;
        }

        if (this.name != null) {
            this.name = null;
        }

        if (this.resumeButton != null) {
            this.resumeButton = null;
        }

        if (this.settingsButton != null) {
            this.settingsButton = null;
        }

        if (this.backButton != null) {
            this.backButton = null;
        }

        if (this.pMenu != null) {
            this.pMenu = null;
        }

        if (this.backgroundMenu != null) {
            this.backgroundMenu = null;
        }

        if (this.vBoxMenu != null) {
            this.vBoxMenu = null;
        }

        if (this.settings != null) {
            this.settings.deleteSettings();
            this.settings = null;
        }

        this.menu_on = false;
        this.ghostCanMoves = true;

        System.gc();
        System.out.println("✔ [Menu]: Speicherbereinigung durchgeführt.");
    }

}
