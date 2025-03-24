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
import theCreepyProperty.Map.TutorialMapInfo;
import theCreepyProperty.Save.ReadWriteSettings;
import theCreepyProperty.Save.ReadWriteSpielstand;
import theCreepyProperty.blocks.Door;
import theCreepyProperty.blocks.Key;
import theCreepyProperty.blocks.Wall;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.entity.Ghost;
import theCreepyProperty.entity.Player;
import theCreepyProperty.main.*;
import theCreepyProperty.menu.Menu;
import theCreepyProperty.screens.GameOver;
import theCreepyProperty.screens.GameWin;

import javax.sound.sampled.LineEvent;
import java.util.Arrays;
import java.util.Random;

public class GameScene {

    private Stage stage;
    private GUI gui;
    private TutorialMapInfo tutorialMapInfo;
    private Scene gameScene;
    private SoundPlayer soundPlayer;

    // Game Scene Pane
    private Pane root = new Pane();
    private Pane pMenu = new Pane();
    private Pane pGameOver = new Pane();
    private Pane pGameWin = new Pane();
    private Pane pGame = new Pane();
    private Pane pWallsItems = new Pane();
    private Pane pGhosts = new Pane();
    private Pane pTutorialMapInfoOver = new Pane();
    private Pane pTutorialMapInfoUnder = new Pane();

    // Game Scene Classes
    private KeyHandler keyHandler;
    private LevelData levelData = new LevelData();
    private MapReader mapReader = new MapReader(this.levelData);
    private MapCreate mapCreate = new MapCreate();
    private ReadWriteSettings readWriteSettings;
    private ReadWriteSpielstand readWriteSpielstand;
    private Wall wall;
    private Key key;
    private Door door;
    private Ghost ghost;
    private Player player;
    private GameOver gameOver;
    private GameWin gameWin;
    private Menu menu;
    private GuiComponents guiComponents;
    private CollisionChecker checker;

    private Timeline timer;
    private boolean isMusicStopped = false;

    private Timeline ghostTimer;

    private double time_seconds = 0.0;

    private Timeline randomNoiseTime;
    private final Random random = new Random();

    // Liste von zufälligen Hintergrundgeräuschen
    private final String[] randomSounds = {
            "src/resources/sounds/background/random background noise/creepy-breath.wav",
            "src/resources/sounds/background/random background noise/creepy-hifreq-woosh.wav",
            "src/resources/sounds/background/random background noise/creepy-laugh.wav",
            "src/resources/sounds/background/random background noise/creepy-room-sound.wav",
            "src/resources/sounds/background/random background noise/creepy-whispering.wav",
            "src/resources/sounds/background/random background noise/creepy-wind.wav",
            "src/resources/sounds/background/random background noise/loud-thunder.wav",
            "src/resources/sounds/background/random background noise/creepy-ambient.wav",
            "src/resources/sounds/background/random background noise/creepy-vocal-ambience.wav"
    };

