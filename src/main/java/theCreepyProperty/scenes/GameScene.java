package theCreepyProperty.scenes;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import theCreepyProperty.Map.LevelData;
import theCreepyProperty.Map.MapCreate;
import theCreepyProperty.Map.MapReader;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Item;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.entity.Player;
import theCreepyProperty.main.*;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.screens.GameWin;

import java.util.Random;

public class GameScene {

    private final Stage stage;
    private final GUI gui;
    private Scene gameScene;
    private SoundPlayer soundPlayer;

    // Game Scene Pane
    private final Pane root = new Pane();
    private final Pane pMenu = new Pane();
    private final Pane pGameOver = new Pane();
    private final Pane pGameWin = new Pane();
    private final Pane pGame = new Pane();
    private final Pane pWallsItems = new Pane();
    private final Pane pGhosts = new Pane();

    // Game Scene Classes
    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private final MapReader mapReader = new MapReader();
    private final MapCreate mapCreate = new MapCreate();
    private ReadWriteSettings readWriteSettings;
    private ReadWriteSpielstand readWriteSpielstand;
    private Wall wall;
    private Item item;
    private Door door;
    private Ghost ghost;
    private final Player player;
    //private final Ghost ghost;
    private final GameOver gameOver;
    private final GameWin gameWin;
    private final Menu menu;
    private GuiComponents guiComponents;
    private final CollisionChecker checker;

    private Timeline timer;
    private double time_seconds = 0.0;

    private Timeline noiseTimer;
    private final Random random = new Random();

    // Liste von zufälligen Hintergrundgeräuschen
    private final String[] randomSounds = {
            "src/resources/sounds/background/random background noise/creepy-breath.wav",
            "src/resources/sounds/background/random background noise/creepy-hifreq-woosh.wav",
            "src/resources/sounds/background/random background noise/creepy-laugh.wav",
            "src/resources/sounds/background/random background noise/creepy-room-sound.wav",
            "src/resources/sounds/background/random background noise/creepy-whispering.wav",
            "src/resources/sounds/background/random background noise/creepy-wind.wav",
            "src/resources/sounds/background/random background noise/loud-thunder.wav"
    };

    public GameScene(Stage stage, GUI gui) {
        this.stage = stage;
        this.gui = gui;

        this.player = new Player(this.gui);
        this.gameOver = new GameOver(this.gui, this);
        this.gameWin = new GameWin(this.gui, this);
        this.menu = new Menu(this.gui, this);
        this.checker = new CollisionChecker(this);
        this.readWriteSettings = new ReadWriteSettings(this.guiComponents);

        createScene();

        // Settings Set
        this.readWriteSettings.settingsRead("src/resources/csv/Einstellungen/settings.csv",this,this.guiComponents);
        this.gui.getSelectScene().setVolume(this.menu.getSettings().getAudio().getMaster()); //selectScene audio volume update
        this.gui.getStartScene().setVolume(this.menu.getSettings().getAudio().getMaster()); //selectScene audio volume update
    }

    private void createScene() {
        // CSV-Datei lesen
        this.levelData = mapReader.readCsvFile(this.gui.getFilePath(), levelData);

        // Wände erstellen
        this.mapCreate.createMap(this.gui, this ,levelData);

        // GUI components
        this.guiComponents = new GuiComponents(this.gui, this.player,this);

        root.getChildren().addAll(pGame, pGameOver, pGameWin, pMenu);
        gameScene = new Scene(root, gui.getWidth(), gui.getHeight());

        //Styles //todo überprüfen ob style.css richtig geladen wurde
        gameScene.getStylesheets().add(("file:src/resources/style/style.css"));
        pGame.getStylesheets().add(("file:src/resources/style/style.css"));

        pGame.getChildren().add(new ImageView(new Image("file:src/resources/textures/flor/Flor.png")));
        pGame.getChildren().add(this.pWallsItems);
        pGame.getChildren().add(this.pGhosts);
        pGame.getChildren().add(this.player.getSolidPlayerAria());
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.player.loadOverlay());
        pGame.getChildren().add(this.guiComponents.gethBox_Level());
        pGame.getChildren().add(this.guiComponents.getvBox_anzeige());
        pGame.getChildren().add(this.guiComponents.gethBox_keys());
        pGame.getChildren().add(this.guiComponents.getvBox_Time());

        // Game Over / Win Menüs hinzufügen
        pGameOver.getChildren().add(this.gameOver.getBackgroundGameOver());
        pGameOver.getChildren().add(this.gameOver.getPGameOver());
        pGameOver.setVisible(false);

