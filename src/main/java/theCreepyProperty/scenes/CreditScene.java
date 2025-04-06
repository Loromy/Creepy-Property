package theCreepyProperty.scenes;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

import javafx.util.Duration;
import theCreepyProperty.checker.FileCheck;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;

public class CreditScene {
    private GUI gui;
    private Scene creditScene;
    private SoundPlayer soundPlayer;

    private Pane root = new Pane();
    private VBox textDisplay = new VBox();
    private VBox slidDown = new VBox();

    private VBox vBox_Text = new VBox();
    private VBox vBox_Butten = new VBox();

    private VBox headLine = new VBox();
    private VBox vBox_entwickler = new VBox();
    private VBox vBox_audio = new VBox();
    private VBox vBox_bilder = new VBox();

    private Label l_creepyProperty;
    private Label l_danke;

    private Label l_entwickler;
    private Label eName;

    private Label l_audio;
    private Label aName;

    private Label l_bilder;

    private Label bildPlayer;
    private Label bildPlayerLink;

    private Label bildStart;
    private Label bildStartLink;

    private Label bildKey;
    private Label bildKeyLink;

    private Label bilder;
    private Label bilderLink;


    private Label l_slidDown_CreepyProperty;
    private Label l_slidDown_danke;

    private Button back;

    public CreditScene(GUI gui) {
        System.out.println(".............................CreditScene..............................");
        this.gui = gui;

        // Initialize all Labels for the finish scene
        this.l_creepyProperty = new Label("Creepy Property\n\n\n");
        this.l_creepyProperty.setId("name");
        this.l_creepyProperty.setStyle("-fx-font-size: 60px;");

        this.l_danke = new Label("Danke fürs Spielen !");
        this.l_danke.setId("finishScene");
        this.l_danke.setStyle("-fx-font-size: 40px;");

        this.l_entwickler = new Label("Entwickler:");
        this.l_entwickler.setId("finishScene");
        this.l_entwickler.setStyle("-fx-font-size: 30px;");

        this.eName = new Label("Lucas Köhler");
        this.eName.setId("finishScene-text");

        this.l_audio = new Label("Alle Audio datei sind von:");
        this.l_audio.setId("finishScene");
        this.l_audio.setStyle("-fx-font-size: 30px;");

        this.aName = new Label("https://pixabay.com/de/sound-effects/");
        this.aName.setId("finishScene-text");

        this.l_bilder = new Label("Bilder:");
        this.l_bilder.setId("finishScene");
        this.l_bilder.setStyle("-fx-font-size: 40px;");

        this.bildPlayer = new Label("PLayer:");
        this.bildPlayer.setId("finishScene");
        this.bildPlayerLink = new Label("https://artistsnclients.com/slots/106828-character-sprite-sheet");
        this.bildPlayerLink.setId("finishScene-text");

        this.bildStart = new Label("Start Bildschirm Bild:");
        this.bildStart.setId("finishScene");
        this.bildStartLink = new Label(" generiert von Chat GPT: https://chatgpt.com");
        this.bildStartLink.setId("finishScene-text");

        this.bildKey = new Label("Schlüssel Bild:");
        this.bildKey.setId("finishScene");
        this.bildKeyLink = new Label(" generiert von Chat GPT: https://chatgpt.com");
        this.bildKeyLink.setId("finishScene-text");

        this.bilder = new Label("Alle anderen Bilder:");
        this.bilder.setId("finishScene");
        this.bilderLink = new Label("selber gepixelt und Bearbeitet");
        this.bilderLink.setId("finishScene-text");



        this.back = new Button("Back");

        this.l_slidDown_CreepyProperty = new Label("Creepy Property");
        this.l_slidDown_CreepyProperty.setId("name");
        this.l_slidDown_CreepyProperty.setStyle("-fx-font-size: 60px;");
        this.l_slidDown_danke = new Label("Danke fürs Spielen !");
        this.l_slidDown_danke.setId("name");
        this.l_slidDown_danke.setStyle("-fx-font-size: 40px;");

        createScene();
    }

