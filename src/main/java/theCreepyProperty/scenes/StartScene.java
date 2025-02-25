package theCreepyProperty.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;
import theCreepyProperty.screens.StartMenu;

public class StartScene {

    private final Stage stage;
    private final GUI gui;
    private Scene startScene;
    private SoundPlayer soundPlayer;

    private final StartMenu startMenu;

    private final String soundButtonClick = "src/resources/sounds/button click.wav";

    private int volume = 50;

    public StartScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.startMenu = new StartMenu(this.gui);
        createScene();

        if (this.gui.getGameScene() != null) {
            this.volume = this.gui.getGameScene().getMenu().getSettings().getAudio().getMaster();
        }
    }

    private void createScene() {
        StackPane startScreen = new StackPane();

        startScreen.getStylesheets().add(("file:src/resources/style/style.css"));

        startScreen.getChildren().add(this.startMenu.getBackgroundStartMenu());
        startScreen.getChildren().add(this.startMenu.getpStartMenu());
        this.startScene = new Scene(startScreen, gui.getWidth(), gui.getHeight());

        startMenu.getStartButton().setOnAction(e -> {
            System.out.println("✔ [StartScene]: Spiel wird gestartet...");

            soundPlayer = new SoundPlayer(soundButtonClick);
            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();

            gui.switchToLevelSelectScene();
        });
        startMenu.getQuitButton().setOnAction(e -> {
            System.out.println("✔ [Start Menu]: Quit");

            soundPlayer = new SoundPlayer(soundButtonClick);
            this.soundPlayer.setVolume(volume);
            this.soundPlayer.play();

            System.exit(0);
        });
    }

    // Getter Methode
    public Scene getScene() {
        return this.startScene;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}