        pGameWin.getChildren().add(this.gameWin.getBackgroundGameWin());
        pGameWin.getChildren().add(this.gameWin.getPGameWin());
        pGameWin.setVisible(false);
        //pGameWin.setMouseTransparent(true);


        pMenu.getChildren().add(this.menu.getBackgroundMenu());
        pMenu.getChildren().add(this.menu.getpMenu());
        pMenu.getChildren().add(this.menu.getSettings().getMenuSettings());
        pMenu.getChildren().add(this.menu.getSettings().getAudio().getMenuAudio());

        // KeyHandler hinzufügen
        keyHandler = new KeyHandler(this.player, this.ghost, this, this.gui.getSelectScene(), this.menu, this.gameOver, this.gameWin);
        keyHandler.addKeyListener(gameScene, this);

        this.pMenu.setVisible(false);

        //timer Start
        startTimer();

        // play sound
        this.soundPlayer = new SoundPlayer("src/resources/sounds/background/background-creepy-sound.wav");
        this.soundPlayer.setVolume(this.getMenu().getSettings().getAudio().getBackground());
        this.soundPlayer.play();

        // random noise
        startRandomNoise();
    }

    // Timer
    private void startTimer() {
        time_seconds = 0.0; // Timer zurücksetzen
        timer = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            time_seconds += 0.01;

            // Berechnung der Zeitkomponenten
            int hours = (int) (time_seconds / 3600);
            int minutes = (int) ((time_seconds % 3600) / 60);
            int seconds = (int) (time_seconds % 60);
            int milliseconds = (int) ((time_seconds * 100) % 100); // Millisekunden berechnen

            // Formatierte Zeit als HH:MM:SS.mm anzeigen
            String formattedTime = String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, milliseconds);
            this.guiComponents.getL_time().setText("Time: " + formattedTime);
        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    // random background noises
    private void startRandomNoise() {
        noiseTimer = new Timeline(new KeyFrame(Duration.seconds(getRandomInterval()), event -> {
            playRandomNoise();
            noiseTimer.getKeyFrames().setAll(new KeyFrame(Duration.seconds(getRandomInterval()), e -> playRandomNoise()));
            noiseTimer.play();
        }));
        noiseTimer.setCycleCount(Timeline.INDEFINITE);
        noiseTimer.play();
    }

    private void playRandomNoise() {
        int soundIndex = random.nextInt(randomSounds.length);
        String soundPath = randomSounds[soundIndex];
        SoundPlayer noisePlayer = new SoundPlayer(soundPath);
        noisePlayer.setVolume((this.getMenu().getSettings().getAudio().getBackground())); // Leiser als Hintergrundmusik
        noisePlayer.play();
    }

    private int getRandomInterval() {
        return random.nextInt(25) + 5; // Zufälliges Intervall zwischen 5 und 30 Sekunden
    }


    public void stopBackgroundMusic() {
        this.soundPlayer.stop();
    }

    public void pGameItemChildren(Rectangle rectangle) {
        this.pWallsItems.getChildren().add(rectangle);
    }

    public void pGameItemChildren(ImageView image) {
        this.pWallsItems.getChildren().add(image);
    }

    public void pGameItemChildrenRemove(ImageView image) {
        this.pWallsItems.getChildren().remove(image);
    }

    public void pGameGhostsChildren(Rectangle rectangle) {
        this.pGhosts.getChildren().add(rectangle);
    }

    public void pGameGhostsChildren(ImageView image) {
        this.pGhosts.getChildren().add(image);
    }

    // Getter Methoden
    public GuiComponents getGuiComponents() {
        return guiComponents;
    }

    public Menu getMenu() {
        return menu;
    }

    public Pane getpMenu() {
        return pMenu;
    }

    public GameOver getGameOver() {
        return gameOver;
    }

    public GameWin getGameWin() {
        return gameWin;
    }

    public Wall getWall() {
        return this.wall;
    }

    public Item getItem() {
        return this.item;
    }

    public Door getDoor() {
        return this.door;
    }

    public Ghost getGhost() {
        return this.ghost;
    }

    public CollisionChecker getChecker() {
        return checker;
    }

    public LevelData getLevelData() {
        return levelData;
    }

    public MapCreate getMapCreate() {
        return mapCreate;
    }

    public Scene getScene() {
        return this.gameScene;
    }

    public Player getPlayer() {
        return this.player;
    }

    public double getTime_seconds() {
        return time_seconds;
    }

    public Pane getPGameWin() {
        return this.pGameWin;
    }

    public Pane getPGameOver() {
        return this.pGameOver;
    }

    // Setter Methoden
    public void setBlur(int strange) {
        pGame.setEffect(new GaussianBlur(strange));
    }
}