    public GameScene(Stage stage, GUI gui) {
        System.out.println(".............................GameScene..............................");
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
        this.levelData = mapReader.readCsvFile(this.gui.getFilePath());

        // Wände erstellen
        this.mapCreate.createMap(this.gui, this ,levelData);

        // GUI components
        this.guiComponents = new GuiComponents(this.gui, this.player,this);

        // Tutorial map Overlay
        if (this.gui.getSelectScene().getMapSelected() == 0) {
            this.tutorialMapInfo = new TutorialMapInfo(this.gui, this);
        }

        root.getChildren().addAll(pGame, pGameOver, pGameWin, pMenu);
        gameScene = new Scene(root, gui.getWidth(), gui.getHeight());

        //Styles //todo überprüfen ob style.css richtig geladen wurde
        gameScene.getStylesheets().add((new FileCheck().checkPath("GameScene", "src/resources/style/style.css")));
        pGame.getStylesheets().add((new FileCheck().checkPath("GameScene", "src/resources/style/style.css")));

        pGame.getChildren().add(new ImageView(new Image(new FileCheck().checkImage("GameScene","file:src/resources/textures/flor/Flor.png"))));
        pGame.getChildren().add(this.pWallsItems);
        pGame.getChildren().add(this.pTutorialMapInfoUnder);
        pGame.getChildren().add(this.pGhosts);
        pGame.getChildren().add(this.player.getSolidPlayerAria());
        pGame.getChildren().add(this.player.draw());
        pGame.getChildren().add(this.player.loadOverlay());
        pGame.getChildren().add(this.pTutorialMapInfoOver);
        pGame.getChildren().add(this.guiComponents.gethBox_Level());
        pGame.getChildren().add(this.guiComponents.getvBox_anzeige());
        pGame.getChildren().add(this.guiComponents.gethBox_keys());
        pGame.getChildren().add(this.guiComponents.getvBox_Time());
        pGame.getChildren().add(this.guiComponents.getvBox_Deaths());
        pGame.getChildren().add(this.guiComponents.getvBox_sprint());

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
        keyHandler = new KeyHandler(this.player, this, this.gui.getSelectScene(), this.menu, this.gameOver, this.gameWin);
        keyHandler.addKeyListener(gameScene, this);

        this.pMenu.setVisible(false);

        //timer Start
        startTimer();

        // play sound in loop
        this.soundPlayer = new SoundPlayer("src/resources/sounds/background/background-creepy-sound.wav");

        // Hintergrundmusik in Dauerschleife abspielen
        this.soundPlayer.getClip().addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP && !isMusicStopped) {
                this.soundPlayer.setVolume(this.getMenu().getSettings().getAudio().getBackground());
                this.soundPlayer.play(); // Musik neu starten
            }
        });
        this.soundPlayer.play();


        // random noise
        startRandomNoise();
    }

    // Timer
    private void startTimer() {
        time_seconds = 0.0; // Timer zurücksetzen
        time_seconds = this.gui.getSelectScene().getTimePlaying();
        timer = new Timeline(new KeyFrame(Duration.millis(10), event -> { // alle 10ms prüfen
            time_seconds += 0.01;

            // Berechnung der Zeitkomponenten
            int hours = (int) (time_seconds / 3600);
            int minutes = (int) ((time_seconds % 3600) / 60);
            int seconds = (int) (time_seconds % 60);
            int milliseconds = (int) ((time_seconds * 100) % 100); // Millisekunden berechnen

            // Formatierte Zeit als HH:MM:SS.mm anzeigen
            String formattedTime = String.format("%02d : %02d : %02d : %02d", hours, minutes, seconds, milliseconds);
            this.guiComponents.getL_time().setText("Time: " + formattedTime);
            this.gui.getSelectScene().setTimePlaying(time_seconds);
        }));

        ghostTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> { // alle 500ms prüfen
            player.checkForNearbyGhosts(this.mapCreate.getGhostList());
        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
        ghostTimer.setCycleCount(Timeline.INDEFINITE);
        ghostTimer.play();
    }

    public void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
        if (ghostTimer != null) {
            ghostTimer.stop();
        }
        if (randomNoiseTime != null) {
            randomNoiseTime.stop();
        }
    }

    // random background noises
    private void startRandomNoise() {
        nextNoise();
    }

    private void nextNoise() {
        double interval = getRandomInterval();

        this.randomNoiseTime = new Timeline(new KeyFrame(Duration.seconds(interval), event -> {
            playRandomNoise();
            nextNoise();
        }));

        this.randomNoiseTime.setCycleCount(1);
        this.randomNoiseTime.play();
    }

    private void playRandomNoise() {
        int soundIndex = random.nextInt(randomSounds.length);
        String soundPath = randomSounds[soundIndex];
        SoundPlayer noisePlayer = new SoundPlayer(soundPath);

        noisePlayer.setVolume(this.getMenu().getSettings().getAudio().getBackground());
        noisePlayer.play();
    }

    private int getRandomInterval() {
        int noise = random.nextInt(25) + 5;

        System.out.println("▶ [GameScene]: getRandomInterval() next RandomSoundNoise in: " + noise + "s");
        return noise; // Zufälliges Intervall zwischen 5 und 30 Sekunden
    }


    public void stopBackgroundMusic() {
        isMusicStopped = true;
        this.soundPlayer.stop();
        if (this.randomNoiseTime != null) {
            this.randomNoiseTime.stop();
        }
    }

    // getChildren
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

    public void pTutorialMapinfoOverChildren(Pane pane) {
        this.pTutorialMapInfoOver.getChildren().add(pane);
    }

    public void pTutorialMapInfoUnderChildren(Pane pane) {
        this.pTutorialMapInfoUnder.getChildren().add(pane);
    }

    // Getter Methoden
    public GuiComponents getGuiComponents() {
        return guiComponents;
    }

    public TutorialMapInfo getTutorialMapInfo() {
        return this.tutorialMapInfo;
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

    public Key getItem() {
        return this.key;
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

    public Timeline getTimer() {
        return timer;
    }

    // Setter Methoden
    public void setBlur(int strange) {
        pGame.setEffect(new GaussianBlur(strange));
    }

    public void deleteGameScene() {
        // Alle Objekte auf null setzen und delete-Methode aufrufen
        if (this.keyHandler != null) {
            this.keyHandler.deleteKeyHandler();
            this.keyHandler = null;
        }

        if (this.gameOver != null) {
            this.gameOver.deleteGameOver();
            this.gameOver = null;
        }

        if (this.gameWin != null) {
            this.gameWin.deleteGameWin();
            this.gameWin = null;
        }

        if (this.menu != null) {
            this.menu.deleteMenu();
            this.menu = null;
        }

        if (this.player != null) {
            this.player.deletePlayer();
            this.player = null;
        }

        if (this.guiComponents != null) {
            this.guiComponents.deleteGuiComponents();
            this.guiComponents = null;
        }

        if (this.checker != null) {
            this.checker.deleteCollisionChecker();
            this.checker = null;
        }

        if (this.readWriteSettings != null) {
            this.readWriteSettings.deleteReadWriteSettings();
            this.readWriteSettings = null;
        }

        if (this.readWriteSpielstand != null) {
            this.readWriteSpielstand.deleteReadWriteSpielstand();
            this.readWriteSpielstand = null;
        }

        if (this.mapReader != null) {
            this.mapReader.deleteMapReader();
            this.mapReader = null;
        }

        if (this.mapCreate != null) {
            this.mapCreate.deleteMapCreate();
            this.mapCreate = null;
        }

        if (this.tutorialMapInfo != null) {
            this.tutorialMapInfo.deleteTutorialMapInfo();
            this.tutorialMapInfo = null;
        }

        if (this.soundPlayer != null) {
            this.soundPlayer.stop();
            this.soundPlayer = null;
        }

        // Weitere Referenzen auf null setzen
        this.stage = null;
        this.gui = null;
        this.gameScene = null;

        // Game Scene Pane
        this.root = null;
        this.pMenu = null;
        this.pGameOver = null;
        this.pGameWin = null;
        this.pGame = null;
        this.pWallsItems = null;
        this.pGhosts = null;
        this.pTutorialMapInfoOver = null;
        this.pTutorialMapInfoUnder = null;

        // Weitere Variablen
        this.levelData = null;
        this.wall = null;
        this.key = null;
        this.door = null;
        this.ghost = null;

        this.timer = null;
        this.ghostTimer = null;
        this.randomNoiseTime = null;

        // Primitive Datentypen zurücksetzen
        this.isMusicStopped = false;
        this.time_seconds = 0.0;

        // Falls es Listen oder Maps gibt, zuerst leeren
        if (this.randomSounds != null) {
            Arrays.fill(this.randomSounds, null); // Array-Inhalt löschen
        }

        // Garbage Collector anstoßen
        System.gc();
        System.out.println("✔ [GameScene]: Speicherbereinigung durchgeführt.");
    }
}