    private void createScene() {
        // Load external styles for the finish scene
        this.textDisplay.getStylesheets().add((new FileCheck().checkPath("CreditScene", "src/resources/style/style.css")));
        this.slidDown.getStylesheets().add((new FileCheck().checkPath("CreditScene", "src/resources/style/style.css")));

        // Initialize and play background sound in loop
        this.soundPlayer = new SoundPlayer("src/resources/sounds/background/finish music.wav");
        this.soundPlayer.getClip().loop(javax.sound.sampled.Clip.LOOP_CONTINUOUSLY);
        this.soundPlayer.setVolume(this.gui.getGameScene().getMenu().getSettings().getAudio().getBackground());
        this.soundPlayer.play();

        // Stop the background music from the game scene
        this.gui.getGameScene().stopBackgroundMusic();

        // Set background image for the finish scene
        BackgroundImage backgroundImage = getBackgroundImage();
        this.root.setBackground(new Background(backgroundImage));

        // Set layout positions and spacing
        int width = 600;
        int height = 1350;
        Position(width, height);

        // Organize UI elements in VBox containers
        this.headLine.getChildren().addAll(this.l_creepyProperty,this.l_danke);
        this.headLine.setAlignment(Pos.TOP_CENTER);

        this.vBox_entwickler.getChildren().addAll(this.l_entwickler,this.eName);
        this.vBox_entwickler.setAlignment(Pos.TOP_CENTER);

        this.vBox_audio.getChildren().addAll(this.l_audio,this.aName);
        this.vBox_audio.setAlignment(Pos.TOP_CENTER);

        this.vBox_bilder.getChildren().addAll(this.l_bilder,this.bildPlayer,this.bildPlayerLink,this.bildStart,this.bildStartLink,this.bildKey,this.bildKeyLink,this.bilder,this.bilderLink);
        this.vBox_bilder.setAlignment(Pos.TOP_CENTER);

        // Add all VBoxes to the textDisplay VBox
        this.vBox_Text.getChildren().addAll(this.headLine,this.vBox_entwickler,this.vBox_audio,this.vBox_bilder);
        this.vBox_Butten.getChildren().addAll(this.l_slidDown_danke,this.back);

        this.textDisplay.getChildren().addAll(this.vBox_Text,this.vBox_Butten);

        // Add the slidDown label and text
        this.slidDown.getChildren().add(this.l_slidDown_CreepyProperty);

        // Add all elements to the root pane
        this.root.getChildren().addAll(this.textDisplay,this.slidDown);
        this.creditScene = new Scene(this.root, this.gui.getWidth(), this.gui.getHeight());

        // Animation for scrolling the text upwards
        TranslateTransition scrollAnimation = new TranslateTransition(Duration.seconds(30), this.textDisplay);
        scrollAnimation.setFromY(height - 300);
        scrollAnimation.setToY(((double) -height / 2)+200);
        scrollAnimation.setCycleCount(1);
        scrollAnimation.setInterpolator(javafx.animation.Interpolator.LINEAR);
        scrollAnimation.play();

        // Animation for sliding down the title
        TranslateTransition scrollAnimationTitel = new TranslateTransition(Duration.seconds(30), this.slidDown);
        scrollAnimationTitel.setFromY(-10000);
        scrollAnimationTitel.setToY(-100);
        scrollAnimationTitel.setCycleCount(1);
        scrollAnimationTitel.setInterpolator(javafx.animation.Interpolator.LINEAR);
        scrollAnimationTitel.play();

        // Handle the back button action
        this.back.setOnAction(_ -> onButtonBack());
    }

    // Get the background image for the finish scene
    private BackgroundImage getBackgroundImage() {
        Image image = new Image("file:src/resources/textures/background/Creepy Property Background.png");
        return new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
    }

    // Set position and layout of elements
    private void Position(double width, double height) {
        this.vBox_Text.setLayoutX((gui.getWidth() - width) / 2);
        this.vBox_Text.setLayoutY((gui.getHeight() - height) / 2);
        this.vBox_Text.setSpacing(20);
        this.vBox_Text.setAlignment(Pos.TOP_CENTER);

        this.vBox_Butten.setLayoutX((gui.getWidth() - width) / 2);
        this.vBox_Butten.setLayoutY((gui.getHeight() - height) / 2);
        this.vBox_Butten.setSpacing(20);
        this.vBox_Butten.setAlignment(Pos.CENTER);

        this.textDisplay.setPrefSize(width,height);
        this.textDisplay.setMinSize(width,height);
        this.textDisplay.setLayoutX((gui.getWidth() - width) / 2);
        this.textDisplay.setLayoutY((gui.getHeight() - height) / 2);
        this.textDisplay.setSpacing(450);
        this.textDisplay.setAlignment(Pos.TOP_CENTER);
        this.textDisplay.setId("finishScene-background");

        this.slidDown.setPrefSize(600,250);
        this.slidDown.setMinSize(600,250);
        this.slidDown.setLayoutX((double) (gui.getWidth() - 600) / 2);
        this.slidDown.setLayoutY((double) (gui.getHeight() - 250) / 2);
        this.slidDown.setAlignment(Pos.CENTER);
    }

    // Handle back button action (return to level select scene)
    private void onButtonBack() {
        this.soundPlayer.stop();
        this.gui.switchToLevelSelectScene();
    }

    public Scene getScene() {
        return creditScene;
    }

    // Delete Variables
    public void deleteFinishScene() {
        System.out.println("⚠ [CreditScene]: Alle Referenzen werden gelöscht...");

        if (this.soundPlayer != null) {
            this.soundPlayer.stop();
            this.soundPlayer = null;
        }

        if (this.root != null) {
            this.root.getChildren().clear();
        }

        this.l_creepyProperty = null;
        this.l_danke = null;
        this.l_entwickler = null;
        this.eName = null;
        this.l_audio = null;
        this.aName = null;
        this.l_bilder = null;
        this.bildPlayer = null;
        this.bildPlayerLink = null;
        this.bildStart = null;
        this.bildStartLink = null;
        this.bildKey = null;
        this.bildKeyLink = null;
        this.bilder = null;
        this.bilderLink = null;
        this.l_slidDown_CreepyProperty = null;
        this.l_slidDown_danke = null;

        this.back = null;

        this.textDisplay.getChildren().clear();
        this.slidDown.getChildren().clear();
        this.vBox_Text.getChildren().clear();
        this.vBox_Butten.getChildren().clear();
        this.headLine.getChildren().clear();
        this.vBox_entwickler.getChildren().clear();
        this.vBox_audio.getChildren().clear();
        this.vBox_bilder.getChildren().clear();

        this.root = null;
        this.textDisplay = null;
        this.slidDown = null;
        this.vBox_Text = null;
        this.vBox_Butten = null;
        this.headLine = null;
        this.vBox_entwickler = null;
        this.vBox_audio = null;
        this.vBox_bilder = null;

        this.creditScene = null;
        this.gui = null;

        System.gc();
        System.out.println("✔ [CreditScene]: Speicherbereinigung durchgeführt.");
    }
}