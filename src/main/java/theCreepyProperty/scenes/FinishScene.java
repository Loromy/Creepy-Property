package theCreepyProperty.scenes;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.util.Duration;
import theCreepyProperty.main.GUI;
import theCreepyProperty.main.SoundPlayer;

public class FinishScene {
    private GUI gui;
    private Scene finishScene;
    private SoundPlayer soundPlayer;

    private final Pane root = new Pane();
    private final VBox textDisplay = new VBox();
    private final VBox slidDown = new VBox();

    private final VBox vBox_Text = new VBox();
    private final VBox vBox_Butten = new VBox();

    private final VBox ueberschrift = new VBox();
    private final VBox vBox_entwickler = new VBox();
    private final VBox vBox_audio = new VBox();
    private final VBox vBox_bilder = new VBox();

    private final Label l_creepyProperty;
    private final Label l_danke;
    private final Label l_entwickler;
    private final Label eName;
    private final Label l_audio;
    private final Label aName;
    private final Label l_bilder;
    private final Label bildPlayer;
    private final Label bildPlayerLink;
    private final Label bildStart;
    private final Label bildStartLink;

    private final Label l_slidDown_CreepyProperty;
    private final Label l_slidDown_danke;

    private final Button back;

    private int width = 600;
    private int height = 1200;

    public FinishScene(Stage stage, GUI gui) {
        this.gui = gui;

        this.l_creepyProperty = new Label("Creepy Property\n\n\n");
        this.l_creepyProperty.setId("finishScene");
        this.l_creepyProperty.setStyle("-fx-font-size: 60px;");

        this.l_danke = new Label("Danke fürs Spielen !");
        this.l_danke.setId("finishScene");
        this.l_danke.setStyle("-fx-font-size: 40px;");

        this.l_entwickler = new Label("Entwickler:");
        this.l_entwickler.setId("finishScene");
        this.l_entwickler.setStyle("-fx-font-size: 30px;");

        this.eName = new Label("Lucas Köhler");
        this.eName.setId("finishScene");

        this.l_audio = new Label("Alle Audio datei sind von:");
        this.l_audio.setId("finishScene");
        this.l_audio.setStyle("-fx-font-size: 30px;");

        this.aName = new Label("https://pixabay.com/de/sound-effects/");
        this.aName.setId("finishScene");

        this.l_bilder = new Label("Bilder:");
        this.l_bilder.setId("finishScene");
        this.l_bilder.setStyle("-fx-font-size: 40px;");

        this.bildPlayer = new Label("PLayer:");
        this.bildPlayer.setId("finishScene");

        this.bildPlayerLink = new Label("https://artistsnclients.com/slots/106828-character-sprite-sheet");
        this.bildPlayerLink.setId("finishScene");

        this.bildStart = new Label("Start Bildschirm Bild:");
        this.bildStart.setId("finishScene");

        this.bildStartLink = new Label(" generiert von Chat GPT: https://chatgpt.com");
        this.bildStartLink.setId("finishScene");

        this.back = new Button("Back");

        this.l_slidDown_CreepyProperty = new Label("Creepy Property");
        this.l_slidDown_CreepyProperty.setId("finishScene");
        this.l_slidDown_CreepyProperty.setStyle("-fx-font-size: 60px;");
        this.l_slidDown_danke = new Label("Danke fürs Spielen !");
        this.l_slidDown_danke.setId("finishScene");
        this.l_slidDown_danke.setStyle("-fx-font-size: 40px;");

        createScene();

        //this.text = new Text();
    }

    private void createScene() {
        // Styles
        this.textDisplay.getStylesheets().add(("file:src/resources/style/style.css"));
        this.slidDown.getStylesheets().add(("file:src/resources/style/style.css"));

//        // play sound
//        this.soundPlayer = new SoundPlayer("src/resources/sounds/background/background-atmosphere.wav");
//        this.soundPlayer.setVolume(3); // Standard 2
//        this.soundPlayer.play();

        //this.root.getChildren().add(new ImageView(new Image("file:src/resources/background/finish Scene Background.png")));
        BackgroundImage backgroundImage = getBackgroundImage();
        this.root.setBackground(new Background(backgroundImage));

        // Position
        Position(this.width, this.height, 20);

        //textDisplay.setTranslateY(300); // Startposition unten
        this.ueberschrift.getChildren().addAll(this.l_creepyProperty,this.l_danke);
        this.ueberschrift.setAlignment(Pos.TOP_CENTER);

        this.vBox_entwickler.getChildren().addAll(this.l_entwickler,this.eName);
        this.vBox_entwickler.setAlignment(Pos.TOP_CENTER);

        this.vBox_audio.getChildren().addAll(this.l_audio,this.aName);
        this.vBox_audio.setAlignment(Pos.TOP_CENTER);

        this.vBox_bilder.getChildren().addAll(this.l_bilder,this.bildPlayer,this.bildPlayerLink,this.bildStart,this.bildStartLink);
        this.vBox_bilder.setAlignment(Pos.TOP_CENTER);


        this.vBox_Text.getChildren().addAll(this.ueberschrift,this.vBox_entwickler,this.vBox_audio,this.vBox_bilder);
        this.vBox_Butten.getChildren().addAll(this.l_slidDown_danke,this.back);

        this.textDisplay.getChildren().addAll(this.vBox_Text,this.vBox_Butten);

        this.slidDown.getChildren().add(this.l_slidDown_CreepyProperty);

        this.root.getChildren().addAll(this.textDisplay,this.slidDown);
        this.finishScene = new Scene(this.root, this.gui.getWidth(), this.gui.getHeight());


        // Animation für das Hochscrollen
        TranslateTransition scrollAnimation = new TranslateTransition(Duration.seconds(20), this.textDisplay);
        scrollAnimation.setFromY(height - 300);
        scrollAnimation.setToY(((double) -height / 2)+200);
        scrollAnimation.setCycleCount(1);
        scrollAnimation.setInterpolator(javafx.animation.Interpolator.LINEAR);
        scrollAnimation.play();


        TranslateTransition scrollAnimationTitel = new TranslateTransition(Duration.seconds(20), this.slidDown);
        scrollAnimationTitel.setFromY(-10000);
        scrollAnimationTitel.setToY(-100);
        scrollAnimationTitel.setCycleCount(1);
        scrollAnimationTitel.setInterpolator(javafx.animation.Interpolator.LINEAR);
        scrollAnimationTitel.play();

        this.back.setOnAction(e -> onButtonBack());
    }

    private BackgroundImage getBackgroundImage() {
        Image image = new Image("file:src/resources/textures/background/finish Scene Background.png");

        // Setze das Hintergrundbild
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true)
        );
        return backgroundImage;
    }

    private void Position(double width, double height, int spacing) {
        this.vBox_Text.setLayoutX((gui.getWidth() - width) / 2);
        this.vBox_Text.setLayoutY((gui.getHeight() - height) / 2);
        this.vBox_Text.setSpacing(spacing);
        this.vBox_Text.setAlignment(Pos.TOP_CENTER);

        this.vBox_Butten.setLayoutX((gui.getWidth() - width) / 2);
        this.vBox_Butten.setLayoutY((gui.getHeight() - height) / 2);
        this.vBox_Butten.setSpacing(spacing);
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
        this.slidDown.setLayoutX((gui.getWidth() - 600) / 2);
        this.slidDown.setLayoutY((gui.getHeight() - 250) / 2);
        this.slidDown.setAlignment(Pos.CENTER);

    }

    private void onButtonBack() {
        this.gui.switchToLevelSelectScene();
    }

    public Scene getScene() {
        return finishScene;
    }
}
